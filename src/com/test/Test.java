package com.test;

import java.util.HashMap;
import java.util.Map;

import com.entities.CapacityConfig;
import com.entities.PriceConfig;
import com.entities.Vehicle;
import com.entities.VehicleType;
import com.parking.Parking;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Map<VehicleType, Double> priceDetails=new HashMap<>();
		Map<VehicleType, Integer> capacityDetails=new HashMap<>();
		
		priceDetails.put(VehicleType.BIKE, 30.0);
		priceDetails.put(VehicleType.AUTO, 50.0);
		priceDetails.put(VehicleType.CAR, 80.0);
		priceDetails.put(VehicleType.TRUCK, 100.0);
		
		capacityDetails.put(VehicleType.BIKE, 5);
		capacityDetails.put(VehicleType.AUTO, 5);
		capacityDetails.put(VehicleType.CAR, 3);
		capacityDetails.put(VehicleType.TRUCK, 1);
		
		Parking parking=new Parking(new PriceConfig(priceDetails), new CapacityConfig(capacityDetails));
		
		parking.enterVehicle(new Vehicle(1,VehicleType.BIKE));
		try {
			parking.enterVehicle(new Vehicle(1,VehicleType.BIKE));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		parking.enterVehicle(new Vehicle(2,VehicleType.TRUCK));
		try {
			parking.enterVehicle(new Vehicle(3,VehicleType.TRUCK));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(61000);
		System.out.println(parking.exitVehicle(2));
		parking.enterVehicle(new Vehicle(3,VehicleType.TRUCK));
	}

}
