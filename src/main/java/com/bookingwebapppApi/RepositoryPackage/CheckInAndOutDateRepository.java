package com.bookingwebapppApi.RepositoryPackage;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.ModelPackage.Property;

@Repository
@Transactional
public interface CheckInAndOutDateRepository extends JpaRepository<CheckInAndOutDate, Long> {
    List<CheckInAndOutDate> findByCheckInDate(Date inViewCheckInDate);
    CheckInAndOutDate findByCheckOutDate(Date inViewCheckInDate);
    CheckInAndOutDate findByProperty(Property property);
}
