
import java.util.Vector;

public class HW {
	private static final int NOTCHBACK = 0;
	private static final int HATCHBACK = 1;
	private static final int TRUCK = 2;
	private static final int CROSSOVER = 3;

	public static void main(String[] args) {
		Vehicle	v[] = new Vehicle[50];
		Vector<Vehicle> vi = new Vector<>();
		
		for (int i=0; i <v.length; i++) {
			v[i]= makeVehicle(); 
			vi.add(v[i]);
		}
		for (int i=0; i <v.length; i++) {
			vi.get(i).printInfo();
		}
	}
	static Vehicle makeVehicle() {
		Vehicle p = null;
		
		switch (Rand.r(NOTCHBACK, CROSSOVER)) {
		case NOTCHBACK:
			p = new Notchback();
			break;
		case HATCHBACK:
			p = new Hatchback();
			break;
		case TRUCK:
			p = new Truck();
			break;
		case CROSSOVER:
			p = new CrossOver();
			break;

		default:
			break;
		}
		
		return p;
	}

}
