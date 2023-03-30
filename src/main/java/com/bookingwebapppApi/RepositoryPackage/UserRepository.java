package com.bookingwebapppApi.RepositoryPackage;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookingwebapppApi.ModelPackage.Userr;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Userr, Long> {
    Userr findByUsername(String username);

    Userr findByEmail(String email);

    Userr findUserById(Long id);


    @Transactional
    @Query("UPDATE Userr u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempt(long failedAttempt, String username);

}
