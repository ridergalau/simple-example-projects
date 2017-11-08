package tujuh.suganda.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EncodeJson {
	public static void main(String[] args) {
		JSONObject headObj = new JSONObject();
		JSONObject objPrimary = new JSONObject();
		JSONArray child = new JSONArray();
		child.add("Rena");
		child.add("Reni");
		child.add("Roro");

		JSONObject obj1 = new JSONObject();
		obj1.put("mother", "Mother Rider");
		obj1.put("father", "Father Rider");
		obj1.put("child", child);

		JSONObject objAccount = new JSONObject();
		objAccount.put("facebook", "@Ridergalau");
		objAccount.put("twitter", "@RiderNotGalau");

		JSONArray array = new JSONArray();
		array.add("salsa");
		array.add("isabela");
		array.add("rona");
		array.add(87897);
		array.add(objAccount);

		objPrimary.put("firstname", "Rider");
		objPrimary.put("lastname", "galau");
		objPrimary.put("address", "Ciamis numb 7");
		objPrimary.put("email", "galaurider@mailX.com");
		objPrimary.put("friends", array);
		objPrimary.put("parents", obj1);

		headObj.put("data", objPrimary);

		System.out.println(headObj);

	}
}
