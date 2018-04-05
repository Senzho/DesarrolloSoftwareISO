/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelEgresoController implements Initializable {
    @FXML
    private ImageView imagenEditar;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblCosto;
    private Egreso egreso;
    private GastoPromocional gasto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setEgreso(Egreso egreso){
        this.egreso = egreso;
        this.cargarEgreso();
    }
    public void cargarEgreso(){
        this.imagenEditar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        this.lblDescripcion.setText(this.egreso.getDescripcion());
        this.lblFecha.setText(this.egreso.getFecha().toGMTString());
        //cambiar metodo de arriba
        this.lblCosto.setText(this.egreso.getMonto());
    }
    public void imagenEditar_onClick(){
        new VentanaCRUEgreso(egreso);

    }
}
