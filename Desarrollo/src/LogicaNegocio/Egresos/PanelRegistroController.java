/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.PagoProfesor;
import LogicaNegocio.Pagos.Promocion;
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
public class PanelRegistroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPrecio;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void generarPanelPagoProfesor(PagoProfesor pago, Profesor profesor){
        this.lblTitulo.setText(profesor.getNombre());
        this.lblFecha.setText(Dates.getSentence(pago.getFecha()));
        this.lblPrecio.setText("$ "+pago.getMonto());
    }
    public void generarPanelPromocion(Promocion promocion){
        this.lblTitulo.setText(promocion.getDescripcion());
        //this.lblFecha.setText(Dates.getSentence(promocion.getfecha()));
        this.lblPrecio.setText("$ "+promocion.getPorcentaje());
    }
    public void generarPanelPagoAlumno(PagoAlumno pago, Alumno alumno){
        this.lblTitulo.setText(alumno.getNombre());
        this.lblFecha.setText(Dates.getSentence(pago.getFecha()));
        this.lblPrecio.setText("$ "+pago.getMonto());
    }
    public void generarPanelEgresos(Egreso egreso){
        this.lblTitulo.setText(egreso.getDescripcion());
        this.lblFecha.setText(Dates.getSentence(egreso.getFecha()));
        this.lblPrecio.setText("$ "+egreso.getMonto());
    }
    
}
