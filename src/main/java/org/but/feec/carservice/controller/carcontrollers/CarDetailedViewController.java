package org.but.feec.carservice.controller.carcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.but.feec.carservice.api.CarDetailedView;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.service.CarEditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarDetailedViewController {
    private static final Logger logger = LoggerFactory.getLogger(CarFindAllController.class);

    public CarDetailedViewController() {
    }

    @FXML
    private TableColumn<CarDetailedView, Object> carID;
    @FXML
    private TableColumn<CarDetailedView, String> carModel;
    @FXML
    private TableColumn<CarDetailedView, String> carNumber;
    @FXML
    private TableColumn<CarDetailedView, Integer> rentCost;
    @FXML
    private TableColumn<CarDetailedView, Integer> parkingID;
    @FXML
    private TableColumn<CarDetailedView, String> city;
    @FXML
    private TableColumn<CarDetailedView, String> street;
    @FXML
    private TableColumn<CarDetailedView, String> house;
    @FXML
    private TableColumn<CarDetailedView, String> carBrand;
    @FXML
    private TableColumn<CarDetailedView, String> supportMail;
    @FXML
    private TableColumn<CarDetailedView, String> supportNumber;
    @FXML
    private TableView<CarDetailedView> systemCarsTableView;
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

        carID.setCellValueFactory(new PropertyValueFactory<CarDetailedView, Object>("carsID"));
        carModel.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("model"));
        carNumber.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("carsNumber"));
        rentCost.setCellValueFactory(new PropertyValueFactory<CarDetailedView, Integer>("rentCost"));
        parkingID.setCellValueFactory(new PropertyValueFactory<CarDetailedView, Integer>("parkingID"));
        city.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("city"));
        street.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("street"));
        house.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("house"));
        carBrand.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("brand"));
        supportMail.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("supportMail"));
        supportNumber.setCellValueFactory(new PropertyValueFactory<CarDetailedView, String>("supportNumber"));

        ObservableList<CarDetailedView> observableCarsList;

        CarDetailedView car = CarRepository.detailedCarSearch(param);
        observableCarsList = FXCollections.observableArrayList(car);

//        if (param == null){
//            List<CarStandardView> cars = carRepository.getCarsStandardViewList();
//            observableCarsList = FXCollections.observableArrayList(cars);
//        }
//        else{
//            CarStandardView car = CarRepository.findCar(param);
//            observableCarsList = FXCollections.observableArrayList(car);
//        }
        systemCarsTableView.setItems(observableCarsList);

        systemCarsTableView.getSortOrder().add(carID);

        systemCarsTableView.getSelectionModel().getSelectedItem();
//        initializeTableViewSelection();
//        loadIcons();
    }
}
