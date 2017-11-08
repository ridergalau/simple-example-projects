package tujuh.suganda.json.pojo;

import java.util.List;
import java.util.Map;

public class CompanyPojo {

	private Map<String, Map<String, String>> employes;
	private String company;
	private String bos;
	private String address;
	private List<String> childcompany;
	
	public List<String> getChildcompany() {
		return childcompany;
	}
	public void setChildcompany(List<String> childcompany) {
		this.childcompany = childcompany;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, Map<String, String>> getEmployes() {
		return employes;
	}
	public void setEmployes(Map<String, Map<String, String>> employes) {
		this.employes = employes;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBos() {
		return bos;
	}
	public void setBos(String bos) {
		this.bos = bos;
	}

}
