package tujuh.suganda.json;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;

import tujuh.suganda.json.pojo.CompanyPojo;

public class StoredStringJsonToPojo {

	public static void main(String[] args) {
		String json = "{ \"company\":\"Rider Oke Company\",\"bos\":\"Rider\" ,\"employes\": { \"1\": { \"firstName\": \"Monica\", \"lastName\": \"Belluci\" }, \"2\": { \"firstName\": \"John\", \"lastName\": \"Smith\" }, \"3\": { \"firstName\": \"Monica\", \"lastName\": \"Belluci\" }, \"4\": { \"firstName\": \"John\", \"lastName\": \"Smith\" }, \"5\": { \"firstName\": \"Monica\", \"lastName\": \"Belluci\" }, \"6\": { \"firstName\": \"John\", \"lastName\": \"Smith\" } },\"childcompany\":[\"Tank Company\",\"Road Company\",\"Hoax Touch Company\"]}";
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			CompanyPojo company = mapper.readValue(json, CompanyPojo.class);
			System.out.println("company	: " + company.getBos());
			System.out.println("bos	: " + company.getCompany());
			System.out.println("child Company	: " + company.getChildcompany());

			Map<String, Map<String, String>> data = company.getEmployes();
			for (Entry<String, Map<String, String>> entry : data.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " | Value : " + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
