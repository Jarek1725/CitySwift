package org.example.server.util;

import org.example.common.dao.ClientRequest;
import org.example.common.dao.ParticularArea;
import org.example.common.dao.UserCredential;
import org.example.common.dto.ServerResponse;
import org.example.server.service.AddressService;
import org.example.server.service.UserService;

public class HandleClientAction {
    public static ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

        switch (clientRequest.getAction()) {
            case "getUserBasicData":
                serverResponse = UserService.getUserBasicData((int) clientRequest.getData());
                break;
            case "login":
                serverResponse = UserService.login((UserCredential) clientRequest.getData());
                break;
            case "getUsersLivingInArea":
                serverResponse = AddressService.ifUsersInParticularArea((ParticularArea) clientRequest.getData());
                break;
            default:
                serverResponse.setResultCode(404);
                serverResponse.setResultMessage("Action not found");
                break;
        }

        return serverResponse;
    }
}
