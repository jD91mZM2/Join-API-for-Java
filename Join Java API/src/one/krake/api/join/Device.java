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
	 * @param par The field
	 * @return The value of the field
	 */
	public String get(DeviceParameter par){
		return fields.get(par.getName());
	}
	
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
