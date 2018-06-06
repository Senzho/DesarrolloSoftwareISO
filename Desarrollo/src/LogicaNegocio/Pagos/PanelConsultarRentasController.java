package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import InterfazGrafica.Pagos.VentanaRegistrarRenta;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Lanzador;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PanelConsultarRentasController implements Initializable {
    @FXML
    private ComboBox comboClientes;
    @FXML
    private FlowPane panelRentas;
    
    private List<Cliente> listaClientes;
    private List<Renta> listaRentas;
    private Lanzador lanzador;
    private AnchorPane pane;
    
    private void cargarClientes(){
        if (this.listaClientes.isEmpty()){
            MessageFactory.showMessage("Advertencia", "Clientes", "No hay clientes registrados", Alert.AlertType.WARNING);
        }else{
            this.comboClientes.getItems().clear();
            this.listaClientes.forEach((cliente) -> {
                this.comboClientes.getItems().add(cliente.getNombre());
            });
        }
    }
    private AnchorPane getPanel (Renta renta){
        AnchorPane pane = null;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelRenta.fxml"));
        try {
            pane = loader.load();
            PanelRentaController controller = loader.getController();
            controller.iniciar(renta, pane, this.lanzador);
            controller.habilitarFecha(true);
        } catch (IOException ex) {
            Logger.getLogger(PanelConsultarRentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pane;
    }
    private void cargarRentasTodas(){
        this.panelRentas.getChildren().clear();
        this.listaRentas.forEach((renta) -> {
            this.panelRentas.getChildren().add(this.getPanel(renta));
        });
    }
    private void cargarRentasCliente(){
        this.panelRentas.getChildren().clear();
        int id = this.getIdCliente();
        for (Renta renta : this.listaRentas) {
            if (renta.getCliente().getIdCliente() == id){
                this.panelRentas.getChildren().add(this.getPanel(renta));
            }
        }
    }
    private int getIdCliente(){
        int id = 0;
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getNombre().equals(this.comboClientes.getValue().toString())){
                id = cliente.getIdCliente();
                break;
            }
        }
        return id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void iniciar(Lanzador lanzador, AnchorPane pane){
        this.pane = pane;
        this.lanzador = lanzador;
        this.listaClientes = new Cliente().obtenerClientes();
        this.listaRentas = new Renta().obtenerRentas();
        this.cargarClientes();
        if (this.listaRentas.isEmpty()){
            MessageFactory.showMessage("Advertencia", "Rentas", "No hay rentas registradas", Alert.AlertType.WARNING);
        }else{
            this.cargarRentasTodas();
        }
    }
    public AnchorPane getPane(){
        return this.pane;
    }
    public void rentaAgregada(Renta renta){
        this.listaRentas.add(renta);
        this.cargarRentasTodas();
    }
    public void rentaEditada(Renta renta){
        for (int i = 0; i < this.listaRentas.size(); i ++){
            if (this.listaRentas.get(i).getIdRenta() == renta.getIdRenta()){
                this.listaRentas.set(i, renta);
                this.cargarRentasTodas();
                break;
            }
        }
    }
    public void rentaBaja(Renta renta){
        for (Renta rentaLista : this.listaRentas){
            if (rentaLista.getIdRenta() == renta.getIdRenta()){
                this.listaRentas.remove(rentaLista);
                this.cargarRentasTodas();
                break;
            }
        }
    }
    public void clienteEditado(Cliente cliente){
        for (int i = 0; i < this.listaRentas.size(); i ++){
            if (this.listaRentas.get(i).getCliente().getIdCliente() == cliente.getIdCliente()){
                this.listaRentas.get(i).setCliente(cliente);
                this.cargarClientes();
                this.cargarRentasTodas();
            }
        }
    }
    
    public void comboClientes_onAction(){
        if (this.listaClientes.isEmpty()){
            MessageFactory.showMessage("Advertencia", "Clientes", "No hay clientes registrados", Alert.AlertType.WARNING);
        }else{
            this.cargarRentasCliente();
        }
    }
    public void botonTodas_onClick(){
        this.cargarRentasTodas();
    }
    public void botonNuevaRenta_onClick(){
        new VentanaRegistrarRenta(this.lanzador);
    }
}
