package one.krake.api.join;

public class DeviceParameter{
	public static final DeviceParameter ID = forName("id");
	public static final DeviceParameter REG_ID = forName("regId");
	public static final DeviceParameter USER_ACCOUNT = forName("userAccount");
	public static final DeviceParameter DEVICE_ID = forName("deviceId");
	public static final DeviceParameter DEVICE_NAME = forName("deviceName");
	public static final DeviceParameter DEVICE_TYPE = forName("deviceType");
	public static final DeviceParameter API_LEVEL = forName("apiLevel");
	public static final DeviceParameter MODEL = forName("model");
	
	public static DeviceParameter forName(String name){
		return new DeviceParameter(name);
	}
	
	private String name;
	private DeviceParameter(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}