package tujuh.suganda.model;

public class SimpleModel {
public String id;
public String firstname;
public String lastname;
public String address;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public SimpleModel(String id, String firstname, String lastname, String address) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.address = address;
}

}
