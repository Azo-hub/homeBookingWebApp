package com.homeBookingWebApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Model.Review;


public interface ReviewRepository extends JpaRepository <Review, Long> {

	List<Review> findByProperty(Property property);

	void deleteByProperty(Property property);

}
