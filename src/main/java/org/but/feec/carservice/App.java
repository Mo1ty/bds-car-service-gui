package org.but.feec.carservice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class App extends Application {

    private FXMLLoader loader;
    private VBox mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            mainStage = loader.load();

            primaryStage.setTitle("BDS JavaFX Demo");
            Scene scene = new Scene(mainStage);
            setUserAgentStylesheet(STYLESHEET_MODENA);
//            String myStyle = getClass().getResource("css/myStyle.css").toExternalForm();
//            scene.getStylesheets().add(myStyle);

//            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("logos/vut.jpg")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            // fix catch exception...
        }
    }

}