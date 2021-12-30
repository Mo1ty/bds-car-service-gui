package org.but.feec.carservice.data;

import org.but.feec.carservice.api.CarDetailedView;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.api.ClientsLoginView;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.exceptions.DataAccessException;
import org.but.feec.carservice.service.CarEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public static ClientsLoginView turnIntoLoginData(ResultSet resultSet) throws SQLException {
        ClientsLoginView person = new ClientsLoginView();
        person.setEmail(resultSet.getString("email"));
        person.setPasswordHash(resultSet.getString("password_hash"));
        return person;
    }

    public static CarStandardView toCarStandardView(ResultSet resultSet) throws SQLException {
        CarStandardView carStandardView = new CarStandardView();
        String x = resultSet.getString("brand");
        carStandardView.setCarsID(resultSet.getInt("cars_id"));
        carStandardView.setBrand(x);
        carStandardView.setParkingID(resultSet.getInt("parking_id"));
        carStandardView.setModel(resultSet.getString("model"));
        carStandardView.setCarsNumber(resultSet.getString("cars_number"));
        carStandardView.setRentCost(resultSet.getInt("rent_cost"));

        return carStandardView;
    }

    public List<CarStandardView> getCarsStandardViewList() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM car_service.cars;");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<CarStandardView> carsList = new ArrayList<>();
            while (resultSet.next()) {
                carsList.add(toCarStandardView(resultSet));
            }
            return carsList;
        } catch (SQLException e) {
            throw new DataAccessException("Cars standard view could not be loaded.", e);
        }
    }

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

    public static boolean carUpdating(String brand, Integer parkingId, String model, String carNumber, Integer rentCost) {

        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE car_service.cars " +
                             "SET brand = ?, " +
                             "parking_id = ?, " +
                             "model = ?, " +
                             "rent_cost = ? " +
                             "WHERE cars_number = ?;")
        ) {
            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, parkingId);
            preparedStatement.setString(3, model);
            preparedStatement.setInt(4, rentCost);
            preparedStatement.setString(5, carNumber);
            preparedStatement.executeUpdate();
            logger.info("Car updated successfully!");
        }
        catch (SQLException e) {
            SuccessAndFailAlerts.failAlarm("Updating a car met an SQL exception and ");
            logger.error("Exception: " + e);
            return false;
        }
        return true;
    }

    public static CarStandardView findCar(String carsNumber) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM car_service.cars WHERE cars_number = ?")
        ) {
            preparedStatement.setString(1, carsNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return CarRepository.toCarStandardView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Finding car by number failed.", e);
        }
        logger.info("Car was not found.");
        return null;
    }

    public static boolean carDeleting(String carNumber)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM car_service.cars WHERE cars_number = ?;")
        ) {
            preparedStatement.setString(1, carNumber);
            preparedStatement.executeUpdate();
            logger.info("Car deleted successfully!");
        }
        catch (SQLException e) {
            SuccessAndFailAlerts.failAlarm("Deleting a car met an SQL exception and ");
            logger.error("Exception: " + e);
            return false;
        }
        return true;
    }

    public static CarDetailedView detailedCarSearch(String carsNumber) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT cars_id, model, cars_number, rent_cost, p.parking_id, city, street, house, b.brand, support_mail, support_number " +
                             "FROM car_service.cars c " +
                             "JOIN car_service.parking p ON c.parking_id = p.parking_id " +
                             "JOIN car_service.brands_support_numbers b on c.brand = b.brand " +
                             "WHERE cars_number = ?;")
        ) {
            preparedStatement.setString(1, carsNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return CarRepository.toCarDetailedView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Finding car by number failed.", e);
        }
        logger.info("Car was not found.");
        return null;
    }

    public static CarDetailedView toCarDetailedView(ResultSet resultSet) throws SQLException {
        CarDetailedView carDetailedView = new CarDetailedView();
        carDetailedView.setCarsID(resultSet.getInt("cars_id"));
        carDetailedView.setModel(resultSet.getString("model"));
        carDetailedView.setCarsNumber(resultSet.getString("cars_number"));
        carDetailedView.setRentCost(resultSet.getInt("rent_cost"));
        carDetailedView.setParkingID(resultSet.getInt("parking_id"));
        carDetailedView.setCity(resultSet.getString("city"));
        carDetailedView.setStreet(resultSet.getString("street"));
        carDetailedView.setHouse(resultSet.getString("house"));
        carDetailedView.setBrand(resultSet.getString("brand"));
        carDetailedView.setSupportMail(resultSet.getString("support_mail"));
        carDetailedView.setSupportNumber(resultSet.getString("support_number"));

        return carDetailedView;
    }
}
