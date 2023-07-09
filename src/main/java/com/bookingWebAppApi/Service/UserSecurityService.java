package com.bookingWebAppApi.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookingWebAppApi.Model.UserPrincipal;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException/*, LockedException */{

        Userr user = userRepository.findByUsername(username);
        System.out.println("here!");

        if (user == null) {
            throw new UsernameNotFoundException("Username not found");

        } else {

            validateUserLogin(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            return userPrincipal;
        }

    }

    private void validateUserLogin(Userr user) {

        if (!user.isAccountNonLocked()) {

            if (userService.unlock(user)) {

            }

        }

    }

}
