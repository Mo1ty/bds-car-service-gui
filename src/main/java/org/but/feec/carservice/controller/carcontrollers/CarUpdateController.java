package org.but.feec.carservice.controller.carcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.service.CarEditService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.valueOf;

public class CarUpdateController {
    public CarUpdateController() {

    }

    private final Logger logger = LoggerFactory.getLogger(CarUpdateController.class);

    @FXML
    private TextField brandTextfield;

    @FXML
    private TextField parkingIdTextfield;

    @FXML
    private TextField modelTextfield;

    @FXML
    private TextField carNumberTextfield;

    @FXML
    private TextField rentCostTextfield;

    @FXML
    private Button enterButton;

    @FXML
    private Button cancelButton;

    private ValidationSupport validation;

    private CarEditService carEditService;
    private CarRepository carRepository;

    @FXML
    private void initialize() {

        logger.info("Initializing CarUpdateController...");

//      initializeServices();
//        initializeValidations();

        logger.info("CarUpdateController initialized");
    }

    private void initializeValidations() {
        validation = new ValidationSupport();
        validation.registerValidator(brandTextfield, Validator.createEmptyValidator("Brand must not be empty."));
        validation.registerValidator(parkingIdTextfield, Validator.createEmptyValidator("Parking id must not be empty."));
        validation.registerValidator(modelTextfield, Validator.createEmptyValidator("Model must not be empty."));
        validation.registerValidator(carNumberTextfield, Validator.createEmptyValidator("Car number must not be empty."));
        validation.registerValidator(rentCostTextfield, Validator.createEmptyValidator("Rent cost must be assigned."));
        enterButton.disableProperty().bind(validation.invalidProperty());
    }

    public void carUpdating() {


        String brand = brandTextfield.getText();
        Integer parkingId = valueOf(parkingIdTextfield.getText());
        String model = modelTextfield.getText();
        String carNumber = carNumberTextfield.getText();
        Integer rentCost = valueOf(rentCostTextfield.getText());

        try {
            CarStandardView carInfo = CarRepository.findCar(carNumber);
            if(carInfo == null){
                SuccessAndFailAlerts.failAlarm("Updating a non-existing car");
                return;
            }
            else {
                boolean updateSucceeded = false;
            }
            boolean updateSucceeded = CarRepository.carUpdating(brand, parkingId, model, carNumber, rentCost);
            logger.info("Transaction happened!");
            if (updateSucceeded) {
                SuccessAndFailAlerts.successAlarm("Creation"); // put success alert & close both scenes
                logger.info("Success?");
            } else {
                SuccessAndFailAlerts.failAlarm("Creation"); // put fail alert & close both scenes
            }
        } catch (Exception e) {
            SuccessAndFailAlerts.failAlarm("Creating a car met an exception and");
//          ResourceNotFoundException | DataAccessException
        }
    }
}


