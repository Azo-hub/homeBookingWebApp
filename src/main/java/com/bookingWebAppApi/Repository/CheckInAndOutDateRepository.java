package com.bookingWebAppApi.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingWebAppApi.Model.CheckInAndOutDate;
import com.bookingWebAppApi.Model.Property;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CheckInAndOutDateRepository extends JpaRepository<CheckInAndOutDate, Long> {
    List<CheckInAndOutDate> findByCheckInDate(Date inViewCheckInDate);
    CheckInAndOutDate findByCheckOutDate(Date inViewCheckInDate);
    CheckInAndOutDate findByProperty(Property property);
	void deleteByProperty(Property property);
}
