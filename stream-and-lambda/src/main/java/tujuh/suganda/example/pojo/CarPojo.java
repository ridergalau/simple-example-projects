package tujuh.suganda.example.pojo;

public class CarPojo {
public String car;
public int maxSpeed;
public int th;
public String getCar() {
	return car;
}
public void setCar(String car) {
	this.car = car;
}
public int getMaxSpeed() {
	return maxSpeed;
}
public void setMaxSpeed(int maxSpeed) {
	this.maxSpeed = maxSpeed;
}
public int getTh() {
	return th;
}
public void setTh(int th) {
	this.th = th;
}
public CarPojo(String car, int maxSpeed, int th) {
	super();
	this.car = car;
	this.maxSpeed = maxSpeed;
	this.th = th;
}

}
