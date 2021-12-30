package org.but.feec.carservice.controller.injectioncontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.carservice.App;
import org.but.feec.carservice.api.SuccessAndFailAlerts;
import org.but.feec.carservice.controller.carcontrollers.CarReadController;
import org.but.feec.carservice.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class InjectionStartController {

    public InjectionStartController() {

    }

    private final Logger logger = LoggerFactory.getLogger(CarReadController.class);

    @FXML
    private TextField carNumberTextfield;

    @FXML
    private Button enterButton;

    public static String parameters;

    private void initialize()
    {
        logger.info("SQLInjectionStart initialized");
    }

    public void carRead() {

        try {
            parameters = carNumberTextfield.getText();
            tableCreate();

        } catch (Exception e) {
            SuccessAndFailAlerts.failAlarm("Creating a car met an exception and");
//          ResourceNotFoundException | DataAccessException
        }
    }

    public void tableCreate(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("SQLInjector.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
