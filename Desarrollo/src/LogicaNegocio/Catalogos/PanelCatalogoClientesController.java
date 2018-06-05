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
    private AnchorPane pane;
    private List<Cliente> clientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void iniciar(Lanzador lanzador, AnchorPane pane){
        this.lanzador = lanzador;
        this.pane = pane;
        this.clientes = new Cliente().obtenerClientes();
        this.cargarClientes(this.clientes);
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
    public AnchorPane getPane(){
        return this.pane;
    }
    public void clienteAgregado(Cliente cliente){
        this.clientes.add(cliente);
        this.cargarClientes(this.clientes);
    }
    public void clienteEditado(Cliente cliente){
        for (int i = 0; i < this.clientes.size(); i ++){
            if (this.clientes.get(i).getIdCliente() == cliente.getIdCliente()){
                this.clientes.set(i, cliente);
                this.cargarClientes(this.clientes);
                break;
            }
        }
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
