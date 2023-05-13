package com.homeBookingWebApp.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Model.Review;
import com.homeBookingWebApp.Repository.ReviewRepository;
import com.homeBookingWebApp.Service.ReviewService;

import jakarta.transaction.Transactional;

@Service
@Transactional
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

	@Override
	public void deleteByProperty(Property property) {
		// TODO Auto-generated method stub
		reviewRepository.deleteByProperty(property);
		
	}

}
