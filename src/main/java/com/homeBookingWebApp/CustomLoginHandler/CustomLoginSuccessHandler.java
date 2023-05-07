package com.homeBookingWebApp.CustomLoginHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.bookingwebapppApi.ModelPackage.UserPrincipal;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.ServicePackage.UserService;

@Component
public class CustomLoginSuccessHandler {
    @Autowired
    private UserService userService;


    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {

        Object principal = event.getAuthentication().getPrincipal();

        if(principal instanceof UserPrincipal) {

            UserPrincipal userPrincipal = (UserPrincipal) event.getAuthentication().getPrincipal();
            Userr user = userService.findByUsername(userPrincipal.getUsername());
            if (user.getFailedAttempt() > 0) {

                userService.resetFailedAttempts(user.getUsername());
            }
        }
    }


}
