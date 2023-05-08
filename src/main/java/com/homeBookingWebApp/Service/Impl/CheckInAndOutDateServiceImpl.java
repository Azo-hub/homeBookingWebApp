package com.homeBookingWebApp.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeBookingWebApp.Model.CheckInAndOutDate;
import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Repository.CheckInAndOutDateRepository;
import com.homeBookingWebApp.Service.CheckInAndOutDateService;

import jakarta.transaction.Transactional;

@Service
@Transactional
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

	@Override
	public void deleteByProperty(Property property) {
		// TODO Auto-generated method stub
		checkInAndOutDateRepository.deleteByProperty(property);
		
	}

}
