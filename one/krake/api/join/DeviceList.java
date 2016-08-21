package one.krake.api.join;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import one.krake.jwebby.URLBuilder;
import one.krake.jwebby.Webby;

public class DeviceList{
	public static final String URL = "https://joinjoaomgcd.appspot.com/_ah/api/registration/v1/listDevices";
	private static final Gson gson = new Gson();
	private String apiKey;
	
	private Consumer<String> error = null;
	private Consumer<List<Device>> success = null;
	
	public DeviceList(String apiKey){
		this.apiKey = apiKey;
	}
	
	public DeviceList setOnSuccess(Consumer<List<Device>> handler){ success = handler; return this; }
	public DeviceList setOnError(Consumer<String> handler){ error = handler; return this; }
	
	private void handle(String msg){
		Consumer<String> error = this.error;
		if(error == null) System.err.println("Error listing devices: " + msg);
		else error.accept(msg);
	}
	private void success(ArrayList<Device> json){
		Consumer<List<Device>> success = this.success;
		if(success != null) success.accept(json);
	}
	
	public void send(){
		new Thread(new Runnable(){
			public void run(){
				sendSync();
			}
		}).start();
	}
	
	public void sendSync(){
		URLBuilder url = new URLBuilder(URL).add("apikey", apiKey);
		
		String result;
		try{
			result = Webby.read(
				url.timestamp().toURL()
			);
		}
		catch(IOException e){
			e.printStackTrace();
			handle("Request failed.");
			return;
		}
		
		JsonObject json;
		try{
			json = gson.fromJson(result, JsonElement.class).getAsJsonObject();
		}
		catch(JsonSyntaxException | UnsupportedOperationException e){
			handle("Parsing result failed");
			return;
		}
		
		if(json.get("success").getAsBoolean()){
			ArrayList<Device> list = new ArrayList<>();
			JsonArray arr = json.getAsJsonArray("records");
			for(JsonElement e : arr){
				JsonObject o = e.getAsJsonObject();
				list.add(new Device(o));
			}
			success(list);
		}
		else
			handle(json.get("errorMessage").getAsString());
	}
}
