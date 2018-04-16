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
public class PanelHistorialPagosAlumno extends Application{
    private Stage stage;
    private int idAlumno;
    private VentanaRegistrarPagoAlumnoController controller; 
    
    public PanelHistorialPagosAlumno(int idAlumno){
        this.idAlumno = idAlumno;
        try {
            this.start(stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getController (VentanaRegistrarPagoAlumnoController controller){
        this.controller = controller; 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelHistorialPagosAlumno.fxml"));
        AnchorPane root = loader.load();
        PanelHistorialPagosAlumnoController controller = loader.getController();
        controller.setStage(this.stage);
        controller.setIdAlumno(idAlumno);
        controller.inicializarPanelPagos();
        Scene scene = new Scene(root, 601, 456);
        primaryStage.setTitle("Historial de pagos ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
