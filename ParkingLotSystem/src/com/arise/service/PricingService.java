package com.arise.service;

import java.util.Optional;

import com.arise.domain.PricingRule;
import com.arise.domain.Ticket;
import com.arise.domain.Vehicle;
import com.arise.repository.PricingRuleRepository;

public class PricingService {

	private PricingRuleRepository pricingRuleRepository;

	public PricingService(PricingRuleRepository pricingRuleRepository) {
		this.pricingRuleRepository = pricingRuleRepository;
	}

	public double calculateFee(Ticket ticket) {
		System.out.println("[SERVICE] Calculating fee for ticket: " + ticket.getId());

		// For demo purposes, we'll use a dummy vehicle type
		// In a real system, you'd get the vehicle from the ticket
		Vehicle.VehicleType vehicleType = Vehicle.VehicleType.CAR;
		Optional<PricingRule> rule = pricingRuleRepository.findbyVehicleType(vehicleType);

		if(rule.isEmpty()) {
			throw new IllegalStateException("No pricing rule found for vehicle type: " + vehicleType);
		}

		PricingRule pricingRule = rule.get();

		// calculate both flat and hourly fees.
		double flatFee = pricingRule.getFlatRate();
		double hourlyFee = calculateHourlyFee(ticket,pricingRule.getRatePerHour());

		// return the minimum of flat and hourly pricing.
		double finalFee = Math.min(flatFee, hourlyFee);

		System.out.println("[SERVICE] Flat fee: " + flatFee + ", Hourly fee: " + hourlyFee + ", Final fee: " + finalFee + " for vehicle type: " + vehicleType);
		return finalFee;
	}
	
	private double calculateHourlyFee(Ticket ticket,double ratePerHour) {
		java.time.Duration duration = java.time.Duration.between(ticket.getEntryTime(), java.time.LocalDateTime.now());
		long hours = duration.toHours();
		
		// minimum 1 hour charge.
		if(hours < 1) {
			hours = 1;
		}
		
		return hours * ratePerHour;
	}
	
	public void addPricingRule(PricingRule rule) {
		pricingRuleRepository.save(rule);
	}
	
	public void updatePricingRule(PricingRule rule) {
		pricingRuleRepository.update(rule);
	}

}
