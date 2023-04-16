package com.bookingwebapppApi.RepositoryPackage;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingwebapppApi.ModelPackage.Booking;
import com.bookingwebapppApi.ModelPackage.Userr;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByLoginUser(Userr loginUserId);
	
	List<Booking> findByBookingFirstNameContaining(String searchInput);
}
