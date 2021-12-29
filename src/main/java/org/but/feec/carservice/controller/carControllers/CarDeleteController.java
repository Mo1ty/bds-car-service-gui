package org.but.feec.carservice.controller.carControllers;

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

public class CarDeleteController {

    private final Logger logger = LoggerFactory.getLogger(CarUpdateController.class);

    @FXML
    private TextField carNumberTextfield;

    @FXML
    private Button enterButton;

    private ValidationSupport validation;

    private CarEditService carEditService;
    private CarRepository carRepository;

    private void initialize() {

        logger.info("Initializing CarDeleteController...");

//      initializeServices();
        initializeValidations();

        logger.info("CarDeleteController initialized");
    }

    private void initializeValidations() {
        validation = new ValidationSupport();
        validation.registerValidator(carNumberTextfield, Validator.createEmptyValidator("Car number field must not be empty."));
        enterButton.disableProperty().bind(validation.invalidProperty());
    }

    public void carDeleting() {

        String carNumber = carNumberTextfield.getText();

        try {
            CarStandardView carInfo = CarRepository.findCar(carNumber);
            if(carInfo == null){
                SuccessAndFailAlerts.failAlarm("Deleting a non-existing car");
                return;
            }
            else {
                boolean deleteSucceeded = false;
            }
            boolean deleteSucceeded = CarRepository.carDeleting(carNumber);
            logger.info("Transaction happened!");
            if (deleteSucceeded) {
                SuccessAndFailAlerts.successAlarm("Delete"); // put success alert & close both scenes
                logger.info("Success?");
            } else {
                SuccessAndFailAlerts.failAlarm("Delete"); // put fail alert & close both scenes
            }
        } catch (Exception e) {
            SuccessAndFailAlerts.failAlarm("Deleting a car met an exception and");
//          ResourceNotFoundException | DataAccessException
        }
    }
}
