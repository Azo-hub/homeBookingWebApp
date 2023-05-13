package com.homeBookingWebApp.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeBookingWebApp.Model.CheckInAndOutDate;
import com.homeBookingWebApp.Model.Property;

import jakarta.transaction.Transactional;


@Transactional
public interface CheckInAndOutDateRepository extends JpaRepository<CheckInAndOutDate, Long> {
    List<CheckInAndOutDate> findByCheckInDate(Date inViewCheckInDate);
    CheckInAndOutDate findByCheckOutDate(Date inViewCheckInDate);
    CheckInAndOutDate findByProperty(Property property);
	void deleteByProperty(Property property);
}
