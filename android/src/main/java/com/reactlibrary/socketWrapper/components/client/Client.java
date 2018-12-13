package com.reactlibrary.socketWrapper.components.client;


import com.facebook.react.bridge.Callback;
import com.reactlibrary.socketWrapper.components.classes.threads.DataTransferThreads;
import com.reactlibrary.socketWrapper.components.classes.threads.SocketConnectionThreads;
import com.reactlibrary.socketWrapper.components.client.clientSocket.ClientSocket;
import com.reactlibrary.socketWrapper.components.client.externalInitializator.ExternalContextInitializator;
import com.reactlibrary.socketWrapper.components.client.notificationer.ClientNotificationer;
import com.reactlibrary.socketWrapper.components.threadCaller.AndroidThreadCaller;

import java.util.Map;

import async_communicator.thread_id_holder.ThreadIdHolder;

import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;

public class Client {
    private ClientSocket clientSocket;


    public ThreadIdHolder configureSocket(String host, int port,DataTradeModel[] dataTradeModels,Map<String,Callback> callbackMap){
        clientSocket = new ClientSocket();
        return clientSocket.configure(host,port,initClientNotificationer(dataTradeModels,callbackMap));
    }

    public boolean isConnectedToServer(){
        return clientSocket.getClientCreatedSocket().isConnectedToServer();
    }
    public void disconnectFromServer(){
        AndroidThreadCaller.callThread(SocketConnectionThreads.class,"disconnectFromServerThread",clientSocket.getClientCreatedSocket());
    }

    public ThreadIdHolder sendMessageToServer(String classIdent, String methodIdent, String dataToSend){
        return AndroidThreadCaller.callThreadAndReturnId(DataTransferThreads.class,"sendMessageToServerThread",classIdent,methodIdent,dataToSend,clientSocket.getClientCreatedSocket());

    }
    public ThreadIdHolder sendMessageToServerNoResponse(String classIdent, String methodIdent, String dataToSend){
        return AndroidThreadCaller.callThreadAndReturnId(DataTransferThreads.class,"sendMessageToServerThreadNoResponse",classIdent,methodIdent,dataToSend,clientSocket.getClientCreatedSocket());

    }

    public ClientNotificationer getClientNotificationer(){
        return (ClientNotificationer) clientSocket.getClientCreatedSocket().getClient().getNotificationer();
    }
    public ExternalContext getExternalContext(){
        return clientSocket.getClientCreatedSocket().getClient().getNotificationer().getExternalContext();
    }
    public boolean isClientInitialized(){
        return clientSocket.isClientInitialized();
    }


    private ClientNotificationer initClientNotificationer(DataTradeModel[] dataTradeModels, Map<String,Callback> callbackMap){
        ExternalContextInitializator contextInitializator = new ExternalContextInitializator(callbackMap);
        return new ClientNotificationer(dataTradeModels,contextInitializator);
    }



}
