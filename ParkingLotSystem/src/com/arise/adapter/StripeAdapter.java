package com.arise.adapter;

import java.util.Random;
import java.util.UUID;

public class StripeAdapter implements PaymentGatewayAdapter{

	@Override
	public boolean pay(UUID ticketId, double amount) {

		// simulate payment processing
		System.out.println("[ADAPTER] StripeAdapter processing payment for ticket: " + ticketId + " amount: " + amount);

		// simulate 95% success rate.
		Random random = new Random();
		boolean success = random.nextDouble()<0.95;

		System.out.println("[ADAPTER] StripeAdapter payment result: " + (success ? "SUCCESS" : "FAILED"));
		return success;
	}

}
