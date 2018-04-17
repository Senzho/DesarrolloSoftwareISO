/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

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
public class VentanaRegistrarPagoAlumno extends Application {
    private int idProfesor;
   
    public VentanaRegistrarPagoAlumno(int idProfesor){
        this.idProfesor = idProfesor;
        try {
            start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaRegistrarPagoAlumno.fxml"));
        AnchorPane root = loader.load();//ancho largo
        VentanaRegistrarPagoAlumnoController controller = loader.getController();
        controller.setIdProfesor(this.idProfesor);
        controller.inicializarCombo();
        Scene scene = new Scene(root, 471, 231);
        primaryStage.setTitle("Registrar pago alumno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
