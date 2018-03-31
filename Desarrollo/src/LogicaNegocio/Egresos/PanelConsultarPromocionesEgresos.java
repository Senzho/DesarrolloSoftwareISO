/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import LogicaNegocio.Catalogos.VentanaCRUAlumno;
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
public class PanelConsultarPromocionesEgresos extends Application {
    
    public PanelConsultarPromocionesEgresos(){
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelConsultarPromocionesEgresos.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 774, 398);
        primaryStage.setTitle("Registro egreso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
