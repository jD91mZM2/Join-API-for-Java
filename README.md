# Join-API-for-Java
Access the Join API simply via Java

# Download
**Important: This API requires GSON.** [Look at the project in Maven](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.google.code.gson%22a%3A%22gson%22)

[Download](https://github.com/LEGOlord208/Join-API-for-Java/releases/tag/1.0)

## Documentation

Example: Change the clipboard of a device.

```
new JoinPush("YOUR DEVICE ID HERE")
			.set(PushParameter.CLIPBOARD, "This is your new clipboard. Hope you like it")
			.send();
```

Here would be a good idea to have a popup and ask for the API key or something.

Full example:
```
package one.krake.api.join.test;

import java.io.IOException;

import one.krake.api.join.JoinPush;
import one.krake.api.join.Parameter;

public class JoinTest{
	public static void main(String[] args) throws IOException{
		new JoinPush("YOUR DEVICE ID HERE")
			.set(PushParameter.CLIPBOARD, "This is your new clipboard. Hope you like it")
			.send();
	}
}

```

You can also send it to a group.

```
new JoinPush("YOUR API KEY HERE", Group.ALL)
			.set(PushParameter.TITLE, "This is happening on all devices.")
			.set(PushParameter.TEXT, "Like damn.")
			.send();
```

What about the find or location action? They don't have a value.
*Then skip the value :S*
```
new JoinPush("YOUR DEVICE ID HERE")
			.set(PushParameter.FIND)
			.send();
```

How can you send to multiple devices without iterating or something?

Easy!
```
new JoinPush(new String[]{"YOUR DEVICE ID HERE", "YOUR OTHER DEVICE ID HERE"})
	.set(PushParameter.FIND)
	.send();
```

What about getting all devices? And can I get their names?
Yup!!

```
Join join = new Join("YOUR API KEY");
ArrayList<Devices> devices = join.getDevices();
```

Ah, very nice! But their names? I thought I asked for that?

Yes sorry, you did! You are correct! Please forgive me! You use `device.get(DeviceParameter.DEVICE_NAME);`.
Explore the DeviceParameter class for more parameters!
But what if I want to make sure some parameter exists? Model doesn't exist on a PC, right?
Just use `device.has(DeviceParameter.DEVICE_NAME);`!
Simple! :D
