package com.bookingwebapppApi.ServicePackage;

import java.util.List;

import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ModelPackage.Review;

public interface ReviewService {

	void save(Review review);

	List<Review> getReviewByProperty(Property property);

	void createReview(String reviewContent, String reviewAuthor, String reviewLocation, Property property);

	void deleteByProperty(Property property);

}
