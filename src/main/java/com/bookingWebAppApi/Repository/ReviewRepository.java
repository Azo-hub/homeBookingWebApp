package com.bookingWebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Review;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository <Review, Long> {

	List<Review> findByProperty(Property property);

	void deleteByProperty(Property property);

}
