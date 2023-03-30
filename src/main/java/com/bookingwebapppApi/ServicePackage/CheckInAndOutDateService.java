package com.bookingwebapppApi.ServicePackage;

import java.util.List;

import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;

public interface CheckInAndOutDateService {
    List<CheckInAndOutDate> findAll();

    void save(CheckInAndOutDate checkInAndOutDate);

}
