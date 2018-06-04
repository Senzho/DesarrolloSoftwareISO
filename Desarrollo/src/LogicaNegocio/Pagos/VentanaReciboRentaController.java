/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaReciboRentaController implements Initializable {
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblMonto;
    @FXML
    private Label lblFechaRentar;
    @FXML
    private Label lblHorario;
    @FXML
    private Label lblSalon;
    private Stage stage;
    private Cliente cliente;
    private Renta renta;
    
   
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setRenta(Renta renta){
        this.renta = renta;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void generarDatosRecibo(){
        this.lblFecha.setText(Dates.getSentence(new Date()));
        this.lblNombre.setText(cliente.getNombre());
        this.lblMonto.setText(renta.getMonto()+ " $");
        this.lblFechaRentar.setText(Dates.getSentence(renta.getFecha()));
        this.lblHorario.setText(renta.getDia().getHoraInicio()+" - "+renta.getDia().getHoraFin());
        this.lblSalon.setText(renta.getDia().getSalon());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void btnGuardar_onClick(){
    
        ReciboPago recibo = new ReciboPago();
        recibo.setCliente(cliente);
        recibo.setRenta(renta);
        if(recibo.generarReciboPagoCliente()){
         MessageFactory.showMessage("Archivo creado","El archivo ha sido creado con exito",recibo.getRutaRegistro(), Alert.AlertType.INFORMATION);
         this.stage.close();
        }else{
            MessageFactory.showMessage("Error de registro", "Archivo no creado", "El archivo no ha podido guardarse", Alert.AlertType.CONFIRMATION);
        }
        
    }
    
}
