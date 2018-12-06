
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.widget.Toast;

public class RNReactSocketModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNReactSocketModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public String println(){
    Toast.makeText(getReactApplicationContext(), "KING KONG JE TU", Toast.LENGTH_LONG).show();
    return "King kong cale";
  }

  @Override
  public String getName() {
    return "RNReactSocket";
  }
}