package com.homeBookingWebApp.Service;

import java.util.List;

import com.homeBookingWebApp.Model.Property;

public interface PropertyService {
    Property createProperty(Property property);

    void deletePropertyById(Long id);

    Property findById(Long propertyId);

    List<Property> findByPropertyType(String propertyType);

    void save(Property newProperty);

    List<Property> findAll();

	List<Property> findByPropertyOwner(String propertyOwner);

}
