package com.bookingwebapppApi.ServicePackage.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ModelPackage.Review;
import com.bookingwebapppApi.RepositoryPackage.ReviewRepository;
import com.bookingwebapppApi.ServicePackage.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void save(Review review) {
		
		reviewRepository.save(review);
	}
	
	@Override
	public List<Review> getReviewByProperty (Property property) {
		
		return reviewRepository.findByProperty(property);
	}
	
	@Override
	public void createReview(String reviewContent, String reviewAuthor, String reviewLocation, Property property) {
		
		Review review = new Review();
		review.setReviewContent(reviewContent);
		review.setReviewAuthor(reviewAuthor);
		review.setReviewLocation(reviewLocation);
		review.setProperty(property);
		reviewRepository.save(review);
		
		
	}

}
