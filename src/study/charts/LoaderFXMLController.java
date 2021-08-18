/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.charts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import study.charts.MainFrameController;

/**
 * FXML Controller class
 *
 * @author MATHEUSDACOSTACAFFER
 */
public class LoaderFXMLController implements Initializable {

    @FXML
    private Button Get;

    @FXML
    private TextField Diretorio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Get.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               File file = new File(Diretorio.getText());
                System.out.println(Diretorio.getText());
                if (file.exists()) {
                    study.charts.StudyCharts.setPath(Diretorio.getText());
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
                        Parent root = loader.load();

                        Scene scene = study.charts.StudyCharts.getScene();

                        Stage stage = (Stage) scene.getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                    } catch (IOException ex) {
                        Logger.getLogger(LoaderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                Diretorio.setText("");
                }
            });
    }

}
