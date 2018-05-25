package LogicaNegocio.Pagos;

import Accesodatos.Pagos.PagoTemporalDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Egresos.Dates;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelPagosTemporalesController implements Initializable, PagoTempBorradoListener {
    @FXML
    private VBox panelPagos;
    @FXML
    private DatePicker selectorfecha;
    
    private List<PagoTemporal> pagos;
    
    private void agregarPago(PagoTemporal pago){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoTemporal.fxml"));
        try {
            AnchorPane pane = loader.load();
            PanelPagoTemporalController controller = loader.getController();
            controller.iniciar(pago, pane, this);
            this.panelPagos.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelPagosTemporalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void cargarPagosTP(){
        this.panelPagos.getChildren().clear();
        if (this.pagos.isEmpty()){
            MessageFactory.showMessage("Información", "Pagos", "No hay pagos registrados", Alert.AlertType.INFORMATION);
        }else{
            this.pagos.forEach((pago) -> {
                this.agregarPago(pago);
            });
        } 
    }
    private void cargarPagosFecha(){
        Date fecha = Dates.toDate(this.selectorfecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        List<PagoTemporal> pagos = new ArrayList();
        for (PagoTemporal pago : this.pagos){
            if (pago.getFecha().equals(fecha)){
                pagos.add(pago);
            }
        }
        if (pagos.isEmpty()){
            MessageFactory.showMessage("Información", "Pagos", "No se encontraron pagos para la fecha seleccionada", Alert.AlertType.INFORMATION);
        }else{
            this.panelPagos.getChildren().clear();
            pagos.forEach((pago) -> {
                this.agregarPago(pago);
            });
        }
    }
    private void borrarPago(AnchorPane pane, int idPago){
        for (int i = 0; i < this.pagos.size(); i ++) {
            PagoTemporal pago = this.pagos.get(i);
            if (pago.getIdPago() == idPago){
                this.pagos.remove(i);
                this.panelPagos.getChildren().remove(pane);
                break;
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectorfecha.setValue(LocalDate.now());
        this.pagos = new PagoTemporalDAOSql().obtenerPagos();
        this.cargarPagosTP();
    }
    
    public void botonTodo_onClick(){
        this.cargarPagosTP();
    }
    public void selectorFecha_onAction(){
        this.cargarPagosFecha();
    }

    @Override
    public void pagoBorrado(AnchorPane pane, int idPago) {
        this.borrarPago(pane, idPago);
    }
}
