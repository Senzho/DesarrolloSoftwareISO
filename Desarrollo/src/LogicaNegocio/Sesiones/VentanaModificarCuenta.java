package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaModificarCuenta extends Application {
    private Usuario usuario;
    private Profesor profesor;
    //private Director director;
    public VentanaModificarCuenta(Usuario usuario, Profesor profesor){
        try {
            this.usuario = usuario;
            this.profesor = profesor;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaModificarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//idTipoUsuario llave foranea para saber el numero de profesor

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Sesiones/VentanaModificarCuenta.fxml"));
        AnchorPane root = loader.load();//ancho largo
        VentanaModificarCuentaController controller = loader.getController();
        controller.setUsuario(usuario,this.profesor);
        Scene scene = new Scene(root, 440, 314);
        primaryStage.setTitle("Modificar cuenta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
