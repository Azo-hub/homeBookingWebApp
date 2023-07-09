package com.bookingWebAppApi.Service;

import java.util.List;

import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Review;

public interface ReviewService {

	void save(Review review);

	List<Review> getReviewByProperty(Property property);

	void createReview(String reviewContent, String reviewAuthor, String reviewLocation, Property property);

	void deleteByProperty(Property property);

}
