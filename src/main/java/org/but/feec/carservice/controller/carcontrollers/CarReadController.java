package org.but.feec.carservice.controller.carcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.carservice.App;
import org.but.feec.carservice.api.CarStandardView;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.data.CarRepository;
import org.but.feec.carservice.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.Integer.valueOf;

public class CarReadController {

    public CarReadController() {

    }

    private final Logger logger = LoggerFactory.getLogger(CarReadController.class);

    @FXML
    private TextField carNumberTextfield;

    @FXML
    private CheckBox detailedView;

    @FXML
    private CheckBox findAll;

    @FXML
    private Button enterButton;

    public static String parameters;


    private void initialize()
    {
        logger.info("CarReadController initialized");
    }

    public void carRead() {

        try {
            if(findAll.isSelected()){
                tableCreate();
            }
            else
            {
                parameters = carNumberTextfield.getText();
                String carNumber = carNumberTextfield.getText();
                CarStandardView carInfo = CarRepository.findCar(carNumber);
                if(carInfo == null)
                {
                    SuccessAndFailAlerts.failAlarm("Car was not found. Searching");
                }
                else{
                    tableCreate();
                }
            }
        } catch (Exception e) {
            SuccessAndFailAlerts.failAlarm("Creating a car met an exception and");
//          ResourceNotFoundException | DataAccessException
        }
    }

    public void tableCreate(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarFindAll.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 189, 241);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
