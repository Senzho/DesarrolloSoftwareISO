package LogicaNegocio.Catalogos;

import LogicaNegocio.Lanzador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUCliente extends Application{
    private Cliente cliente;
    private Lanzador lanzador;
    
    public VentanaCRUCliente(Cliente cliente, Lanzador lanzador){
        this.cliente = cliente;
        this.lanzador = lanzador;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUCliente(Lanzador lanzador){
        try {
            this.lanzador = lanzador;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/VentanaCRUCliente.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUClienteController controller = loader.getController();
        controller.iniciar(this.cliente, this.lanzador);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
