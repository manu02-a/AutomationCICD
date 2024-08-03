package ManuAcademy.Testdata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	/*public List<HashMap<String, String>> getJsonDataToMap() {
		
		//read json to string
		String jsonContent = Files.readString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\ManuAcademy\\Testdata\\PurchaseOrder.json"), StandardCharsets.UTF_8);	
		
		
		string to hashmap - jackson databind
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String,String>> data = map.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}*/
	
	
	

}
