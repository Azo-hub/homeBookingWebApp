package com.bookingWebAppApi.Service;

import com.bookingWebAppApi.Model.PaymentMethod;

public interface PaymentMethodService {
	void save(PaymentMethod paymentMethod);

	PaymentMethod getById(Long id);

}
