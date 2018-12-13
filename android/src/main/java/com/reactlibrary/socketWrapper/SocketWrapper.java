package com.reactlibrary.socketWrapper;

import com.reactlibrary.socketWrapper.components.classes.notifications.ReactNotificationClass;
import com.reactlibrary.socketWrapper.components.client.Client;
import com.facebook.react.bridge.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import async_communicator.AsyncCommunicator;
import async_communicator.thread_id_holder.ThreadIdHolder;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class SocketWrapper {
    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
    private final Client client;
    private final Map<String,Callback> callbacks;
    private final ArrayList<DataTradeModel> dataTradeModel;

    public SocketWrapper() {
        client = new Client();
        callbacks = new HashMap<>();
        dataTradeModel= new ArrayList<>();

        dataTradeModel.add(new ReactNotificationClass());
    }

    public void addCallbackNotification(String methodIdent,Callback callback){
        callbacks.put(methodIdent,callback);
    }
    public void addNotificationClass(DataTradeModel notificationClass){
        dataTradeModel.add(notificationClass);
    }

    public void sendMessageToServer(String classIdent,String methodIdent,String message,Callback callback){
        ThreadIdHolder idHolder = client.sendMessageToServer(classIdent,methodIdent,message);
        boolean flag = asyncCommunicator.waitForFlagAndRemove(idHolder.getThreadId(),"SendMessageStatus");
        callback.invoke(flag);
    }
    public void sendMessageToServerNoResponse(String classIdent,String methodIdent,String message,Callback callback){
        ThreadIdHolder idHolder = client.sendMessageToServerNoResponse(classIdent,methodIdent,message);
        boolean flag = asyncCommunicator.waitForFlagAndRemove(idHolder.getThreadId(),"SendMessageStatus");
        callback.invoke(flag);
    }

    public void connectToServer(String host,int port,Callback callback){
        ThreadIdHolder threadIdHolder = client.configureSocket(host,port,dataTradeModel.toArray(new DataTradeModel[dataTradeModel.size()]),callbacks);
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();

        boolean connection = asyncCommunicator.waitForFlagAndRemove(threadIdHolder.getThreadId(),"Connection");
        callback.invoke(connection);
    }
}
