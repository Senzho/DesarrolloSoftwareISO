package InterfazGrafica.Pagos;

import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.Renta;
import LogicaNegocio.Pagos.VentanaEditarRentaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaEditarRenta extends Application{
    private Renta renta;
    private Lanzador lanzador;
    
    public VentanaEditarRenta(Renta renta, Lanzador lanzador){
        try {
            this.renta = renta;
            this.lanzador = lanzador;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaEditarRenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaEditarRenta.fxml"));
        AnchorPane root = loader.load();
        VentanaEditarRentaController controller = loader.getController();
        controller.iniciar(this.renta, this.lanzador);
        Scene scene = new Scene(root, 450, 300);
        primaryStage.setTitle("Renta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
