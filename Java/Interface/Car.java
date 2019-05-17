package sMeginness.HW07.objects;

import sMeginness.HW07.Interface.CarbonFootprint;

public class Car implements CarbonFootprint{
	private double mpg;
	private int weeklyMilesDriven;
	private double CO2perGallon = 19.4;
	
	public Car(double mpg, int weeklyMilesDriven, double cO2perGallon) {
		this.mpg = mpg;
		this.weeklyMilesDriven = weeklyMilesDriven;
		CO2perGallon = cO2perGallon;
	}

	@Override
	public double getCarbonFootprint() {
		return ((weeklyMilesDriven * 52) / mpg) * CO2perGallon;
	}
	
}
