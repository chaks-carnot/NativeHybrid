# NativeHybrid
 A sample app for the Native Hybrid link in Android.
 
 Make a sample ionic app using the following commands
 
  ```shell
  mkdir ionic_project
  cd ionic_porject
  ionic start sampleapp
  cd sampleapp
  ionic add platform android
  cd platform/android
  ```
 
 Now you should be able to see the android folder in the project. Open the android folder in Android Studio (import the project and let the Android Studio build it). The project should now look something like this,
 
 ![Image](../master/screenshots/Screen%20Shot%202016-10-19%20at%209.20.11%20AM.png?raw=true)
 
 Now if the apk is built it should run the sample app in an android phone. If everything goes okay, the next step is to build a sample activity (In this case it is called Next_Activity.java)
 
 ```java
 package com.ionicframework.sampleapp724603;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

public class MainActivity extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);

    }
}
```

The xml(layout) for the same will look like,

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_next_"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.ionicframework.sampleapp724603.Next_Activity">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:layout_centerInParent="true"
        android:text=" YO"
        android:textSize="200dp"
        />

</RelativeLayout>
```

Add the XML to the /res/layout folder. If it does not exist create one.

The next step is to write the Cordova plugin so as to make the connection between the Next_Activity and MainActivity. The plugin would have just an intent and nothing else as shown below,

```java
package com.ionicframework.sampleapp724603;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;


public class CallNext extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext){

        Context context=this.cordova.getActivity().getApplicationContext();
        Intent intent=new Intent(context, Next_Activity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        return false;
    }
}
```

Now since the index.html file includes the stock code so we would change this to a simple page with a button. The code of the sample index.html would then look like,

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
  <title></title>

  <link rel="manifest" href="manifest.json">

  <link href="lib/ionic/css/ionic.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">

  <!-- IF using Sass (run gulp sass first), then uncomment below and remove the CSS includes above
  <link href="css/ionic.app.css" rel="stylesheet">
  -->

  <!-- ionic/angularjs js -->
  <script src="lib/ionic/js/ionic.bundle.js"></script>

  <!-- cordova script (this will be a 404 during development) -->
  <script src="cordova.js"></script>

  <!-- your app's js -->
  <script src="js/app.js"></script>
  <script src="js/controllers.js"></script>
  <script src="js/services.js"></script>

</head>
<body ng-app="starter" class="platform-android platform-cordova platform-webview">
<h2>Hello Android</h2>
<button onclick="initial()">test</button>

</body>

<script type="text/javascript">
      function initial(){
      var name = "YOYO";
      cordova.exec(sayHelloSuccess, sayHelloFailure, "CallNext", "sayHello", [name]);
      }

      function sayHelloSuccess(data){
      alert("OK: " + data);
      }

      function sayHelloFailure(data){
      alert("FAIL: " + data);
      }
  </script>
</html>
```
A custom script was added for the callback and onclick listener of the button as shown above. This woudld call the custom plugin and show the output to the user. To know more about the formatting of the plugin and creating one please see the following official [document](https://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/). The callbacks for the success and failure have been implemented in an alertbox.

Now the last portion contains the registration of activity in the mainfest file which would look something like this,
```xml
<?xml version='1.0' encoding='utf-8'?>
<manifest android:hardwareAccelerated="true"
    android:versionCode="1"
    android:versionName="0.0.1"
    package="com.ionicframework.sampleapp724603"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <supports-screens android:anyDensity="true"
        android:largeScreens="true"
        android:normalScreens="true"
        android:resizeable="true"
        android:smallScreens="true"
        android:xlargeScreens="true" />
    <uses-permission android:name="android.permission.INTERNET" />
    <application android:hardwareAccelerated="true"
        android:icon="@drawable/icon"
        android:label="@string/app_name"
        android:supportsRtl="true">
        <activity android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale"
            android:label="@string/activity_name"
            android:launchMode="singleTop"
            android:name="MainActivity"
            android:theme="@android:style/Theme.DeviceDefault.NoActionBar"
            android:windowSoftInputMode="adjustResize">
            <intent-filter android:label="@string/launcher_name">
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".Next_Activity" />
    </application>
    <uses-sdk android:minSdkVersion="16" android:targetSdkVersion="23" />
</manifest>
```

and the registration of plugin in the config.xml file generated by ionic. The following code is then added to the config file.
```xml
 <feature name="CallNext">
        <param name="android-package" value="com.ionicframework.sampleapp724603.CallNext" />
    </feature>
```

Now you should be able to clean build the project and run the project in any android phone.



 
 
