# thenpush-android
Android library for https://thenpush.me/ service

# What's this all about?

This is an Android library meant to help you use thenpush.me service to better manage your GCM (Google Cloud Message) push notifications.

This library makes easier to manage the registration IDs using thenpush.me backend, and easier track which push notifications sent trough thenpush.me were actually received by you app's user.

This repository includes a `sample` app, which is just a modified version of Google's quick start repository (https://github.com/googlesamples/google-services/tree/master/android/gcm)

If you don't have your project configured for GCM yet, visit first the oficial tutorial: https://developers.google.com/cloud-messaging/android/start

# Getting started with thenpush-android:

- Register at https://thenpush.me
- Add the dependency on your build.gradle:
> `compile 'thenpush.me:client:0.1.1+'`
- At your `strings.xml`, add the API Token for your user, and the project ID for the project you created at https://thenpush.me:

```
    <string name="thenpushme_api_token">PUT YOUR THENPUSH.ME API TOKEN HERE</string>
    <string name="thenpushme_project_id">PUT THE UUID FOR THE PROJECT YOU CREATED HERE</string>
```

Now, you are ready to manage the GCM registration IDs using https://thenpush.me backend, and tracking the pushes using the library.

# Saving a registration ID:

Once you get a GCM token (registration ID), you can send it to thenpush.me service doing:

```
DeviceSender sender = DeviceSender.getInstance(getApplicationContext());
Device device = new Device(token);
sender.send(device);
```

To make it easier to find your users, you can associate a list of tags, or a hashmap of attributes to the Device:

```
// build the device object with tags and data
// tags = ["tag1", "tag2", "tag3"]
// data = {"age": 15, "name": "John Doe"}
String [] tags = new String[]{"tag1", "tag2", "tag3"};
HashMap data = new HashMap();
data.put("age", 15);
data.put("name", "John Doe");
Device device = new Device(token, tags, data);

// Send the device object
DeviceSender sender = DeviceSender.getInstance(getApplicationContext());
sender.send(device);
```

# Marking a push as received

Once you receive a push sent trough thenpush.me service, you can mark it as received by doing:

```
@Override
public void onMessageReceived(String from, Bundle data) {
  PushReceiver.getInstance(getApplicationContext()).notifyReceipt(from, data);
}
```
