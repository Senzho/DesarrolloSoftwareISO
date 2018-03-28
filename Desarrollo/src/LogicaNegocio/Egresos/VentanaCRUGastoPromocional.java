/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

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
public class VentanaCRUGastoPromocional extends Application {
    private GastoPromocional gastoPromocional;
    
    public VentanaCRUGastoPromocional(){
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUGastoPromocional.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUGastoPromocional(GastoPromocional gastoPromocional){
        try {
            this.gastoPromocional = gastoPromocional;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUGastoPromocional.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/VentanaCRUGastoPromocional.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUGastoPromocionalController controller = loader.getController();
        controller.setGastoPromocional(this.gastoPromocional);
        Scene scene = new Scene(root, 337, 374);
        primaryStage.setTitle("Registro gasto promocional");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
