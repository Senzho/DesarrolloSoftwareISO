/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Egresos.Dates;
import com.itextpdf.text.Font;
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
public class PanelPagoClienteController implements Initializable {
    private Renta renta;
    private Cliente cliente;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblMonto;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFin;
    
    /**
     * Initializes the controller class.
     */
    
    public void setPago(Renta renta){
        this.renta = renta;
        this.lblMonto.setText("Monto: "+renta.getMonto());
        this.lblHoraInicio.setText("Inicio: "+this.renta.getDia().getHoraInicio());
        this.lblHoraFin.setText("Fin: "+this.renta.getDia().getHoraFin());
        this.lblFecha.setText("Fecha: "+Dates.getSentence(this.renta.getFecha()));
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void btnGenerarRecibo_onClick(){
        new VentanaReciboRenta(cliente, renta);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //imagenCancelar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    
}
