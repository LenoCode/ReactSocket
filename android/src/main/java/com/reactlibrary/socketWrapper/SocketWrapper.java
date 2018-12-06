package com.reactlibrary.socketWrapper;

import com.reactlibrary.socketWrapper.components.client.Client;
import com.facebook.react.bridge.Callback;

import async_communicator.AsyncCommunicator;
import async_communicator.thread_id_holder.ThreadIdHolder;

public class SocketWrapper {
    private final Client client;


    public SocketWrapper() {
        client = new Client();
    }

    public void connectToServer(String host,int port,Callback callback){
        ThreadIdHolder threadIdHolder = client.configureSocket(host,port);
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();

        boolean connection = asyncCommunicator.waitForFlagAndRemove(threadIdHolder.getThreadId(),"Connection");
        callback.invoke(connection);
    }
}
