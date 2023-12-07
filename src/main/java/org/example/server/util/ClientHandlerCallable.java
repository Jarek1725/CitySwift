package org.example.server.util;



import org.example.common.dao.ClientRequest;
import org.example.common.dto.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHandlerCallable implements Callable<ServerResponse> {
    private Socket clientSocket;

    public ClientHandlerCallable(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public ServerResponse call() throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();
            System.out.println(clientRequest.getAction());
            ServerResponse response = HandleClientAction.handleClientAction(clientRequest);
            objectOutputStream.writeObject(response);
            return response;

        }
        catch (Exception e) {
            e.printStackTrace();
            ServerResponse response = new ServerResponse();
            response.setResultCode(500);
            response.setResultMessage("Internal Server Error");
            return response;
        }

        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
