package com.entities;

import java.time.LocalDateTime;

public class Vehicle {
	
	private int vehicleId;
	private VehicleType vehicleType;
	private LocalDateTime entryTime;
	
	public Vehicle(int vehicleId, VehicleType vehicleType) {
		this.vehicleId=vehicleId;
		this.vehicleType=vehicleType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	
	public void updateEntryTime() {
		entryTime = LocalDateTime.now();
	}
	
}
