package com.homeBookingWebApp.Service;

import java.util.List;

import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Model.Review;

public interface ReviewService {

	void save(Review review);

	List<Review> getReviewByProperty(Property property);

	void createReview(String reviewContent, String reviewAuthor, String reviewLocation, Property property);

	void deleteByProperty(Property property);

}
