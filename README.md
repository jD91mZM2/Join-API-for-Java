# Join-API-for-Java
Access the Join API simply via Java

# Download
**Important: This API requires GSON.** [Look at the project in Maven](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.google.code.gson%22a%3A%22gson%22)

[Download](http://www.mediafire.com/download/8wb79z2to75b574/Join_Java_API.jar)

## Documentation

Example: Change the clipboard of a device.

```
new JoinPush("YOUR DEVICE ID HERE")
			.setClipboard("This is your new clipboard. Hope you like it")
			.send();
```

Here would be a good idea to have a popup and ask for the API key or something.

Full example:
```
package one.krake.api.join.test;

import java.io.IOException;

import one.krake.api.join.JoinPush;

public class JoinTest{
	public static void main(String[] args) throws IOException{
		new JoinPush("YOUR DEVICE ID HERE")
			.setClipboard("This is your new clipboard. Hope you like it")
			.send();
	}
}

```

You can also send it to a group.

```
new JoinPush("YOUR API KEY HERE", Group.ALL)
			.setTitle("This is happening on all devices.")
			.setText("Like damn.")
			.send();
```
More examples
```
new JoinPush("YOUR DEVICE ID HERE")
			.setFind(true)
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

But what if I only want phones?
```
Join join = new Join("YOUR API KEY");
ArrayList<Devices> devices = join.getDevices(DeviceType.PHONE);
```
But what if I want phone and tablets then?
```
Join join = new Join("YOUR API KEY");
ArrayList<Devices> devices = join.getDevices(Arrays.asList(DeviceType.PHONE, DeviceType.TABLET));
```

Ah, very nice! But their names? I thought I asked for that?

Yes sorry, you did! You are correct! Please forgive me! You use `device.getDeviceName();`.
Explore the DeviceParameter class for more parameters!
But what if I want to make sure some parameter exists? Model doesn't exist on a PC, right?
Just use `device.has(DeviceParameter.DEVICE_NAME);`!
Simple! :D


## Aliases

All the `setTitle(value)` methods and such are just aliases.
The previous usage was `set(PushParameter.TITLE, value)`.
We recommend using `setTitle`, but it's good to know the old way.
Also, we still use parameters on Device's `has` method.


What if the API is missing something? Maybe Join adds the action "cool=true" or something?
Then you just use the old way, but with `forName`.! `set(PushParameter.forName("cool"), true);`

Amazing!
