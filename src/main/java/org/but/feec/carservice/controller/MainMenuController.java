package org.but.feec.carservice.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.but.feec.carservice.App;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainMenuController {

    private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);

    @FXML
    private Button carReadButton;

    @FXML
    private Button carCreateButton;

    @FXML
    private Button carUpdateButton;

    @FXML
    private Button carDeleteButton;

    @FXML
    private Button carDetailedInfoButton;

    @FXML
    private Button carFindByFilterButton;







    @FXML
    private Button clientsReadButton;

    @FXML
    private Button clientsCreateButton;

    @FXML
    private Button clientsUpdateButton;

    @FXML
    private Button clientsDeleteButton;

    @FXML
    private Button rentCostTextfield;

    @FXML
    private Button clientsDetailedInfoButton;

    @FXML
    private Button clientsSetRolesButton;







    private ValidationSupport validation;

    @FXML
    private void initialize() {
        logger.info("MainMenuController initialized");
    }


    public void carCreateButtonPressed()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarCreating.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 251, 315);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }





}