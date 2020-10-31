package xyz.sheldonleung.controller;

import java.io.File;
import java.io.IOException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import xyz.sheldonleung.service.DownloadWallpaperService;
import xyz.sheldonleung.service.impl.DownloadWallpaperServiceImpl;

/**
 * 首页控制器
 * <p>
 * Create at 2020/10/31 00:14
 *
 * @author Sheldon Leung
 * @version 0.1
 */
public class IndexController {

    @FXML
    public Button selectPathButton;

    @FXML
    public Button downloadButton;

    @FXML
    public Label downloadPathLabel;

    @FXML
    public Label imagePathLabel;

    String savePath = null;

    @FXML
    private void downloadWallpaper() {
        downloadButton.setOnMouseClicked(mouseEvent -> {
            if (savePath != null) {
                String imagePath = new DownloadWallpaperServiceImpl().downloadBingWallpaper(savePath);
                imagePathLabel.setText("The Damn Image Path: " + imagePath);
            } else {
                imagePathLabel.setText("Damn FAIL!");
            }
        });
    }

    @FXML
    private void selectDownloadPath() {
        selectPathButton.setOnMouseClicked(mouseEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Save The Damn Wallpaper");
            File file = directoryChooser.showDialog(null);
            if (file != null) {
                try {
                    savePath = file.getCanonicalPath() + "\\";
                } catch (IOException e) {
                    downloadPathLabel.setText("Damn FAIL!");
                    e.printStackTrace();
                }
                downloadPathLabel.setText("The Damn Download Path: " + savePath);
            }
            System.out.println(savePath);
        });
    }
}
