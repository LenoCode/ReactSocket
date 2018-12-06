package com.reactlibrary.socketWrapper.components.classes.threads;

import com.reactlibrary.socketWrapper.components.client.clientSocket.ClientSocket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import async_communicator.AsyncCommunicator;
import socket_installer.SI.socket_creation.client.ClientSocketCreator;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import thread_watcher.models.abstractions.user_method.UserMethod;
import thread_watcher.models.annotations.ThreadMethod;
import thread_watcher.user_parts.thread_bundle.Bundle;


public class SocketConnectionThreads extends UserMethod {
    private final String clientSocketParam = "clientSocket";
    private final String hostParam = "host";
    private final String portParam = "port";
    private final String notificationerActionsParam = "notificationerActions";
    private final String clientCreatedSocketParam = "clientCreatedSocket";


    @ThreadMethod(paramNames = {clientSocketParam,hostParam,portParam,notificationerActionsParam})
    public void configureSocket(Bundle bundle){
        ClientSocket clientSocket = (ClientSocket) bundle.getArguments(clientSocketParam);
        String host =(String) bundle.getArguments(hostParam);
        Integer port = (Integer) bundle.getArguments(portParam);
        NotificationerActions notificationerActions = (NotificationerActions) bundle.getArguments(notificationerActionsParam);
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host,port));

            ClientCreatedSocket clientCreatedSocket = ClientSocketCreator.createClientCreatedSocket(notificationerActions,socket,0);
            clientSocket.setClientCreatedSocket(clientCreatedSocket);
            clientCreatedSocket.initSocket();
            clientSocket.setClientCreatedSocket(clientCreatedSocket);

            asyncCommunicator.addFlag(Thread.currentThread().getId(),"Connection",true);

        } catch (IOException exception) {
            asyncCommunicator.addFlag(Thread.currentThread().getId(),"Connection",false);
        } catch (SocketExceptions socketExceptions) {
            asyncCommunicator.addFlag(Thread.currentThread().getId(),"Connection",false);
        }
        finally {

        }
    }



}
