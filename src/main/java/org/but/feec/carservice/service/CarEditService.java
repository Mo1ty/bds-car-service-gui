package org.but.feec.carservice.service;

import com.zaxxer.hikari.HikariDataSource;
import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarEditService {

    private static HikariDataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public static boolean startCreation(String brand, Integer parkingId, String model, String carNumber, Integer rentCost)
    {
        logger.info("Creation started!");
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO car_service.cars VALUES (DEFAULT, ?, ?, ?, ?, ?);")
        ) {
            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, parkingId);
            preparedStatement.setString(3, model);
            preparedStatement.setString(4, carNumber);
            preparedStatement.setInt(5, rentCost);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("No access to the data.", e);
        }
        return true;
    }
}
