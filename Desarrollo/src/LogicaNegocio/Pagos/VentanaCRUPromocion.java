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
public class VentanaCRUPromocion extends Application{
    private Stage stage;
    private int idProfesor;
    private Promocion promocion;
    
    public VentanaCRUPromocion(int idProfesor){
        this.idProfesor = idProfesor;
        try {
            this.start(stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUPromocion(int idProfesor, Promocion promocion){
        this.idProfesor = idProfesor;
        this.promocion = promocion;
        try {
            this.start(stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaCRUPromocion.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUPromocionController controller = loader.getController();
        controller.setStage(this.stage);
        controller.setIdProfesor(idProfesor);
        if(promocion!= null){
            controller.setPromocion(this.promocion);
        }
        Scene scene = new Scene(root, 568, 331);
        primaryStage.setTitle("Consultar promociones");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}