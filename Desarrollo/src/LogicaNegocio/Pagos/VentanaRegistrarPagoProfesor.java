package LogicaNegocio.Pagos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaRegistrarPagoProfesor extends Application{
    private Stage stage;
    
    public VentanaRegistrarPagoProfesor(){
        try {
            this.start(this.stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaRegistrarPagoProfesor.fxml"));
        AnchorPane root = loader.load();
        VentanaRegistrarPagoProfesorController controller = loader.getController();
        controller.setStage(this.stage);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Registrar pago");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
