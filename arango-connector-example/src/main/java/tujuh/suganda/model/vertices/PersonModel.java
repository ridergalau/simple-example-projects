package tujuh.suganda.model.vertices;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.arangodb.entity.DocumentField;

public class PersonModel implements Serializable {
	@DocumentField(DocumentField.Type.KEY)
	private String _key;

	private String firstname;
	private String lastname;
	private Map<String, String> address;
	private List<String> hobies;
	private BigInteger timestamp;
	private String gender;
	private String email;

	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	public List<String> getHobies() {
		return hobies;
	}

	public void setHobies(List<String> hobies) {
		this.hobies = hobies;
	}

	public BigInteger getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonModel(String _key, String firstname, String lastname, Map<String, String> address, List<String> hobies,
			BigInteger timestamp, String gender, String email) {
		super();
		this._key = _key;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.hobies = hobies;
		this.timestamp = timestamp;
		this.gender = gender;
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonModel [_key=" + _key + ", firstname=" + firstname + ", lastname=" + lastname + ", address="
				+ address + ", hobies=" + hobies + ", timestamp=" + timestamp + ", gender=" + gender + ", email="
				+ email + "]";
	}

}
