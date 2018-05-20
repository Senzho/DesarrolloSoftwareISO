package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PanelPagoProfesorController implements Initializable {
    @FXML
    private Label fecha;
    @FXML
    private Label tipoPago;
    @FXML
    private Label monto;
    @FXML
    private Button generar;
    private Profesor profesor;
    private PagoProfesor pagoProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setPagoProfesor(PagoProfesor pagoProfesor){
        this.pagoProfesor = pagoProfesor;
        this.cargarPago();
    }
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
    }
    public void cargarPago(){
        this.fecha.setText(Dates.getSentence(this.pagoProfesor.getFecha()));
        String tipoPago;
        if (this.pagoProfesor.isTipoPago()){
            tipoPago = "Pago mensual";
        }else{
            tipoPago = "Pago quincenal";
        }
        this.tipoPago.setText(tipoPago);
        this.monto.setText("Monto: " + this.pagoProfesor.getMonto());
    }
    
    public void generar_OnClick(){
        new VentanaReciboProfesor(profesor, pagoProfesor);
    }
}
