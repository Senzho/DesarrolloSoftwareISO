package LogicaNegocio.Sesiones;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaInicioSesion extends Application {

    public VentanaInicioSesion() {
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Sesiones/VentanaInicioSesion.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 370, 165);
        primaryStage.setTitle("Inicio sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
