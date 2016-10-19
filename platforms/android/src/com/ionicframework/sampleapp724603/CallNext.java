package com.ionicframework.sampleapp724603;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

/**
 * Created by chaks on 10/15/16.
 */

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
