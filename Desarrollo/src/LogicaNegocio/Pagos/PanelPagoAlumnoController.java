/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
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
    private Alumno alumno;
    
    /**
     * Initializes the controller class.
     */
    public void setPago(PagoAlumno pagoAlumno){
        this.pagoAlumno = pagoAlumno;
        lblFecha.setText(Dates.getSentence(pagoAlumno.getFecha()));
        lblMonto.setText(pagoAlumno.getMonto());
        if(pagoAlumno.getTipoPago() == 0){
            lblTipoPago.setText("Inscripción");
        }else{
            lblTipoPago.setText("Mensualidad");
        }
        if(pagoAlumno.getIdPromocion() != 0){
            Promocion promocion = new Promocion().obtenerPromocion(pagoAlumno.getIdPromocion());
            lblPromocion.setText(promocion.getDescripcion());
        }
    }
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void generarRecibo_onClick(){
        new VentanaReciboPago(alumno, pagoAlumno);
    }
}
