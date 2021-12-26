package org.but.feec.carservice.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zaxxer.hikari.HikariDataSource;
import org.but.feec.carservice.api.FullLoginData;
import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.data.PersonRepository;

import java.sql.*;
import java.util.ArrayList;

import org.but.feec.carservice.exceptions.DataAccessException;
import org.but.feec.carservice.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthService {

    private static HikariDataSource dataSource;

    private PersonRepository personRepository;

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        FullLoginData userData = findPersonToAuthenticate(username);

        if (userData.getEmail() == null || userData.getPasswordHash() == null) {
            throw new ResourceNotFoundException("Provided username is not found.");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), userData.getPasswordHash());
        return result.verified;
    }

    public FullLoginData findPersonToAuthenticate(String email) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, password_hash FROM car_service.clients c JOIN car_service.user_login_data l ON c.clients_id = l.clients_id WHERE email = ?")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return FullLoginData.turnIntoLoginData(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }
}
