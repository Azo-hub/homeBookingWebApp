package com.bookingWebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingWebAppApi.Model.Booking;
import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Userr;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByLoginUser(Userr loginUserId);
	
	List<Booking> findByBookingFirstNameContaining(String searchInput);

	void deleteByProperty(Property property);
}
