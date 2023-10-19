
public abstract class Vehicle {
	int		engine_num;
	String  	cartype;
	
	public Vehicle() {
		engine_num = 1; cartype = null;
	}
	public Vehicle(String c, int e) {
		cartype = c; engine_num = e;
	}
	public String toString() {
		return String.format("[%s] (엔진: %d개) -",cartype,engine_num);
	}
	public abstract String whatType();
	public abstract String whatSize();
	public abstract void printInfo() ;
	
	public String getType() {
		if(this instanceof Notchback) return "승용차";
		else if(this instanceof Hatchback) return "승용차";
		else return "SUV";
		
	}

}
class Sedan extends Vehicle implements PowerSource {
	String	 car_type;
	int	 see_range;
	
	public Sedan() {
		super("승용차", 1);
		car_type = "일반승용차"; see_range = 0;		
	}
	@Override
	public String fuelType() {
		return "가솔린";
	}

	@Override
	public String whatType() {
		return car_type;
	}
	@Override
	public String whatSize() {
		return "보통";
	}
	public String getSeeRange() {
		if(see_range == 0) return "보통";
		else return "높음";	
	}
	public String toString() {
		return String.format("%s [%s - 시야 높이: %s] ",super.toString(),car_type,getSeeRange());
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}
}
class Notchback extends Vehicle implements MovingPower {
	String 		popular;
	boolean		back_pop;
	
	public Notchback() {
		super("노치백", 1);
		popular = "높음"; back_pop = true;
	}
	@Override
	public String force() {
		return "엔진"; 
	}

	@Override
	public String whatType() {
		return "일반승용차";
	}

	@Override
	public String whatSize() {
		return "보통";
	}
	public String getBackPop() {
		if(back_pop == true) return "튀어나옴";
		else return "평평함";	
	}
	public String toString() {
		return String.format("%s 대중성: %s, 뒤통수: %s ",super.toString(),popular,getBackPop());
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}
	
}
class Hatchback extends Vehicle implements Feature {
	String 		back;
	boolean		trunk_separation;
	
	public Hatchback() {
		super("해치백", 1);
		back = "평평함"; trunk_separation = false;
	}
	public String gettrunk() {
		if(trunk_separation == false) return "x";
		else return "o";	
	}
	public String toString() {
		return String.format("%s 뒤통수: %s, 트렁크 분리: %s ",super.toString(),back,gettrunk());
	}
	@Override
	public int loadCapacity() {
		return 400;
	}
	@Override
	public String whatType() {
		return "일반승용차";
	}
	@Override
	public String whatSize() {
		return "보통";
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}
	
}
class SUV extends Vehicle implements Driving {
	int	car_high;
	String 	safe;
	
	public SUV() {
		super("suv", 1);
		car_high = 1; safe = "높음";
	}

	@Override
	public String whatType() {
		return "suv";
	}

	@Override
	public String whatSize() {
		return "큼";
	}
	public String getCarhigh() {
		if(car_high == 1) return "높음";
		else return "보통";	
	}
	public String toString() {
		return String.format("%s [%s - 차 높이: %s, 안전성: %s] ",super.toString(),whatType(),getCarhigh(),safe);
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}
}
class Truck extends Vehicle implements PowerSource {
	String color;
	boolean back_seat;
	
	public Truck() {
		super("트럭", 1);
		color = "파랑"; back_seat = false;
	}

	@Override
	public String fuelType() {
		return "디젤";
	}

	@Override
	public String whatType() {
		return "트럭";
	}

	@Override
	public String whatSize() {
		return "큼";
	}
	
	public String isBackSeat() {
		if(back_seat == false) return "없음";
		else return "있음";	
	}
	public String toString() {
		return String.format("%s 색깔: %s, 뒷자리: %s ",super.toString(),color,isBackSeat());
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}
	
}
class CrossOver extends Vehicle implements MovingPower{
	boolean forstorts;
	String  utility;
	
	public CrossOver() {
		super("일반suv", 1);
		forstorts = true; utility= "o";
	}
	
	@Override
	public String force() {
		return "엔진";
	}
	@Override
	public String whatType() {
		return "SUV";
	}
	@Override
	public String whatSize() {
		return "큼";
	}
	public String isforstorts() {
		if(forstorts == true) return "가능";
		else return "불가능";	
	}
	public String toString() {
		return String.format("%s 스포츠에 사용: %s, 다목적용도: %s ",super.toString(),isforstorts(),utility);
	}
	public void printInfo() {
		System.out.println("<"+getType()+">"+this);
	}

}
	



