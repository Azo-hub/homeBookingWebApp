package com.bookingwebapppApi.ServicePackage;

import java.util.List;

import com.bookingwebapppApi.ExceptionPackage.EmailExistException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
import com.bookingwebapppApi.ExceptionPackage.UsernameExistException;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.UtilityPackage.PasswordResetToken;

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
