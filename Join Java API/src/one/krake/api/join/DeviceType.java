package one.krake.api.join;

public class DeviceType{
	public static final DeviceType PHONE = forId(1);
	public static final DeviceType TABLET = forId(2);
	public static final DeviceType CHROME = forId(3);
	public static final DeviceType PC = forId(4);
	
	public static DeviceType forId(int id){
		return new DeviceType(id);
	}
	
	private int id;
	private DeviceType(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		return "DeviceType: int(" + id + ")";
	}
	
	public boolean equals(Object o){
		if(o instanceof DeviceType){
			DeviceType d = (DeviceType) o;
			return getId() == d.getId();
		}
		return this == o;
	}
}
