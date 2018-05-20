/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Desktop
 */
public class VentanaReciboPago  extends Application{
    private Stage stage;
    private Alumno alumno;
    private PagoAlumno pagoAlumno;
    
    public VentanaReciboPago(Alumno alumno, PagoAlumno pagoAlumno){
        this.alumno = alumno;
        this.pagoAlumno = pagoAlumno;
        try {
            this.start(this.stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaReciboPago.fxml"));
        AnchorPane root = loader.load();
        VentanaReciboPagoController controller = loader.getController();
        controller.setStage(this.stage);
        controller.setAlumno(alumno);
        controller.setPagoAlumno(pagoAlumno);
        controller.generarDatosRecibo();
        Scene scene = new Scene(root, 316, 260);
        primaryStage.setTitle("Recibo de pago");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
