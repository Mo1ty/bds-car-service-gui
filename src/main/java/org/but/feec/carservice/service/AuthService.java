package org.but.feec.carservice.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zaxxer.hikari.HikariDataSource;
import org.but.feec.carservice.data.PersonRepository;

import java.sql.*;
import java.util.ArrayList;

import org.but.feec.carservice.exceptions.DataAccessException;
import org.but.feec.carservice.exceptions.ResourceNotFoundException;

public class AuthService {

    private static HikariDataSource dataSource;

    private PersonRepository personRepository;

    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        ArrayList<String> userData = findPersonToAuthenticate(username);

        if (userData.get(0) == null || userData.get(1) == null) {
            throw new ResourceNotFoundException("Provided username is not found.");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), userData.get(1));
        return result.verified;
    }

    public ArrayList<String> findPersonToAuthenticate(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select email, password_hash from car_service.clients c " +
                             "join car_service.user_login_data l " +
                             "on c.clients_id = l.clients_id where email = ?")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<String> userData = new ArrayList<String>();
                userData.add(resultSet.getString("email"));
                userData.add(resultSet.getString("password_hash"));
                return userData;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
    }
}
