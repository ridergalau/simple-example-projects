package tujuh.suganda.json.pojo;

import java.io.Serializable;

public class MyModel implements Serializable{
	
	private String _id;
	private String street;
	private String city;
	private int zip; 
	private String state;
	private int beds;
	private int baths;
	private int sq__ft;
	private String type;
	private String sale_date;
	private int price;
	private String latitude;
	private String longitude;

	
	
	
	public MyModel(String _id, String street, String city, int zip, String state, int beds, int baths, int sq__ft,
			String type, String sale_date, int price, String latitude, String longitude) {
		super();
		this._id = _id;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.beds = beds;
		this.baths = baths;
		this.sq__ft = sq__ft;
		this.type = type;
		this.sale_date = sale_date;
		this.price = price;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getBaths() {
		return baths;
	}
	public void setBaths(int baths) {
		this.baths = baths;
	}
	public int getSq__ft() {
		return sq__ft;
	}
	public void setSq__ft(int sq__ft) {
		this.sq__ft = sq__ft;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	

	
}