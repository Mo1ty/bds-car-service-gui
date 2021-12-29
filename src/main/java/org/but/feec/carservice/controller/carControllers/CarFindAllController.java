package org.but.feec.carservice.controller.carControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.service.CarEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarFindAllController {

    private static final Logger logger = LoggerFactory.getLogger(CarFindAllController.class);

    public CarFindAllController() {
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

        List<Object> x = new ArrayList<Object>();
        x.add(carID);
        x.add(carBrand);
        x.add(parkingID);
        x.add(carModel);
        x.add(carNumber);
        x.add(rentCost);

        buildCarTable();

        logger.info("CarDetailedViewController initialized");
    }


    public void buildCarTable(){
        carRepository = new CarRepository();
        carEditService = new CarEditService(carRepository);
//        GlyphsDude.setIcon(exitMenuItem, FontAwesomeIcon.CLOSE, "1em");

        carID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("carsID"));
        carBrand.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("brand"));
        parkingID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("parkingID"));
        carModel.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("model"));
        carNumber.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("carsNumber"));
        rentCost.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("rentCost"));

        List<CarStandardView> cars = carRepository.getCarsStandardViewList();
        ObservableList<CarStandardView> observableCarsList = FXCollections.observableArrayList(cars);
        systemCarsTableView.setItems(observableCarsList);

        systemCarsTableView.getSortOrder().add(carID);

        systemCarsTableView.getSelectionModel().getSelectedItem();
//        initializeTableViewSelection();
//        loadIcons();
    }
}
