package org.example.server.service;

import org.example.dao.UserCredential;
import org.example.dto.BasicUserData;
import org.example.dto.ServerResponse;
import org.example.dto.UserToken;
import org.example.server.model.UserModel;
import org.example.server.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private static final UserRepository userRepository = new UserRepository();

    public static ServerResponse getUserBasicData(int userId) {
        BasicUserData basicUserData = userRepository.fetchBasicUserDataById(userId);
        return ServerResponseService.createPositiveServerResponse(basicUserData);
    }

    public static ServerResponse login(UserCredential userCredential){
        Optional<UserModel> givenCredentials = userRepository.fetchUserCredentials(userCredential.getMail(),
                userCredential.getPassword());
        if(givenCredentials.isPresent()){

            return ServerResponseService.createPositiveServerResponse(new UserToken(givenCredentials.get().getId()));


        }else{
            return ServerResponseService.notFoundServerResponse();
        }
    }
}
