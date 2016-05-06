package one.krake.api.join;

public class Parameter{
	public static final Parameter TITLE = forName("title");
	public static final Parameter TEXT = forName("text");
	public static final Parameter ICON = forName("icon");
	public static final Parameter URL = forName("url");
	public static final Parameter CLIPBOARD = forName("clipboard");
	public static final Parameter FILE = forName("file");
	public static final Parameter SMSNUMBER = forName("smsnumber");
	public static final Parameter SMSTEXT = forName("smstext");
	public static final Parameter WALLPAPER = forName("wallpaper");
	public static final Parameter LOCATION = forName("location");
	public static final Parameter FIND = forName("find");
	
	public static Parameter forName(String name){
		return new Parameter(name);
	}
	
	private String name;
	
	private Parameter(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
