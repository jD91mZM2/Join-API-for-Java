package one.krake.api.join;

public class PushParameter{
	public static final PushParameter TITLE = forName("title");
	public static final PushParameter TEXT = forName("text");
	public static final PushParameter ICON = forName("icon");
	public static final PushParameter URL = forName("url");
	public static final PushParameter CLIPBOARD = forName("clipboard");
	public static final PushParameter FILE = forName("file");
	public static final PushParameter SMSNUMBER = forName("smsnumber");
	public static final PushParameter SMSTEXT = forName("smstext");
	public static final PushParameter WALLPAPER = forName("wallpaper");
	public static final PushParameter LOCATION = forName("location");
	public static final PushParameter FIND = forName("find");
	
	public static PushParameter forName(String name){
		return new PushParameter(name);
	}
	
	private String name;
	
	private PushParameter(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
