package controladoras;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ventanas.VentanaPrincipal;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraSplash implements Initializable {

    @FXML
    ImageView imagenFondo;

    @FXML
    ProgressBar progreso;

    Task tareaSecundaria;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instancias();

        progreso.progressProperty().bind(tareaSecundaria.progressProperty());

        FadeTransition transition = new FadeTransition(Duration.seconds(3),imagenFondo);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(tareaSecundaria).start();
            }
        });

        tareaSecundaria.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                Stage stage = (Stage) imagenFondo.getScene().getWindow();
                stage.hide();
            }
        });
    }

    private void instancias() {
        tareaSecundaria = new Task() {
            @Override
            protected Object call() throws Exception {

                for(int i = 0;i < 100;i++){
                    updateProgress(i,100);
                    Thread.sleep(100);
                }

                updateProgress(1,100);

                return null;
            }
        };
    }
}