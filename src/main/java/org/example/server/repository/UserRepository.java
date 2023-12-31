package org.example.server.repository;

import org.example.common.dto.BasicUserData;
import org.example.common.dto.CreateUserData;
import org.example.server.mapper.ToUserModelMapper;
import org.example.server.model.AddressModel;
import org.example.server.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    GenericRepository<UserModel> repository = new GenericRepository<>();
    AddressRepository addressRepository = new AddressRepository();
    ToUserModelMapper mapper = new ToUserModelMapper();

    public Optional<UserModel> fetchUserById(int userId) {
        String sql = "SELECT * FROM app_user WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public BasicUserData fetchBasicUserDataById(int userId){
        Optional<UserModel> userModel = fetchUserById(userId);
        List<AddressModel> addressModel = addressRepository.fetchAddressesByUserId(userId);
        return userModel.map(model -> new BasicUserData(model, addressModel)).orElseGet(() -> new BasicUserData(null, null));
    }

    public Optional<UserModel> fetchUserCredentials(String username, String password) {
        String sql = "SELECT * FROM app_user WHERE mail = ? and password=?  ";
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public int insertUser(CreateUserData createUserData) {
        String sql = "INSERT INTO app_user (mail, password, first_name, last_name, mobile, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(createUserData.getMail());
        params.add(createUserData.getPassword());
        params.add(createUserData.getFirstName());
        params.add(createUserData.getLastName());
        params.add(createUserData.getMobile());
        params.add(createUserData.getDateOfBirth());
        return repository.insert(sql, params);
    }

}
