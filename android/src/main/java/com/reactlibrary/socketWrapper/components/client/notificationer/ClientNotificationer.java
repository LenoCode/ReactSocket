package com.reactlibrary.socketWrapper.components.client.notificationer;

import java.io.IOException;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class ClientNotificationer extends NotificationerActions {
    public ClientNotificationer(DataTradeModel[] objects, ExternalContextInitializator externalContextInitializator) {
        super(objects);
        setupExternalContextInitializator(externalContextInitializator);
    }

    @Override
    public void exceptionHandler(ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {

    }
}
