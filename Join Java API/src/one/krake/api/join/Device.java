package one.krake.api.join;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class Device{
	private HashMap<String, String> fields;
	
	protected Device(JsonElement obj){
		Gson gson = new Gson();
		fields = gson.fromJson(obj, new TypeToken<HashMap<String, String>>(){}.getType());
	}
	
	/**
	 * Get a field from the Device.
	 * 
	 * <strong>Every Parameter in this API has it's own alias method,
	 * such as {@link Device#getDeviceType()}</strong>
	 * @param par The field
	 * @return The value of the field
	 */
	public String get(DeviceParameter par){
		return fields.get(par.getName());
	}
	
	public String getId(){ return get(DeviceParameter.ID); }
	public String getRegId(){ return get(DeviceParameter.REG_ID); }
	public String getUserAccount(){ return get(DeviceParameter.USER_ACCOUNT); }
	public String getDeviceId(){ return get(DeviceParameter.DEVICE_ID); }
	public String getDeviceName(){ return get(DeviceParameter.DEVICE_NAME); }
	public DeviceType getDeviceType(){
		return DeviceType.forId(Integer.parseInt(get(DeviceParameter.DEVICE_TYPE)));
	}
	public String getApiLevel(){ return get(DeviceParameter.API_LEVEL); }
	public String getModel(){ return get(DeviceParameter.MODEL); }
	
	/**
	 * Check if there is a field with that name.
	 * @param par The field
	 * @return True if it found the field successfully, false otherwise.
	 */
	public boolean has(DeviceParameter par){
		return fields.containsKey(par.getName());
	}
	
	public String toString(){
		String str = "";
		
		for(String key : fields.keySet())
			str += "\n" + key + ": " + fields.get(key);
		
		if(str.startsWith("\n"))
			str = str.substring(1);
		
		return str;
	}
}
