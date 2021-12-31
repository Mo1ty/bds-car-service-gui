package org.but.feec.carservice.controller.carcontrollers;

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

import java.util.List;

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
        logger.info("CarDetailedViewController initialized");
        buildCarTable();
    }


    public void buildCarTable(){
        carRepository = new CarRepository();
        carEditService = new CarEditService(carRepository);
//        GlyphsDude.setIcon(exitMenuItem, FontAwesomeIcon.CLOSE, "1em");

        String param = CarReadController.parameters;

        carID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("carsID"));
        carBrand.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("brand"));
        parkingID.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("parkingID"));
        carModel.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("model"));
        carNumber.setCellValueFactory(new PropertyValueFactory<CarStandardView, String>("carsNumber"));
        rentCost.setCellValueFactory(new PropertyValueFactory<CarStandardView, Integer>("rentCost"));

        ObservableList<CarStandardView> observableCarsList;

        if (param == null){
            List<CarStandardView> cars = carRepository.getCarsStandardViewList();
            observableCarsList = FXCollections.observableArrayList(cars);
        }
        else{
            CarStandardView car = carRepository.findCar(param);
            observableCarsList = FXCollections.observableArrayList(car);
        }
        systemCarsTableView.setItems(observableCarsList);

        systemCarsTableView.getSortOrder().add(carID);

        systemCarsTableView.getSelectionModel().getSelectedItem();
//        initializeTableViewSelection();
//        loadIcons();
    }
}
