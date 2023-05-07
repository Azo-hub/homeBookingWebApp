package com.bookingwebapppApi.ServicePackage;

import java.util.List;

import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.ModelPackage.Property;

public interface CheckInAndOutDateService {
    List<CheckInAndOutDate> findAll();

    void save(CheckInAndOutDate checkInAndOutDate);

	void deleteByProperty(Property property);

}
