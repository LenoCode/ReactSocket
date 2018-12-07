
package com.reactlibrary;

import android.telecom.Call;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.reactlibrary.socketWrapper.SocketWrapper;

public class RNReactSocketModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private final SocketWrapper socketWrapper = new SocketWrapper();

  public RNReactSocketModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void connectToServer(String host,int port,Callback callback){
      callback.invoke("STATUS JE TRUE FILIPE");
  }

  @ReactMethod
  public void test(Callback callback){
    callback.invoke("King kong");
  }



  @Override
  public String getName() {
    return "ReactSocket";
  }
}