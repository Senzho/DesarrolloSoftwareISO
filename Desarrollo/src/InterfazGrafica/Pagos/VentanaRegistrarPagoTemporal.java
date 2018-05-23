package InterfazGrafica.Pagos;

import LogicaNegocio.Pagos.VentanaRegistrarPagoTemporalController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaRegistrarPagoTemporal extends Application{
    public VentanaRegistrarPagoTemporal(){
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoTemporal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaRegistrarPagoTemporal.fxml"));
        AnchorPane root = loader.load();
        VentanaRegistrarPagoTemporalController controller = loader.getController();
        controller.inicializar(primaryStage);
        Scene scene = new Scene(root, 450, 250);
        primaryStage.setTitle("Registro temporal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
