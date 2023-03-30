package com.bookingwebapppApi.RepositoryPackage;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingwebapppApi.ModelPackage.Booking;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
