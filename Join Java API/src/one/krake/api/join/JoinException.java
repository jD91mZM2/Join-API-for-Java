package one.krake.api.join;

import java.io.IOException;

public class JoinException extends IOException{
	public JoinException(String error){
		super(error);
	}
}
