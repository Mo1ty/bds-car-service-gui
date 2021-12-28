package org.but.feec.carservice.api;

import javafx.scene.control.Alert;

public class SuccessAndFailAlerts {

    public static void successAlarm(String act)
    {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(act + " was successful!");

    }

    public static void failAlarm(String act) {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fail!");
        alert.setHeaderText(act + " failed! Try again!");
    }
}
