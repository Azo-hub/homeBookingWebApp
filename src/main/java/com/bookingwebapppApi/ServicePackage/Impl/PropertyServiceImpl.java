package com.bookingwebapppApi.ServicePackage.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.RepositoryPackage.PropertyRepository;
import com.bookingwebapppApi.ServicePackage.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    
    
    @Override
    public List<Property> findByPropertyOwner (String propertyOwner) {
    	
    	return propertyRepository.findByCreatedBy(propertyOwner);
    }


    @Override
    public Property createProperty(Property property) {
        // TODO Auto-generated method stub

        propertyRepository.save(property);

        return property;
    }

    @Override
    public void deletePropertyById(Long id) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("deprecation")
    @Override
    public Property findById(Long propertyId) {
        // TODO Auto-generated method stub
       // return propertyRepository.getById(propertyId);
    	return propertyRepository.getOne(propertyId);
    }

    @Override
    public List<Property> findByPropertyType(String propertyType) {
        // TODO Auto-generated method stub
        return propertyRepository.findByPropertyType(propertyType);

    }

    @Override
    public void save(Property newProperty) {
        // TODO Auto-generated method stub
        propertyRepository.save(newProperty);

    }

    @Override
    public List<Property> findAll() {
        // TODO Auto-generated method stub
        return propertyRepository.findAll();
    }

}
