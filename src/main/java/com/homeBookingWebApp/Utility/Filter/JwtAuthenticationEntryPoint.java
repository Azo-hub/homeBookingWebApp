package com.homeBookingWebApp.Utility.Filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.bookingwebapppApi.UtilityPackage.HttpCustomResponse;
import com.bookingwebapppApi.UtilityPackage.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException arg2) throws IOException {

        HttpCustomResponse httpCustomResponse =
                new HttpCustomResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN,
                        HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
                        SecurityConstant.FORBIDDEN_MESSAGE);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpCustomResponse);
        outputStream.flush();

    }

}
