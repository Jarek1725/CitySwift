package org.example.common.dto;

import org.example.server.model.AddressModel;
import org.example.server.model.UserModel;

import java.io.Serializable;
import java.util.List;

public class BasicUserData implements Serializable {
    private UserModel userModel;
    private List<AddressModel> addressModel;

    public BasicUserData() {
    }

    public BasicUserData(UserModel userModel, List<AddressModel> addressModel) {
        this.userModel = userModel;
        this.addressModel = addressModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<AddressModel> getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(List<AddressModel> addressModel) {
        this.addressModel = addressModel;
    }
}
