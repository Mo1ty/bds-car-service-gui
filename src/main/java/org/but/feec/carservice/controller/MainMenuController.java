package org.but.feec.carservice.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
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

import java.awt.*;
import java.io.IOException;
import java.net.URI;

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
            Scene scene = new Scene(fxmlLoader.load(), 270, 370);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }

    public void carUpdateButtonPressed()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarUpdating.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 258, 451);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }

    public void carAlterUpdateButtonPressed()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarNumberUpdating.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 260, 330);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }

    public void carDeleteButtonPressed()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarDeleting.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 190, 240);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }

    public void carReadButtonPressed()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/CarReading.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 190, 240);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }
    }

    public void sqlInject()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("SQLInjections.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 190, 240);
            Stage stage = new Stage();
            stage.setTitle("Set Parameters");
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            // fix it later
        }

    }

    public void openLink() throws IOException {
        String s = "https://youtu.be/dQw4w9WgXcQ";
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create(s));
    }

    public void openHelpLink() throws IOException {
        String s = "https://github.com/Mo1ty/bds-car-service-gui";
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create(s));
    }


    public void Backup() {
        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + currentPath);
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            final String cmd = "pg_dump  --format=p --exter \"postgres\" db_name > \"" + currentPath + "\\" + "car_service.sql" + "\"";
            Process p = rt.exec(cmd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
