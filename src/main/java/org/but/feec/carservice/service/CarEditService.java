package org.but.feec.carservice.service;

import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarEditService {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public static boolean startCreation(String brand, String parkingId, String model, String carNumber, String rentCost)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO car_service.cars VALUES (DEFAULT, ?, ?, ?, ?, ?);")
        ) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, parkingId);
            preparedStatement.setString(1, model);
            preparedStatement.setString(1, carNumber);
            preparedStatement.setString(1, rentCost);

        }
        catch (SQLException e) {
            throw new DataAccessException("No access to the data.", e);
        }
        return true;
    }
}
