package com.homeBookingWebApp.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.RepositoryPackage.CheckInAndOutDateRepository;
import com.bookingwebapppApi.ServicePackage.CheckInAndOutDateService;

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
