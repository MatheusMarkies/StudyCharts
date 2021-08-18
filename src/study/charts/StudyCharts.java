/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.charts;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author MATHEUSDACOSTACAFFER
 */
public class StudyCharts extends Application {
    
static String path = "";
static Scene scene;
        @Override
    public void start(Stage stage) throws Exception {
        
//        FXMLLoader FXML_MAIN  = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
//        Parent root = FXML_MAIN.load();
//        
//        Scene scene = new Scene(root);
//        
//        stage.setTitle("Raptor UI");
//        
//        stage.setScene(scene);
//        stage.show();
//        
//        MainFrameController FXML_Start = new MainFrameController();


        FXMLLoader FXML_MAIN  = new FXMLLoader(getClass().getResource("LoaderFXML.fxml"));
        Parent root = FXML_MAIN.load();
        
        scene = new Scene(root);
        
        stage.setTitle("Raptor UI");
        
        stage.setScene(scene);
        stage.show();
        
        LoaderFXMLController FXML_Start = new LoaderFXMLController();

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        StudyCharts.path = path;
    }

    public static Scene getScene() {
        return scene;
    }
    
}
