
package com.reactlibrary;


import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.reactlibrary.socketWrapper.SocketWrapper;
import org.json.JSONException;
import org.json.JSONObject;

public class RNReactSocketModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private final SocketWrapper socketWrapper = new SocketWrapper();

  public RNReactSocketModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void connectToServer(String host,int port,Callback callback){
      socketWrapper.connectToServer(host,port,callback);
  }
  @ReactMethod
  public void addNotificationMethods(ReadableMap map){
    if (map.hasKey("login")){
      Toast.makeText(reactContext, "true je ovo", Toast.LENGTH_SHORT).show();
    }
  }





  @Override
  public String getName() {
    return "ReactSocket";
  }
}