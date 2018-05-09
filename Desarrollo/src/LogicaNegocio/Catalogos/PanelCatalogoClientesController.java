package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Lanzador;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelCatalogoClientesController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private VBox panelClientes;
    private Lanzador lanzador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
        this.cargarClientes(new Cliente().obtenerClientes());
    }
    public void cargarClientes(List<Cliente> clientes){
        this.panelClientes.getChildren().clear();
        Collections.sort(clientes);
        clientes.forEach((clienteObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelCliente.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #D8D8D8;");
                PanelClienteController controller = loader.getController();
                controller.setCliente(clienteObtenido);
                controller.setLanzador(lanzador);
                this.panelClientes.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelCatalogoClientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void buscar_OnClick(){
        List<Cliente> clientes = new Cliente().obtenerClientes(this.busqueda.getText());
        if (!clientes.isEmpty()){
            this.cargarClientes(clientes);
        }else{
            MessageFactory.showMessage("Información", "Búsqueda", "No hay resultados", Alert.AlertType.INFORMATION);
        }
    }
}
