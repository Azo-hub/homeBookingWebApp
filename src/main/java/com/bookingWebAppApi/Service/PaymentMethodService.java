package com.bookingWebAppApi.Service;

import java.util.List;

import com.bookingWebAppApi.Model.PaymentMethod;

public interface PaymentMethodService {
	void save(PaymentMethod paymentMethod);

	PaymentMethod getById(Long id);

	List<PaymentMethod> getAllPaymentMethod();

	void removeCard(Long id);

}
