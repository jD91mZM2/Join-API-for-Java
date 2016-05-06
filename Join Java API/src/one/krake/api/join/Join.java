package one.krake.api.join;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Join{
	protected static String makeRequest(String sURL) throws IOException{
		String response = "";
		
		URL url = new URL(sURL);
		Scanner scan = new Scanner(url.openStream());
		
		while(scan.hasNextLine())
			response += "\n" + scan.nextLine();
		
		scan.close();
		
		if(response.startsWith("\n"))
			response = response.substring(1);
		return response;
	}
	
	private String apiKey;
	public Join(String apiKey){
		this.apiKey = apiKey;
	}
	
	/**
	 * Get all devices.
	 * @param apiKey The API Key for the account.
	 * @return An {@link ArrayList} of {@link Device}s.
	 * @throws IOException If something went wrong, such as bad internet connection et.c.
	 */
	public ArrayList<Device> getDevices() throws IOException{
		String response = makeRequest("https://joinjoaomgcd.appspot.com/_ah/api/registration/v1/listDevices?apikey="
								+ apiKey);
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(response, JsonElement.class).getAsJsonObject();
		if(!obj.get("success").getAsBoolean())
			throw new JoinException(obj);
		
		ArrayList<Device> devices = new ArrayList<>();

		JsonArray records = obj.getAsJsonArray("records");
		for(JsonElement e : records)
			devices.add(new Device(e));
			
		return devices;
	}
}
