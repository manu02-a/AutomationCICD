package ManuAcademy.Testdata;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReaderclass {
	
	public String[] jsonReader() throws IOException, ParseException {
		//Parsing the file
		JSONParser parser = new JSONParser();
		
		FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\ManuAcademy\\Testdata\\PurchaseOrder.json");
		Object obj = parser.parse(reader);
		
		JSONObject jsonObject=(JSONObject)obj;
		JSONArray array = (JSONArray) jsonObject.get("Userlogins");
		
		String[] arr = new String[array.size()];
		for(int i=0; i<array.size(); i++) {
			JSONObject users = (JSONObject) array.get(i);
			String email = (String)users.get("email");
			String password = (String)users.get("password");
			String product = (String)users.get("product");
			
			arr[i]= email+","+password+","+product;
			
		}
		return arr;
		
	}

}
