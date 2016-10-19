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


 
 
