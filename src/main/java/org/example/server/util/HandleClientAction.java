package org.example.server.util;

import org.example.dao.ClientRequest;
import org.example.dao.ParticularArea;
import org.example.dao.UserCredential;
import org.example.dto.ServerResponse;
import org.example.server.service.AddressService;
import org.example.server.service.UserService;

public class HandleClientAction {
    public static ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

        switch (clientRequest.getAction()) {
            case "getUserBasicData":
                int data = (int) clientRequest.getData();
                serverResponse = UserService.getUserBasicData(data);
                break;
            case "login":
                UserCredential loginData = (UserCredential) clientRequest.getData();
                serverResponse = UserService.login(loginData);
                break;
            case "getUsersLivingInArea":
                ParticularArea newUserArea = (ParticularArea) clientRequest.getData();
                serverResponse = AddressService.ifUsersInParticularArea(newUserArea);
                break;
            default:
                serverResponse.setResultCode(404);
                serverResponse.setResultMessage("Action not found");
                break;
        }

        return serverResponse;
    }
}
