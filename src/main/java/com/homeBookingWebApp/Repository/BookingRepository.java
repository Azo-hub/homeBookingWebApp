package com.homeBookingWebApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeBookingWebApp.Model.Booking;
import com.homeBookingWebApp.Model.Userr;

import jakarta.transaction.Transactional;

@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByLoginUser(Userr loginUserId);
	
	List<Booking> findByBookingFirstNameContaining(String searchInput);
}
