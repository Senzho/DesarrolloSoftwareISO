/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrodecontrolared;

import LogicaNegocio.Sesiones.VentanaInicioSesion;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author marioolopez
 */
public class CentroDeControlAred extends Application {

    public void start(Stage primaryStage) throws IOException {
        new VentanaInicioSesion();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
