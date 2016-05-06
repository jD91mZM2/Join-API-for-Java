# Join-API-for-Java
Access the Join API simply via Java

# Download
**Important: This API requires GSON.** [Look at the project in Maven](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.google.code.gson%22a%3A%22gson%22)

[Download](https://github.com/LEGOlord208/Join-API-for-Java/releases/tag/1.0)

## Documentation

Example: Change the clipboard of a device.

```
new JoinPush("YOUR DEVICE ID HERE")
			.set(Parameter.CLIPBOARD, "This is your new clipboard. Hope you like it")
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
			.set(Parameter.CLIPBOARD, "This is your new clipboard. Hope you like it")
			.send();
	}
}

```

You can also send it to a group.

```
new JoinPush("YOUR API KEY HERE", Group.ALL)
			.set(Parameter.TITLE, "This is happening on all devices.")
			.set(Parameter.TEXT, "Like damn.")
			.send();
```

What about the find or location action? They don't have a value.
*Then skip the value :S*
```
new JoinPush("YOUR DEVICE ID HERE")
			.set(Parameter.FIND)
			.send();
```
