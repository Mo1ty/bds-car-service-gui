package org.but.feec.carservice.controller.injectioncontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.but.feec.carservice.api.CarDetailedView;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.config.DataSourceConfig;
import org.but.feec.carservice.controller.carcontrollers.CarFindAllController;
import org.but.feec.carservice.controller.carcontrollers.CarReadController;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.exceptions.DataAccessException;
import org.but.feec.carservice.service.CarEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InjectionProcessController {

    private static final Logger logger = LoggerFactory.getLogger(InjectionProcessController.class);

    public InjectionProcessController() {
    }

    @FXML
    private TableColumn<CarStandardView, Integer> carID;
    @FXML
    private TableColumn<CarStandardView, String> carBrand;
    @FXML
    private TableColumn<CarStandardView, Integer> parkingID;
    @FXML
    private TableColumn<CarStandardView, String> carModel;
    @FXML
    private TableColumn<CarStandardView, String> carNumber;
    @FXML
    private TableColumn<CarStandardView, Integer> rentCost;
    @FXML
    private TableView<CarStandardView> systemCarsTableView;
//    @FXML
//    public MenuItem exitMenuItem;

    private CarEditService carEditService;
    private CarRepository carRepository;


    @FXML
    private void initialize() {
        logger.info("InjectionProcessController initialized");
        buildCarTable();
    }


    public void buildCarTable(){
        carRepository = new CarRepository();
        carEditService = new CarEditService(carRepository);
//        GlyphsDude.setIcon(exitMenuItem, FontAwesomeIcon.CLOSE, "1em");

        String param = InjectionStartController.parameters;

        carID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("carsID"));
        carBrand.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("brand"));
        parkingID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("parkingID"));
        carModel.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("model"));
        carNumber.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("carsNumber"));
        rentCost.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("rentCost"));

        ObservableList<CarStandardView> observableCarsList;
        CarStandardView car = vulnerableFindCar(param);
        observableCarsList = FXCollections.observableArrayList(car);

        systemCarsTableView.setItems(observableCarsList);
        systemCarsTableView.getSortOrder().add(carID);
        systemCarsTableView.getSelectionModel().getSelectedItem();
//        initializeTableViewSelection();
//        loadIcons();
    }

    public CarStandardView vulnerableFindCar(String carsNumber) {
        String text = "SELECT * FROM car_service.cars WHERE cars_number = " + "'" + carsNumber + "'";
        try (Connection connection = DataSourceConfig.getConnection();
             Statement Statement = connection.createStatement();
        ){
            try (ResultSet resultSet = Statement.executeQuery(text)) {
                if (resultSet.next()) {
                    return carRepository.toCarStandardView(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DataAccessException("Finding car by number failed.", e);
        }
        logger.info("Car was not found.");
        return null;
    }
}
