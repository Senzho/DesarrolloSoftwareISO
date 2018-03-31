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
public class PanelGastoPromocionalController implements Initializable {
    @FXML
    private ImageView imagenEditar;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblFechaFin;
    @FXML
    private Label lblCosto;
    private GastoPromocional gasto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setPromocion(GastoPromocional gasto){
        this.gasto = gasto;
        this.cargarGasto();
    }
    public void cargarGasto(){
        this.imagenEditar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        this.lblDescripcion.setText(this.gasto.getDescripcion());
        this.lblFechaInicio.setText(this.gasto.getFechaInicio().toGMTString());
         this.lblFechaFin.setText(this.gasto.getFechaFin().toGMTString());
        this.lblCosto.setText(this.gasto.getMonto());
    }
    public void imagenEditar_onClick(){
        new VentanaCRUGastoPromocional(this.gasto);

    }
}
