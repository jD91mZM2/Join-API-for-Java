package one.krake.api.join;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JoinPush{
	private static final PushParameter deviceId = PushParameter.forName("deviceId");
	private static final PushParameter deviceIds = PushParameter.forName("deviceIds");
	private static final PushParameter senderId = PushParameter.forName("senderId");
	private static final PushParameter apiKey = PushParameter.forName("apikey");
	
	private HashMap<PushParameter, String> var = new HashMap<>();
	
	/**
	 * Create a new Join message!
	 * @param recipient The recipient device.
	 */
	public JoinPush(String recipient){
		set(deviceId, recipient);
	}
	
	/**
	 * Create a new Join message!
	 * @param recipient The recipient devices.
	 */
	public JoinPush(String[] recipient){
		set(deviceIds, String.join(",", recipient));
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
	 * 
	 * <strong>Every Parameter in this API has it's own alias method,
	 * such as {@link JoinPush#setTitle(String)}</strong>
	 * @param par The parameter. For example {@link PushParameter#TITLE}
	 * @param value The value, for example Hello!
	 * @return The instance, useful for cleaner code.
	 */
	public JoinPush set(PushParameter par, String value){
		var.put(par, value);
		return this;
	}
	
	public JoinPush setTitle(String value){ return set(PushParameter.TITLE, value); }
	public JoinPush setText(String value){ return set(PushParameter.TEXT, value); }
	public JoinPush setIcon(String value){ return set(PushParameter.ICON, value); }
	public JoinPush setURL(String value){ return set(PushParameter.URL, value); }
	public JoinPush setClipboard(String value){ return set(PushParameter.CLIPBOARD, value); }
	public JoinPush setFile(String value){ return set(PushParameter.FILE, value); }
	public JoinPush setSmsNumber(String value){ return set(PushParameter.SMSNUMBER, value); }
	public JoinPush setSmsText(String value){ return set(PushParameter.SMSTEXT, value); }
	public JoinPush setWallpaper(String value){ return set(PushParameter.WALLPAPER, value); }
	public JoinPush setLocation(boolean value){ return set(PushParameter.LOCATION, value); }
	public JoinPush setFind(boolean value){ return set(PushParameter.FIND, value); }
	
	/**
	 * Will do the same thing as {@link JoinPush#set(PushParameter, String)},
	 * but the boolean will be converted automatically.
	 * Used for things without a value, such as {@link PushParameter#FIND} and {@link PushParameter#LOCATION}
	 * @param par The parameter. For example Parameter.TITLE
	 * @return The instance, useful for cleaner code.
	 */
	public JoinPush set(PushParameter par, boolean value){
		return set(par, String.valueOf(value));
	}
	
	/**
	 * Sends the push on the main thread.
	 * @throws IOException If something went wrong, such as bad internet connection et.c.
	 * @return The Json that was responded.
	 */
	public JsonObject send() throws IOException{
		StringBuilder url = new StringBuilder("https://joinjoaomgcd.appspot.com/_ah/api/messaging/v1/sendPush");
		boolean first = true;
		for(PushParameter key : var.keySet()){
			url.append(first ? "?" : "&");
			url.append("&" + key.toString() + "=" + URLEncoder.encode(var.get(key), "UTF-8"));
			
			first = false;
		}
		
		String response = Join.makeRequest(url.toString());
		
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(response, JsonElement.class).getAsJsonObject();
		
		if(!obj.get("success").getAsBoolean())
			throw new JoinException(obj);
		
		return obj;
	}
}
