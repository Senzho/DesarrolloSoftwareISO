package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.FlowPane;

public class PanelCatalogoClientesController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private FlowPane panelClientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarClientes(new Cliente().obtenerClientes());
    }
    public void cargarClientes(List<Cliente> clientes){
        this.panelClientes.getChildren().clear();
        clientes.forEach((clienteObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelCliente.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #D8D8D8;");
                PanelClienteController controller = loader.getController();
                controller.setCliente(clienteObtenido);
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
