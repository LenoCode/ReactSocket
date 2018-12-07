package com.reactlibrary.socketWrapper.components.client.externalInitializator;

import com.facebook.react.bridge.Callback;

import java.util.HashMap;
import java.util.Map;

import socket_installer.SI_context.context_object.ContextObject;
import socket_installer.SI_context.external_context.ExternalContext;

public class ExternalContextInitializator implements socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator {
    private final Map<String,Callback> callbackMap;

    public ExternalContextInitializator(){
        callbackMap = new HashMap<>();
    }

    public ExternalContextInitializator(Map<String, Callback> callbackMap) {
        this.callbackMap = callbackMap;
    }

    @Override
    public void initializeExternalContext(ExternalContext externalContext) {
        try {
            ContextObject<Map<String,Callback>> contextObject = new ContextObject<>();
            contextObject.setContextObject(callbackMap);
            externalContext.saveContextObject(contextObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
