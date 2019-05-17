package sMeginness.HW07.objects;

import sMeginness.HW07.Interface.CarbonFootprint;

public class Building implements CarbonFootprint{
	private double electricBill;
	private double kwhPrice;
	private double elecEmissFactor = 1.37;
	
	public Building(double electricBill, double kwhPrice, double elecEmissFactor) {
		this.electricBill = electricBill;
		this.kwhPrice = kwhPrice;
		this.elecEmissFactor = elecEmissFactor;
	}

	@Override
	public double getCarbonFootprint() {
		return (elecEmissFactor * electricBill / kwhPrice)*12;
	}
}
