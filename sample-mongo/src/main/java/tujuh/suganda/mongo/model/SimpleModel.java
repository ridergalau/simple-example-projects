package tujuh.suganda.mongo.model;

import java.util.List;
import java.util.Map;

public class SimpleModel {
	private String _id;

	private String name;
	private int age;
	private List<String> parent;
	private Map<String,String> addres;
	private Map<String,List<String>> otherdata;
	
	private Map<String,Map<String,String>> otherdata2;
	
	
	
	public Map<String, Map<String, String>> getOtherdata2() {
		return otherdata2;
	}
	public void setOtherdata2(Map<String, Map<String, String>> otherdata2) {
		this.otherdata2 = otherdata2;
	}
	public Map<String, List<String>> getOtherdata() {
		return otherdata;
	}
	public void setOtherdata(Map<String, List<String>> otherdata) {
		this.otherdata = otherdata;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getParent() {
		return parent;
	}
	public void setParent(List<String> parent) {
		this.parent = parent;
	}
	public Map<String, String> getAddres() {
		return addres;
	}
	public void setAddres(Map<String, String> addres) {
		this.addres = addres;
	}
	
	
}
