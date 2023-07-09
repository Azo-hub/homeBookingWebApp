package com.bookingWebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingWebAppApi.Model.PaymentMethod;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
