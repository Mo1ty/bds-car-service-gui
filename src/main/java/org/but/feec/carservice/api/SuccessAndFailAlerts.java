package org.but.feec.carservice.api;

import javafx.scene.control.Alert;
import org.but.feec.carservice.controller.MainMenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuccessAndFailAlerts {

    private static final Logger logger = LoggerFactory.getLogger(SuccessAndFailAlerts.class);

    public static void successAlarm(String act)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(act + " was successful!");
        logger.info("Alarm shown!");

        alert.showAndWait();
    }

    public static void failAlarm(String act) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fail!");
        alert.setHeaderText(act + " failed! Try again!");

        alert.showAndWait();
    }
}
