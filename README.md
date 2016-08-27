# Join-API-for-Java
Access the Join API simply via Java

# Download
**Important: This API requires [GSON](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.google.code.gson%22a%3A%22gson%22)  and [jWebby](https://krake.one/webby)**

## This API was recently re-written, and might have a bug or two. Or missing feature.
Just stay with me and report all errors, and everything will work fine!

## Documentation

Example: Change the clipboard of a device.

```
new JoinPush("YOUR DEVICE ID HERE")
			.setClipboard("This is your new clipboard. Hope you like it")
			.send();
```

Here you should do anything you want. Take a look through what methods are available and just use them :D
Also, it would be a good idea to have a popup and ask for the Device Id or something.

Full example:
```
package me.yournamehere.jointest;

import one.krake.api.join.JoinPush;

public class JoinTest{
	public static void main(String[] args){
		new JoinPush("YOUR DEVICE ID HERE")
			.setClipboard("This is your new clipboard. Hope you like it")
			.send();
	}
}

```

You SHOULD catch error messages and success events, too

```
new JoinPush("YOUR DEVICE ID HERE")
	.setOnError(e -> System.out.println("Oh noes, something happened"))
	.setOnSuccess(e -> System.out.println("yay everything works"))
	.setClipboard("This is your new clipboard. Hope you like it")
	.send();
```

The error event also gives you an error message, and the success event gives you a JsonObject.

You can also send messages to a group.

```
new JoinPush(Group.GROUP_ALL)
	.setApiKey("YOUR API KEY HERE")
	.setTitle("This is happening on all devices.")
	.setText("Like damn.")
	.send();
```
More examples
```
new JoinPush("YOUR DEVICE ID HERE")
	.setFind()
	.send();
```

By the way, all set methods also has a get method, too.
For example
```
JoinPush push = new JoinPush("YOUR DEVICE ID HERE")
	.setFind();
	
System.out.println(push.isFind())
```

How can you send to multiple devices without iterating or something?

Easy!
```
new JoinPush("YOUR DEVICE ID HERE", "YOUR OTHER DEVICE ID HERE")
	.setFind()
	.send();
```

By default all requests are sent on a new thread. Both here, and in the a future example coming right up.
If you want to disable this, just use the sendSync function

```
new JoinPush("YOUR DEVICE ID HERE", "YOUR OTHER DEVICE ID HERE")
	.setFind()
	.sendSync();
```

What about getting all devices? And can I get their names?
Yup!!

```
new DeviceList("API KEY GOES HERE")
	.setOnSuccess(System.out::println)
	.send();
```
Obviously, the success gives you a Device class where you can look at pretty much everything.
Is it a phone? Does it have Tasker?

But what if I want to make sure some parameter exists? Model doesn't exist on a PC, right?
Just use `device.hasModel();`!
Simple! :D

Yay
