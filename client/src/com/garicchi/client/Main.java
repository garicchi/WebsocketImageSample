package com.garicchi.client;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

public class Main {

    static Session session;
    static WebsocketMessenger messenger;
    public static void main(String[] args) {
	    messenger = new WebsocketMessenger();
        messenger.connect("ws://127.0.0.1:8080/ws");
        messenger.sendFile("file.png");

    }
}
