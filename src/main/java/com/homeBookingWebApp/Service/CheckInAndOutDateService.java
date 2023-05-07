package com.homeBookingWebApp.Service;

import java.util.List;

import com.homeBookingWebApp.Model.CheckInAndOutDate;
import com.homeBookingWebApp.Model.Property;

public interface CheckInAndOutDateService {
    List<CheckInAndOutDate> findAll();

    void save(CheckInAndOutDate checkInAndOutDate);

	void deleteByProperty(Property property);

}
