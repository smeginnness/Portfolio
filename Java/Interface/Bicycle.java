package sMeginness.HW07.objects;

import sMeginness.HW07.Interface.CarbonFootprint;

public class Bicycle implements CarbonFootprint{
	private String brand;
	private int tireSize;
	private double price;
	
	public Bicycle() {}
	public Bicycle(String brand, int tireSize, double price) {
		this.brand = brand;
		this.tireSize = tireSize;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getTireSize() {
		return tireSize;
	}
	public void setTireSize(int tireSize) {
		this.tireSize = tireSize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public double getCarbonFootprint() {
		return 0;
	}
	
}
