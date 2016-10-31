package one.krake.api.join;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import one.krake.jwebby.URLBuilder;
import one.krake.jwebby.Webby;

public class JoinPush{
	public static final String URL = "https://joinjoaomgcd.appspot.com/_ah/api/messaging/v1/sendPush";
	private static final Gson gson = new Gson();
	
	private ArrayList<String> devices = new ArrayList<>();
	
	private String apiKey = null;
	private String text = null;
	private String title = null;
	private String icon = null;
	private boolean smallicon = false;
	private String url = null;
	private String clipboard = null;
	private String file = null;
	private String smsnumber = null;
	private String smstext = null;
	private String wallpaper = null;
	private boolean find = false;
	
	private Consumer<String> error = null;
	private Consumer<JsonObject> success = null;
	
	public JoinPush(String device){
		addDevice(device);
	}
	public JoinPush(String... devices){
		addDevices(devices);
	}
	
	public void addDevice(String device){
		devices.add(device);
	}
	public void removeDevice(String device){
		devices.remove(device);
	}
	
	public void addDevices(String... devices){
		for(String device : devices)
			addDevice(device);
	}
	public void removeDevices(String... devices){
		for(String device : devices)
			removeDevice(device);
	}
	
	public List<String> devices(){
		return new ArrayList<>(devices);
	}
	
	public JoinPush setApiKey(String s){ apiKey = s; return this; }
	public JoinPush setText(String s){ text = s; return this; }
	public JoinPush setTitle(String s){ title = s; return this; }
	public JoinPush setIcon(String s){ icon = s; return this; }
	
	public JoinPush setSmallIcon(boolean b){ smallicon = b; return this; }
	public JoinPush setSmallIcon(){ smallicon = true; return this; }
	
	public JoinPush setUrl(String s){ url = s; return this; }
	public JoinPush setClipboard(String s){ clipboard = s; return this; }
	public JoinPush setFile(String s){ file = s; return this; }
	public JoinPush setSmsNumber(String s){ smsnumber = s; return this; }
	public JoinPush setSmsText(String s){ smstext = s; return this; }
	public JoinPush setWallpaper(String s){ wallpaper = s; return this; }
	
	public JoinPush setFind(boolean b){ find = b; return this; }
	public JoinPush setFind(){ find = true; return this; }

	public JoinPush setOnSuccess(Consumer<JsonObject> handler){ success = handler; return this; }
	public JoinPush setOnError(Consumer<String> handler){ error = handler; return this; }
	
	public String getApiKey(){ return apiKey; }
	public String getText(){ return text; }
	public String getTitle(){ return title; }
	public String getIcon(){ return icon; }
	public boolean isSmallIcon(){ return smallicon; }
	public String getUrl(){ return url; }
	public String getClipboard(){ return clipboard; }
	public String getFile(){ return file; }
	public String getSmsNumber(){ return smsnumber; }
	public String getSmsText(){ return smstext; }
	public String getWallpaper(){ return wallpaper; }
	public boolean isFind(){ return find; }
	
	private void handle(String prefix, String msg){
		Consumer<String> error = this.error;
		if(error == null) System.err.println(prefix + ": " + msg);
		else error.accept(msg);
	}
	private void handle(String msg){
		handle("Failed sending push", msg);
	}
	private void success(JsonObject json){
		Consumer<JsonObject> success = this.success;
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
		URLBuilder link = new URLBuilder(URL);
		
		List<String> devices = devices();
		
		if(devices.isEmpty())
			handle("Device list was empty");
		
		if(devices.size() == 1)
			link.add("deviceId", devices.get(0));
		else
			link.add("deviceIds", String.join(",", devices));
		
		String apiKey = this.apiKey;
		String text = this.text;
		String title = this.title;
		String icon = this.icon;
		boolean smallicon = this.smallicon;
		String url = this.url;
		String clipboard = this.clipboard;
		String file = this.file;
		String smsnumber = this.smsnumber;
		String smstext = this.smstext;
		String wallpaper = this.wallpaper;
		boolean find = this.find;
		
		if(apiKey != null) link.add("apikey", apiKey);
		if(text != null) link.add("text", text);
		if(title != null) link.add("title", title);
		if(icon != null) link.add("icon", icon);
		if(smallicon) link.add("smallicon", true);
		if(url != null) link.add("url", url);
		if(clipboard != null) link.add("clipboard", clipboard);
		if(file != null) link.add("file", file);
		if(smsnumber != null) link.add("smsnumber", smsnumber);
		if(smstext != null) link.add("smstext", smstext);
		if(wallpaper != null) link.add("wallpaper", wallpaper);
		if(find) link.add("find", true);
		
		String result;
		try{
			result = Webby.get(link.timestamp().toString());
		}
		catch(IOException e){
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
		
		if(json.get("success").getAsBoolean())
			success(json);
		else
			handle(json.get("errorMessage").getAsString());
	}
}
