package one.krake.api.join;

public class Group{
	public static final Group ALL = forName("group.all");
	public static final Group ANDROID = forName("group.android");
	public static final Group CHROME = forName("group.chrome");
	public static final Group WINDOWS = forName("group.windows10");
	public static final Group PHONE = forName("group.phone");
	public static final Group TABLET = forName("group.tablet");
	public static final Group PC = forName("group.pc");
	
	
	public static Group forName(String name){
		return new Group(name);
	}
	
	private String name;
	private Group(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
