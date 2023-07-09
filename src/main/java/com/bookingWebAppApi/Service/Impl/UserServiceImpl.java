package com.bookingWebAppApi.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingWebAppApi.Exception.EmailExistException;
import com.bookingWebAppApi.Exception.UserNotFoundException;
import com.bookingWebAppApi.Exception.UsernameExistException;
import com.bookingWebAppApi.Model.Role;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Repository.PasswordResetTokenRepository;
import com.bookingWebAppApi.Repository.UserRepository;
import com.bookingWebAppApi.Service.UserService;
import com.bookingWebAppApi.Utility.PasswordResetToken;

@Service
public class UserServiceImpl implements UserService {
    public static final int MAX_FAILED_ATTEMPTS = 4;
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return (PasswordResetToken) passwordResetTokenRepository.findByToken(token);

    }

    @Override
    public void createPasswordResetTokenForUser(final Userr user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public Userr findByUsername(String username) {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(username);
    }

    @Override
    public Userr findByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }

    @Override
    public Userr createUser(String email, String username, String role)
            throws UsernameExistException, UserNotFoundException, EmailExistException {
    	/*Validate if user do not already exist */
        validateNewUsernameAndEmail("", username, email);

        
        /*Start creating a new user*/
        Userr user = new Userr();
        user.setUsername(username);
        user.setEmail(email);
        user.setAccountNonLocked(true);
        // user.setLockTime(null);
        user.setFailedAttempt((long) 0);

        user.setDateJoined(new Date());
        user.setAccountEnabled(true);
        
        if (!role.trim().isEmpty() && role.equals("ROLE_TRAVELLER_USER")) {

        	user.setRole(Role.ROLE_TRAVELLER_USER.name());
        	user.setAuthorities(Role.ROLE_TRAVELLER_USER.getAuthorities());

		}

		if (!role.trim().isEmpty() && role.equals("ROLE_OWNER_USER")) {
			user.setRole(Role.ROLE_OWNER_USER.name());
			user.setAuthorities(Role.ROLE_OWNER_USER.getAuthorities());

		}
		
		if (!role.trim().isEmpty() && role.equals("ROLE_ADMIN")) {
			user.setRole(Role.ROLE_ADMIN.name());
			user.setAuthorities(Role.ROLE_ADMIN.getAuthorities());

		}


        
        /* user.setImageUrl(imageUrl); */
        userRepository.save(user);
        user.setUserId("CDF-" + (10000 + user.getId()));
        userRepository.save(user);


        return user;

    }

    @Override
    public Userr save(Userr user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public void increaseFailedAttempt(Userr user) {
        long newFailedAttempts = user.getFailedAttempt() + 1;

        userRepository.updateFailedAttempt(newFailedAttempts, user.getUsername());

    }

    @Override
    public void lock(Userr user) {

        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        userRepository.save(user);

    }

    @Override
    public boolean unlock(Userr user) {

        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {

            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt((long) 0);

            userRepository.save(user);

            return true;
        }

        return false;

    }

    @Override
    public void resetFailedAttempts(String username) {

        userRepository.updateFailedAttempt(0, username);

    }

    @Override
    public Userr findUserById(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteUserById(Long userId) {
        // TODO Auto-generated method stub
        passwordResetTokenRepository.deleteByUser(userRepository.findUserById(userId));
        userRepository.deleteById(userId);


    }

    @Override
    public List<Userr> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
    
    
    
    @Override
    public List<Userr> searchUserByUsername(String searchInput) {

		List<Userr> userList = userRepository.findByUsernameContaining(searchInput);
		
		List<Userr> filteredUser = new ArrayList<>();
		
		for (Userr eachUser : userList) {
			if (!eachUser.getRole().equals("ROLE_ADMIN")) {

				filteredUser.add(eachUser);

			}
		}
		
		return filteredUser;

		
    }
    
    
    @Override
    public List<Userr> searchUserByEmail(String searchInput) {

		List<Userr> userList = userRepository.findByEmailContaining(searchInput);
		
		List<Userr> filteredUser = new ArrayList<>();
		
		for (Userr eachUser : userList) {
			if (!eachUser.getRole().equals("ROLE_ADMIN")) {

				filteredUser.add(eachUser);

			}
		}
		
		return filteredUser;

		
    }

    
    
    
    

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    private Userr validateNewUsernameAndEmail(String currentUsername, String newUsername, String email)
            throws UsernameExistException, UserNotFoundException, EmailExistException {

        Userr userByNewUsername = findByUsername(newUsername);
        Userr userByEmail = findByEmail(email);

        if (!currentUsername.trim().isEmpty()) {

            Userr localUser = findByUsername(currentUsername);

            if (localUser == null) {

                throw new UserNotFoundException("No user found by username " + currentUsername);
            }

            if (userByNewUsername != null && !localUser.getId().equals(userByNewUsername.getId())) {

                throw new UsernameExistException("Username already exists");

            }

            if (userByEmail != null && !localUser.getId().equals(userByEmail.getId())) {

                throw new EmailExistException("Email already exists");

            }

            return localUser;

        } else {

            if (!email.contains("@")) {

                throw new EmailExistException("Enter a valid email");
            }

            if (userByNewUsername != null) {

                throw new UsernameExistException("Username already exists");

            }

            if (userByEmail != null) {

                throw new EmailExistException("Email already exists");

            }

            return null;

        }
    }

}
