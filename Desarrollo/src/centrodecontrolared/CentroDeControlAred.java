/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrodecontrolared;

import LogicaNegocio.Egresos.PanelIngresosEgresosController;
import LogicaNegocio.Sesiones.VentanaInicioSesion;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author marioolopez
 */
public class CentroDeControlAred extends Application {

    //@Override
    // public void start(Stage primaryStage) {
    //new VentanaInicioSesion();
    //new VentanaRegistrarPagoAlumno(1);
    //new PanelHistorialPagosAlumno(2);
    //}
    public void start(Stage primaryStage) throws IOException {//PanelIngresosEgresosController
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelIngresosEgresos.fxml"));
        AnchorPane root = loader.load();
        PanelIngresosEgresosController controller = loader.getController();
        controller.inicializarPaneles();
        Scene scene = new Scene(root, 568, 331);
        primaryStage.setTitle("Consultar promociones");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
