/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerguardarfoto;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Desktop
 */
public class ObtenerGuardarFoto extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/obtenerguardarfoto/VentanaImagenPrueba.fxml"));
        AnchorPane root = loader.load();//ancho largo
        VentanaImagenPruebaController controller = loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(root, 768, 468);
        primaryStage.setTitle("Ventana principal profesor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
