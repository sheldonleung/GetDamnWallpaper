package xyz.sheldonleung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 程序入口
 * <p>
 * Create at 2020/10/31 00:14
 *
 * @author Sheldon Leung
 * @version 0.1
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("index.fxml")));
        stage.setScene(new Scene(root, 500, 300));
        stage.setTitle("Download the damn wallpaper");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}