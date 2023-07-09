package com.bookingWebAppApi.Service;

import java.util.List;

import com.bookingWebAppApi.Model.CheckInAndOutDate;
import com.bookingWebAppApi.Model.Property;

public interface CheckInAndOutDateService {
    List<CheckInAndOutDate> findAll();

    void save(CheckInAndOutDate checkInAndOutDate);

	void deleteByProperty(Property property);

}
