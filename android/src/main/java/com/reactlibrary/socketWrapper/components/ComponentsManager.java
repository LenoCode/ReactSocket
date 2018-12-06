package com.reactlibrary.socketWrapper.components;


import com.reactlibrary.socketWrapper.components.client.Client;

public class ComponentsManager {
    private static ComponentsManager componentsManager;
    private final Client client;

    private ComponentsManager(){
        client = new Client();
    }

    public static ComponentsManager getComponentsManager(){
        if (componentsManager == null){
            componentsManager = new ComponentsManager();
        }
        return componentsManager;
    }
    public static void destroyComponentManager(){
        componentsManager = null;
    }

    public Client getClient() {
        return client;
    }

}
