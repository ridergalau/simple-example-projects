package tujuh.suganda.model;

import java.io.Serializable;

public class ModelSimple implements Serializable{
private String name;
private String surename;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurename() {
	return surename;
}
public void setSurename(String surename) {
	this.surename = surename;
}
public ModelSimple(String name, String surename) {
	super();
	this.name = name;
	this.surename = surename;
}

}
