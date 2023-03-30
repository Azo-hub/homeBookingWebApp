package com.bookingwebapppApi.ServicePackage.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.RepositoryPackage.CheckInAndOutDateRepository;
import com.bookingwebapppApi.ServicePackage.CheckInAndOutDateService;

@Service
public class CheckInAndOutDateServiceImpl implements CheckInAndOutDateService {
    @Autowired
    private CheckInAndOutDateRepository checkInAndOutDateRepository;

    @Override
    public List<CheckInAndOutDate> findAll() {
        // TODO Auto-generated method stub
        return checkInAndOutDateRepository.findAll();
    }

    @Override
    public void save(CheckInAndOutDate checkInAndOutDate) {
        // TODO Auto-generated method stub
        checkInAndOutDateRepository.save(checkInAndOutDate);

    }

}
