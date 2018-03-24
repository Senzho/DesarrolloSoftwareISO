/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Marioolopez
 */
public class VentanaPrincipalDirector extends Application {
    private Usuario usuario;
    private Profesor profesor;
    
    public VentanaPrincipalDirector(Usuario usuario, Profesor profesor) {
        try {
            this.usuario = usuario;
            this.profesor = profesor;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Sesiones/VentanaPrincipalDirector.fxml"));
        AnchorPane root = loader.load();//ancho largo
        VentanaPrincipalDirectorController controller = loader.getController();
        controller.setUsuario(this.usuario, this.profesor);
        Scene scene = new Scene(root, 768, 468);
        primaryStage.setTitle("Ventana principal director");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
