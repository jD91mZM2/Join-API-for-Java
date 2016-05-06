package one.krake.api.join;

import java.io.IOException;

import com.google.gson.JsonObject;

public class JoinException extends IOException{
	public JoinException(JsonObject object){
		super(object.get("errorMessage").getAsString());
	}
}
