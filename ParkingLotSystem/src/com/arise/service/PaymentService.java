package com.arise.service;

import java.util.UUID;

import com.arise.adapter.PaymentGatewayAdapter;
import com.arise.adapter.RazorpayAdapter;
import com.arise.adapter.StripeAdapter;
import com.arise.domain.Payment;
import com.arise.domain.Payment.PaymentGateway;
import com.arise.repository.PaymentRepository;

public class PaymentService {

	private PaymentRepository paymentRepository;
	private PaymentGatewayAdapter defaultGateway;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
		this.defaultGateway = new RazorpayAdapter(); // Default gateway.
		System.out.println("[SERVICE] PaymentService initialized with default gatway: Razorpay");
	}

	public boolean processPayment(UUID ticketId,double amount) {
		System.out.println("[SERVICE] Processing payment for ticket: " + ticketId + " amount: " + amount);
		Payment payment = new Payment(ticketId,amount,PaymentGateway.RAZORPAY);
		paymentRepository.save(payment);

		boolean success = defaultGateway.pay(ticketId, amount);

		if(success) payment.markAsSuccess();
		else payment.markAsFailed();

		paymentRepository.update(payment);
		System.out.println("[SERVICE] Payment processed with status: " + (success ? "SUCCESS" : "FAILED"));

		return success;
	}

	public boolean processPaymentWithRetry(UUID ticketId,double amount,int maxRetries) {
		System.out.println("[SERVICE] Processing payment with retry for ticket: " + ticketId);

		for(int attempt = 1;attempt <= maxRetries;attempt++) {
			System.out.println("[SERVICE] Payment attempt "+attempt+" of "+maxRetries);

			boolean success = processPayment(ticketId,amount);
			if(success) {
				System.out.println("[SERVICE] Payment successful on attempt "+attempt);
				return true;
			}

			// Try different gateway on retry.
			if(attempt > 1) {
				// SingleTon pattern can be used here to avoid creating multiple instances of the same gateway.
				defaultGateway = new StripeAdapter();
				System.out.println("[SERVICE] Switching to Stripe gateway for retry");
			}
		}

		System.out.println("[SERVICE] Payment failed after " + maxRetries + " attempts");
		return false;

	}

	public void setDefaultGateway(PaymentGatewayAdapter gateway) {
		this.defaultGateway = gateway;
	}

}
