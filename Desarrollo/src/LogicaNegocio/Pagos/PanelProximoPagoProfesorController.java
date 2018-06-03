/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelProximoPagoProfesorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblMonto;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblTipoPago;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setDatosProfesor(String nombre, String monto, boolean tipoPago, Date fecha){
        lblNombre.setText(nombre);
        lblMonto.setText(monto);
        lblFecha.setText(Dates.getSentence(fecha));
        if(tipoPago){
            lblTipoPago.setText("Quincenal");
        }else{
            lblTipoPago.setText("Mensualidad");
        }
        
    }
}
