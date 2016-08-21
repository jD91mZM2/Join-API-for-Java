package one.krake.api.join;

import com.google.gson.JsonObject;

public class Device{
	private JsonObject json;
	
	public Device(JsonObject json){
		this.json = json;
	}
	
	public String getId(){ return json.get("id").getAsString(); }
	public String getRegId(){ return json.get("regId").getAsString(); }
	public String getRegId2(){ return json.get("regId2").getAsString(); }
	public String getUserAccount(){ return json.get("userAccount").getAsString(); }
	public String getDeviceId(){ return json.get("deviceId").getAsString(); }
	public String getDeviceName(){ return json.get("deviceName").getAsString(); }
	public String getDeviceType(){ return json.get("deviceType").getAsString(); }
	public String getApiLevel(){ return json.get("apiLevel").getAsString(); }
	public String getModel(){ return json.get("model").getAsString(); }
	
	public boolean hasTasker(){ return json.get("hasTasker").getAsBoolean(); }
	public boolean hasModel(){ return json.has("model"); }
	public boolean hasRegId2(){ return json.has("regId2"); }
	
	public String toString(){
		return getDeviceName();
	}
}
