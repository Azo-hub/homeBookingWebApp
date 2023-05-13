package com.homeBookingWebApp.Service;

import java.util.List;

import com.homeBookingWebApp.Exception.EmailExistException;
import com.homeBookingWebApp.Exception.UserNotFoundException;
import com.homeBookingWebApp.Exception.UsernameExistException;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Utility.PasswordResetToken;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final Userr user, final String token);

    Userr findByUsername(String username);

    Userr findByEmail(String email);

    List<Userr> getAllUsers();

    Userr save(Userr user);

    void increaseFailedAttempt(Userr user);

    void lock(Userr user);

    boolean unlock(Userr user);

    void resetFailedAttempts(String username);

    Userr findUserById(Long id);



    Userr createUser(String email, String username, String role)
            throws UsernameExistException, UserNotFoundException, EmailExistException;

    void deleteUserById(Long userId);

	List<Userr> searchUserByUsername(String searchInput);

	List<Userr> searchUserByEmail(String searchInput);


}
