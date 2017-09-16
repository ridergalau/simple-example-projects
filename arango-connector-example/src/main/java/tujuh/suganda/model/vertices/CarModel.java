package tujuh.suganda.model.vertices;

import java.math.BigInteger;

import com.arangodb.entity.DocumentField;
import com.arangodb.velocypack.annotations.SerializedName;

public class CarModel {
	private String _key;
	private String merk;
	private String no_car;
	private String color;
	private String manufacture;
	private String category;
//	private BigInteger timestamp;

	  public CarModel() {
	      super();
	    }
	  
	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public String getNo_car() {
		return no_car;
	}

	public void setNo_car(String no_car) {
		this.no_car = no_car;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

//	public BigInteger getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(BigInteger timestamp) {
//		this.timestamp = timestamp;
//	}

	public CarModel(String _key, String merk, String no_car, String color, String manufacture, String category) {
		super();
		this._key = _key;
		this.merk = merk;
		this.no_car = no_car;
		this.color = color;
		this.manufacture = manufacture;
		this.category = category;
	}


}
