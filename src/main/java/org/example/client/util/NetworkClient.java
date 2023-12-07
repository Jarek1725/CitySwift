package org.example.client.util;

import org.example.common.dao.ClientRequest;
import org.example.common.dto.ServerResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkClient {
    private final String serverAddress = "127.0.0.1";
    private final int serverPort = 8081;

    public NetworkClient() {
    }

    public ServerResponse sendRequest(ClientRequest request) throws Exception {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            outputStream.writeObject(request);
            return (ServerResponse) inputStream.readObject();
        }
    }
}