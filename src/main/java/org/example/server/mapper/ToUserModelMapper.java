package org.example.server.mapper;


import org.example.server.interfaces.RowMapper;
import org.example.server.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToUserModelMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("mail"),
                rs.getString("mobile"),
                rs.getDate("date_of_birth").toLocalDate()
        );
    }
}
