package org.but.feec.carservice.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.service.CarEditService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.valueOf;

public class CarCreatingController {
    public CarCreatingController() {

    }

    private final Logger logger = LoggerFactory.getLogger(CarCreatingController.class);

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

    @FXML
    private void initialize() {

        logger.info("Attempt to initialize CarCreatingClassController...");



//      initializeServices();
//        initializeValidations();

        logger.info("CarCreatingClassController initialized");
    }

    private void initializeValidations() {
        validation = new ValidationSupport();
        validation.registerValidator(modelTextfield, Validator.createEmptyValidator("The model must not be empty."));
        validation.registerValidator(carNumberTextfield, Validator.createEmptyValidator("The car number must not be empty."));
        validation.registerValidator(rentCostTextfield, Validator.createEmptyValidator("Rent cost must be assigned."));
        enterButton.disableProperty().bind(validation.invalidProperty());
    }

    public void enterButtonPressed() {
        String brand = brandTextfield.getText();
        Integer parkingId = valueOf(parkingIdTextfield.getText());
        String model = modelTextfield.getText();
        String carNumber = carNumberTextfield.getText();
        Integer rentCost = valueOf(rentCostTextfield.getText());

        try {
            boolean creationSucceded = CarEditService.startCreation(brand, parkingId, model, carNumber, rentCost);
            logger.info("Transaction happened!");
            if (creationSucceded) {
                SuccessAndFailAlerts.successAlarm("Creation"); // put success alert & close both scenes
                logger.info("Success?");
            } else {
                SuccessAndFailAlerts.failAlarm("Creation"); // put fail alert & close both scenes
            }
        } catch (Exception e) {
            SuccessAndFailAlerts.failAlarm("Creation with exception");
//          ResourceNotFoundException | DataAccessException
        }
    }
}

