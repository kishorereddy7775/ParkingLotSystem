package com.entities;

import java.util.Map;

public record PriceConfig(Map<VehicleType, Double> priceDetails) {
	
	public Double getPrice(VehicleType type) {
		return priceDetails.get(type);
	}
}
