package com.reactlibrary.socketWrapper.components.classes.notifications;


import com.facebook.react.bridge.Callback;

import java.util.Map;



import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_context.external_context.ExternalContext;

@ClassIdentifier(identification = "ReactClass")
public class ReactNotificationClass extends DataTrade {


    @MethodIdentifier(identification = "ReactMethod")
    public void reactMethod(String notification, NotificationerStatesBundle notificationerStatesBundle){
        ExternalContext externalContext = getExternalContext();
        Map<String,Callback> callbackFunctions = (Map<String, Callback>) externalContext.getContextObject("callbackFunctions").getObject();
        String[] parsedNotification = parseNotification(notification);

        if (callbackFunctions.containsKey(parsedNotification[0])){
           callbackFunctions.get(parsedNotification[0]).invoke(parsedNotification[1]);
        }
    }


    private String[] parseNotification(String notification){
        int indexSeperator = notification.indexOf("|");
        String reactMethodIdent = notification.substring(0,indexSeperator);
        String reactNotification = notification.substring(indexSeperator+1);

        return new String[] {reactMethodIdent,reactNotification};
    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
