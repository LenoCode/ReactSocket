package com.reactlibrary.socketWrapper.components.classes.threads;



import android.util.Log;

import java.io.IOException;

import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import thread_watcher.models.abstractions.user_method.UserMethod;
import thread_watcher.models.annotations.ThreadMethod;
import thread_watcher.user_parts.thread_bundle.Bundle;

public class DataTransferThreads extends UserMethod {
    private final String classIdentParam = "ClassIdent";
    private final String methodIdentParam = "MethoIdent";
    private final String dataToSendParam = "DataToSend";
    private final String clientCreatedSocketParam = "ClientCreatedSocket";

    @ThreadMethod(paramNames = {classIdentParam,methodIdentParam,dataToSendParam, clientCreatedSocketParam})
    public void sendMessageToServerThread(Bundle bundle) {
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
        ClientCreatedSocket clientCreatedSocket = (ClientCreatedSocket) bundle.getArguments(clientCreatedSocketParam);
        String classIdent = (String) bundle.getArguments(classIdentParam);
        String methodIdent = (String) bundle.getArguments(methodIdentParam);
        String dataToSend = (String) bundle.getArguments(dataToSendParam);

        try {
            clientCreatedSocket.runSocketNoStreamOpen(classIdent,methodIdent,dataToSend);
        } catch (IOException e) {
            Log.d("LENOO",e.getLocalizedMessage());
            asyncCommunicator.addFlag(Thread.currentThread().getId(),"IOException",true);
        } catch (SocketExceptions socketExceptions) {
            Log.d("LENOO",socketExceptions.getLocalizedMessage());
            asyncCommunicator.addFlag(Thread.currentThread().getId(),"SocketException",true);
        }
    }

}
