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

public class CarNumberUpdatingController {


    public CarNumberUpdatingController() {

    }

    private final Logger logger = LoggerFactory.getLogger(CarUpdateController.class);

    @FXML
    private TextField oldCarNumberTextfield;

    @FXML
    private TextField newCarNumberTextfield;

    @FXML
    private Button enterButton;

    private ValidationSupport validation;

    private CarEditService carEditService;
    private CarRepository carRepository;

    @FXML
    private void initialize() {

        logger.info("Initializing CarNumberUpdatingController...");


        logger.info("CarNumberUpdatingController initialized");
    }

    public void carNumberUpdating() {


        String oldCarNumber = oldCarNumberTextfield.getText();
        String newCarNumber = newCarNumberTextfield.getText();

        if(CarRepository.findCar(newCarNumber) == null)
        {
            try {
                CarStandardView carInfo = CarRepository.findCar(oldCarNumber);

                boolean updateSucceeded = false;

                if(carInfo == null){
                    SuccessAndFailAlerts.failAlarm("Updating a non-existing car");
                    return;
                }

                updateSucceeded = CarRepository.carAlternativeUpdating(oldCarNumber, carInfo.getBrand(), carInfo.getParkingID(),
                        carInfo.getModel(), newCarNumber, carInfo.getRentCost());

                logger.info("Transaction happened!");
                if (updateSucceeded) {
                    SuccessAndFailAlerts.successAlarm("Creation"); // put success alert & close both scenes
                    logger.info("Success?");
                } else {
                    SuccessAndFailAlerts.failAlarm("Creation"); // put fail alert & close both scenes
                }
            } catch (Exception e) {
                SuccessAndFailAlerts.failAlarm("Creating a car met an exception and");
            }
        }
        else{
                SuccessAndFailAlerts.failAlarm("Car with this number already exists! Updating");
        }
    }
}
