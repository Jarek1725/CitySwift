package org.example.server.service;

import org.example.common.dao.ParticularArea;
import org.example.common.dto.ListOfAddressInArea;
import org.example.common.dto.ServerResponse;
import org.example.server.model.AddressModel;
import org.example.server.repository.AddressRepository;

import java.util.List;

public class AddressService {
    private static final AddressRepository addressRepository = new AddressRepository();

    public static ServerResponse ifUsersInParticularArea(ParticularArea userArea){
        List<AddressModel> givenUserArea = addressRepository.fetchUsersInAddressArea(userArea);
        if(!givenUserArea.isEmpty()){

            return ServerResponseService.createPositiveServerResponse(new ListOfAddressInArea(givenUserArea));


        }else{
            return ServerResponseService.notFoundServerResponse();
        }
    }
}
