package com.bookingWebAppApi.Service;

import java.util.List;

import com.bookingWebAppApi.Exception.EmailExistException;
import com.bookingWebAppApi.Exception.UserNotFoundException;
import com.bookingWebAppApi.Exception.UsernameExistException;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Utility.PasswordResetToken;

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

    /*User getOne(Long id);*/

    Userr findUserById(Long id);



    Userr createUser(String email, String username, String role)
            throws UsernameExistException, UserNotFoundException, EmailExistException;

    void deleteUserById(Long userId);

	List<Userr> searchUserByUsername(String searchInput);

	List<Userr> searchUserByEmail(String searchInput);


}
