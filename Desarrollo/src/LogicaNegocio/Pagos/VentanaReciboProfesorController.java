/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
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
public class VentanaReciboProfesorController implements Initializable {
     @FXML
    private Label txtFecha;
    @FXML
    private Label txtNombre;
    @FXML
    private Label txtMonto;
    @FXML
    private Label txtTipoPago;
    private Profesor profesor;
    private PagoProfesor pagoProfesor;
    private Stage stage;
    
    public void setPagoProfesor(PagoProfesor pagoProfesor){
        this.pagoProfesor = pagoProfesor;
    }
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void generarDatosRecibo(){
        this.txtFecha.setText(Dates.getSentence(pagoProfesor.getFecha()));
        this.txtNombre.setText(profesor.getNombre());
        this.txtMonto.setText(pagoProfesor.getMonto());
        String tipoPago="";
        if (!pagoProfesor.isTipoPago()){
            tipoPago = "Quincenal";
        }else{
            tipoPago = "Mensualidad";
        }
        this.txtTipoPago.setText(tipoPago);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void btnGuardar_onCLick(){
        ReciboPago recibo = new ReciboPago();
        recibo.setProfesor(profesor);
        recibo.setPagoProfesor(pagoProfesor);
        if(recibo.generarReciboPagoProfesor()){
         MessageFactory.showMessage("Archivo creado", "El archivo ha sido creado con exito",recibo.getRutaRegistro(), Alert.AlertType.INFORMATION);
         this.stage.close();
        }else{
            MessageFactory.showMessage("Error de registro", "Archivo no creado", "El archivo no ha podido guardarse", Alert.AlertType.INFORMATION);
        }
    }
    
}
