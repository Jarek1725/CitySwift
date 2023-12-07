package org.example.server.repository;

import org.example.common.dto.ParticularArea;
import org.example.server.mapper.ToAddressModelMapper;
import org.example.server.model.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {
    GenericRepository<AddressModel> repository = new GenericRepository<>();
    ToAddressModelMapper mapper = new ToAddressModelMapper();

    public List<AddressModel> fetchAddressesByUserId(int userId) {
        String sql = "SELECT * FROM ADDRESS WHERE user_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public List<AddressModel> fetchUsersInAddressArea(ParticularArea userArea) {
        String sql = "SELECT * FROM ADDRESS WHERE postal_code = ?";
        List<Object> params = new ArrayList<>();
        params.add(userArea.getArea());
        return repository.fetchMultipleRow(sql, mapper, params);
    }
}
