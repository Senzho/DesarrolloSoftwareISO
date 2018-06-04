/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
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
public class VentanaReciboPagoController implements Initializable {

    @FXML
    private Label txtFecha;
    @FXML
    private Label txtNombre;
    @FXML
    private Label txtMonto;
    @FXML
    private Label txtTipoPago;
    private Alumno alumno;
    private PagoAlumno pagoAlumno;
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
    }
    public void setPagoAlumno(PagoAlumno pagoAlumno){
        this.pagoAlumno = pagoAlumno;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void generarDatosRecibo(){
        this.txtFecha.setText(Dates.getSentence(pagoAlumno.getFecha()));
        this.txtNombre.setText(alumno.getNombre());
        this.txtMonto.setText(pagoAlumno.getMonto());
        String tipoPago="";
        if (pagoAlumno.getTipoPago() == 0){
            tipoPago = "inscripción";
        }else{
            tipoPago = "Mensualidad";
        }
        this.txtTipoPago.setText(tipoPago);
    }
    public void btnGuardar_onCLick(){
        ReciboPago recibo = new ReciboPago();
        recibo.setAlumno(alumno);
        recibo.setPagoAlumno(pagoAlumno);
        if(recibo.generarReciboPagoAlumno()){
         MessageFactory.showMessage("Archivo creado","El archivo ha sido creado con exito", recibo.getRutaRegistro(),Alert.AlertType.INFORMATION);
         this.stage.close();
        }else{
            MessageFactory.showMessage("Error de registro", "Archivo no creado", "El archivo no ha podido guardarse", Alert.AlertType.INFORMATION);
        }
    }
    
}
