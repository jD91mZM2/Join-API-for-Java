package one.krake.api.join;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JoinPush{
	private static final Parameter senderId = Parameter.forName("senderId");
	private static final Parameter apiKey = Parameter.forName("apikey");
	
	private String id = null;
	private HashMap<Parameter, String> var = new HashMap<>();
	
	/**
	 * Create a new Join message!
	 * @param recipient The recipient device.
	 */
	public JoinPush(String recipient){
		id = recipient;
	}
	
	/**
	 * Create a new Join message to a group of recipients.
	 * @param apiKey To use groups, you need to enter the API Key.
	 * @param group The group you want to use. For example {@link Group#ALL}
	 */
	public JoinPush(String apiKey, Group group){
		this(group.getName());
		set(JoinPush.apiKey, apiKey);
	}
	
	/**
	 * Set the Sender ID. This allows stuff like location to respond to your computer.
	 * @param senderId The sender ID for the device that will get the response.
	 * @return The instance, useful for cleaner code.
	 */
	public JoinPush setSenderId(String senderId){
		set(JoinPush.senderId, senderId);
		return this;
	}
	
	
	/**
	 * Add some information to your request.
	 * JoinPush#set(Parameter.CLIPBOARD, "Hellu")
	 * will change your devices clipboard to Hellu once you send it.
	 * @param par The parameter. For example {@link Parameter#TITLE}
	 * @param value The value, for example Hello!
	 * @return The instance, useful for cleaner code.
	 */
	public JoinPush set(Parameter par, String value){
		var.put(par, value);
		return this;
	}
	
	/**
	 * Will do the same thing as {@link JoinPush#set(Parameter, String)},
	 * but the String will automatically be true.
	 * Used for things without a value, such as {@link Parameter#FIND} and {@link Parameter#LOCATION}
	 * @param par The parameter. For example Parameter.TITLE
	 * @return The instance, useful for cleaner code.
	 */
	public JoinPush set(Parameter par){
		return set(par, "true");
	}
	
	/**
	 * Sends the push.
	 * @throws IOException If something went wrong, such as bad internet connection et.c.
	 * @return The Json that was responded.
	 */
	public JsonObject send() throws IOException{
		String UTF_8 = "UTF-8";
		
		StringBuilder url = new StringBuilder("https://joinjoaomgcd.appspot.com/_ah/api/messaging/v1/sendPush");
		url.append("?deviceId=" + URLEncoder.encode(id, UTF_8));
		for(Parameter key : var.keySet())
			url.append("&" + key.toString() + "=" + URLEncoder.encode(var.get(key), UTF_8));
		
		String response = makeRequest(url.toString());
		
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(response, JsonElement.class).getAsJsonObject();
		
		if(!obj.get("success").getAsBoolean())
			throw new JoinException(obj.get("errorMessage").getAsString());
		
		return obj;
	}
	
	private static String makeRequest(String sURL) throws IOException{
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
}
