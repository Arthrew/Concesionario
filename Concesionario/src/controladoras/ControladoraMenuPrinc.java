package controladoras;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraMenuPrinc implements Initializable {

    @FXML
    MenuItem informacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        informacion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Scene sceneActual = informacion.getGraphic().getScene();
                Stage stage = (Stage) sceneActual.getWindow();
                Parent root =null;
                try{
                    root = FXMLLoader.load(getClass()
                            .getResource("../layout/layout_menuP.fxml"));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                Scene scene = new Scene(root,sceneActual.getWidth(), sceneActual.getHeight());
                stage.setScene(scene);
            }
        });

    }
}