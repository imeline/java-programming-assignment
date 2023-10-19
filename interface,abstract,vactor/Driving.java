
interface Driving {
	int		wheel_num = 4;
	String brake_is = "있음";
	
}
interface PowerSource {
	String	fuelType();
}
interface MovingPower {
	String force();
}
interface Feature {
	int loadCapacity();
}