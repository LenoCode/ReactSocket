
package com.reactlibrary;


import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.reactlibrary.socketWrapper.SocketWrapper;

import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class RNReactSocketModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private final SocketWrapper socketWrapper = new SocketWrapper();

  public RNReactSocketModule(ReactApplicationContext reactContext, DataTradeModel[] dataTradeModels) {
    super(reactContext);
    this.reactContext = reactContext;
    for (DataTradeModel dataTradeModel : dataTradeModels){
        socketWrapper.addNotificationClass(dataTradeModel);
    }
  }

  @ReactMethod
  public void connectToServer(String host,int port,Callback callback){
    socketWrapper.connectToServer(host,port,callback);
  }
  @ReactMethod
  public void addNotificationMethod(String methodIdent,Callback callback){
    socketWrapper.addCallbackNotification(methodIdent,callback);
  }
  @ReactMethod
  public void sendMessageToServer(String classIdent,String methodIdent,String message,Callback callback){
    socketWrapper.sendMessageToServer(classIdent,methodIdent,message,callback);
  }
  @ReactMethod
  public void sendMessageToServerNoResponse(String classIdent,String methodIdent,String message,Callback callback){
    socketWrapper.sendMessageToServerNoResponse(classIdent,methodIdent,message,callback);
  }





  @Override
  public String getName() {
    return "ReactSocket";
  }
}