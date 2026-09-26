package com.entities;

import java.util.Map;

public record CapacityConfig(Map<VehicleType, Integer> capacityDetails) {
	
	public int getCapacity(VehicleType type) {
		return capacityDetails.get(type);
	}
}
