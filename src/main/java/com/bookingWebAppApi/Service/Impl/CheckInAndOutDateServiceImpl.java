package com.bookingWebAppApi.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingWebAppApi.Model.CheckInAndOutDate;
import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Repository.CheckInAndOutDateRepository;
import com.bookingWebAppApi.Service.CheckInAndOutDateService;

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
