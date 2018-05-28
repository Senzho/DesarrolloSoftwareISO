/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Asistencia;

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
public class PanelAsistenciaAlumnoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblFecha;
    public void setDatos(String nombre, String fecha){
        this.lblFecha.setText(fecha);
        this.lblNombre.setText(nombre);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
