package com.parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.entities.CapacityConfig;
import com.entities.PriceConfig;
import com.entities.Vehicle;
import com.entities.VehicleType;
import com.exception.ParkingLotException;

public class Parking {

	private PriceConfig priceConfig;
	private CapacityConfig capacityConfig;
	private Map<VehicleType, Integer> currentVehicleCount;
	private Map<Integer, Vehicle> vehicles;
	
	public Parking(PriceConfig priceConfig, CapacityConfig capacityConfig) {
		this.priceConfig=priceConfig;
		this.capacityConfig=capacityConfig;
		currentVehicleCount=new HashMap<>();
		vehicles=new HashMap<>();
	}

	public synchronized void enterVehicle(Vehicle vehicle) throws Exception {
		validateVehicle(vehicle);
		currentVehicleCount.putIfAbsent(vehicle.getVehicleType(), 0);
		validateCapacity(vehicle);
		addVehicle(vehicle);
		updateVehicleEntryTime(vehicle);
	}
	
	public synchronized double exitVehicle(int vehicleId) throws Exception {
		validateVehicleDetails(vehicleId);
		double price=calculatePrice(vehicleId);
		removeVehicle(vehicleId);
		return price;
	}
	private void validateVehicle(Vehicle vehicle) throws Exception {
		if(vehicles.containsKey(vehicle.getVehicleId())) {
			throw new ParkingLotException("Vehicle already Parked");
		}
	}
	private void validateCapacity(Vehicle vehicle) throws Exception  {
		if(capacityConfig.getCapacity(vehicle.getVehicleType())<=currentVehicleCount.get(vehicle.getVehicleType())) {
			throw new ParkingLotException("Parking is full for Vehicle Type: "+vehicle.getVehicleType());
		}
	}
	private void addVehicle(Vehicle vehicle) {
		vehicles.put(vehicle.getVehicleId(), vehicle);
		currentVehicleCount.put(vehicle.getVehicleType(), currentVehicleCount.get(vehicle.getVehicleType())+1);
	}
	private void updateVehicleEntryTime(Vehicle vehicle) {
		vehicle.updateEntryTime();
	}
	private void validateVehicleDetails(int vehicleId) throws Exception {
		if(!vehicles.containsKey(vehicleId)) {
			throw new ParkingLotException("Vehicle not parked");
		}
	}
	private double calculatePrice(int vehicleId) {
		Vehicle vehicle=vehicles.get(vehicleId);
		double pricePerMinutes=priceConfig.getPrice(vehicle.getVehicleType());
		long seconds = ChronoUnit.SECONDS.between(vehicle.getEntryTime(), LocalDateTime.now());
		long hours = (seconds/60) + (seconds%60>0?1:0);
		return pricePerMinutes*hours;
	}
	private void removeVehicle(int vehicleId) {
		Vehicle vehicle = vehicles.get(vehicleId);
		currentVehicleCount.put(vehicle.getVehicleType(), currentVehicleCount.get(vehicle.getVehicleType())-1);
		vehicles.remove(vehicleId);
	}
}
