package com.homeBookingWebApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.homeBookingWebApp.Model.Userr;

import jakarta.transaction.Transactional;



@Transactional
public interface UserRepository extends JpaRepository<Userr, Long> {
    Userr findByUsername(String username);

    Userr findByEmail(String email);

    Userr findUserById(Long id);


    @Transactional
    @Query("UPDATE Userr u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempt(long failedAttempt, String username);

	List<Userr> findByUsernameContaining(String searchInput);

	List<Userr> findByEmailContaining(String searchInput);

}
