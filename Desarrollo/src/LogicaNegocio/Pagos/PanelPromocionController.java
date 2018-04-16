/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

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
public class PanelPromocionController implements Initializable {
    @FXML
    private Label lblDescripcion;
    @FXML
    private ImageView imagenEditar;
    @FXML
    private ImageView imagenVer;
    private Promocion promocion;
    private VentanaConsultarPromocionesController controller; 
    private int idProfesor;
    /**
     * Initializes the controller class.
     */
    public void setVentanaConsultarPromocionesController(VentanaConsultarPromocionesController controller){
        this.controller = controller; 
    }
    public void setIdProfesor(int idProfesor){
        this.idProfesor = idProfesor;
    }
    
    public void setPromocion(Promocion promocion){
        this.promocion = promocion;
        lblDescripcion.setText(promocion.getDescripcion());
        cargarImagenes();
    }
    public void promocion_onClick(){
        this.setPromocion();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void cargarImagenes(){
    this.imagenEditar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
    this.imagenVer.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
    
    }
    public void setPromocion(){
        controller.setPromocion(this.promocion);
    }
    public void imagenEditar_onClick(){
        new VentanaCRUPromocion(this.idProfesor,this.promocion);
    }
}
