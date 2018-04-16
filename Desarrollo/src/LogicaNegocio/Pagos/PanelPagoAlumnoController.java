/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelPagoAlumnoController implements Initializable {
    private PagoAlumno pagoAlumno;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblMonto;
    @FXML
    private Label lblTipoPago;
    @FXML
    private Label lblPromocion;
    private Promocion promocion;
    
    /**
     * Initializes the controller class.
     */
    public void setPago(PagoAlumno pagoAlumno){
        this.pagoAlumno = pagoAlumno;
        
        lblFecha.setText(Dates.toString(pagoAlumno.getFecha()));
        lblMonto.setText(pagoAlumno.getMonto());
        if(pagoAlumno.getTipoPago() == 0){
            lblTipoPago.setText("Inscripción");
        }else{
            lblTipoPago.setText("Mensualidad");
        }
        System.out.println("idPromocion "+ pagoAlumno.getIdPromocion());
        if(pagoAlumno.getIdPromocion() != 0){
            
            Promocion promocion = new Promocion().obtenerPromocion(pagoAlumno.getIdPromocion());
            lblPromocion.setText(promocion.getDescripcion());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
