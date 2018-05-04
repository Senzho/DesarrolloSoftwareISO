package InterfazGrafica.Pagos;

import LogicaNegocio.Pagos.VentanaRegistrarRentaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaRegistrarRenta extends Application{
    public VentanaRegistrarRenta(){
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarRenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaRegistrarRenta.fxml"));
        AnchorPane root = loader.load();
        VentanaRegistrarRentaController controller = loader.getController();
        controller.inicializar(primaryStage);
        Scene scene = new Scene(root, 450, 200);
        primaryStage.setTitle("Renta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
