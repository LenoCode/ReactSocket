package com.reactlibrary.socketWrapper.components.client.clientSocket;


import com.reactlibrary.socketWrapper.components.classes.threads.SocketConnectionThreads;
import com.reactlibrary.socketWrapper.components.threadCaller.AndroidThreadCaller;

import async_communicator.thread_id_holder.ThreadIdHolder;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;

public class ClientSocket {
    private boolean clientInitialized;
    private ClientCreatedSocket clientCreatedSocket;


    public ThreadIdHolder configure(String host, int port, NotificationerActions notificationerActions) {
        return AndroidThreadCaller.callThreadAndReturnId(SocketConnectionThreads.class,"configureSocket",this,host,port,notificationerActions);
    }

    public ClientCreatedSocket getClientCreatedSocket() {
        return clientCreatedSocket;
    }

    public void setClientCreatedSocket(ClientCreatedSocket clientCreatedSocket){
        this.clientCreatedSocket = clientCreatedSocket;
        this.clientInitialized = true;
    }

    public boolean isClientInitialized() {
        return clientInitialized;
    }
}
