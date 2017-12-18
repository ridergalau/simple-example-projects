package tujuh.suganda.example.pojo;

import java.util.ArrayList;
import java.util.List;

public class Lambda {
	public static void main(String[] args) {
		List<CarPojo> car = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			CarPojo carP = new CarPojo("carno" + i, 200 + i, 2010 + i);
			car.add(carP);
		}

		List<CarPojo> sCar = new ArrayList<>();
		
		car.stream().filter(s->s.getMaxSpeed()>=203).forEach(elem -> sCar.add(elem));
		
		
		for (CarPojo p : sCar) {
			System.out.println("name : "+p.getCar()+"\nMaxspeed : "+p.getMaxSpeed()+"\nTh : "+p.getTh());
		}

	}
}
