package com.bookingwebapppApi.RepositoryPackage;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingwebapppApi.ModelPackage.Property;

@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
	List<Property> findByPropertyType(String PropertyType);

	List<Property> findByCreatedBy(String propertyOwner);
}
