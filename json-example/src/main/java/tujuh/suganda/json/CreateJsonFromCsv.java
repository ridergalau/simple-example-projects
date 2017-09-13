package tujuh.suganda.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import tujuh.suganda.json.pojo.MyModel;

public class CreateJsonFromCsv {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		String csv = "src/main/resources/exampledata.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<MyModel> allData = new ArrayList<MyModel>();

		try {
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				MyModel model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
						Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]), data[7], data[8],
						Integer.valueOf(data[9]), data[10], data[11]);
				allData.add(model);
			}
			String jsonString = mapper.writeValueAsString(allData);
			System.out.println(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
