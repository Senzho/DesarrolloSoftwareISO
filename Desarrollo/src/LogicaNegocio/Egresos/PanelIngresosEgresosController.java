/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.PanelPagoAlumnoController;
import LogicaNegocio.Pagos.PanelPromocionController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelIngresosEgresosController implements Initializable {
    @FXML
    private VBox panelAlumnos;
    @FXML
    private VBox panelProfesores;
    @FXML
    private VBox panelPromociones;
    @FXML
    private VBox panelOtros;
    @FXML
    private Button btnExportar;
    @FXML
    private ComboBox comboPeriodo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void btnexportar_onClick(){
    
    }
    public void inicializarPanelPagos() {
        List<PagoAlumno> pagos = new PagoAlumno().obtenerPagos();
        this.panelAlumnos.getChildren().clear();
        for(int i = pagos.size() - 1; i > - 1; i--){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoAlumno.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPagoAlumnoController controller = loader.getController();
                controller.setPago(pagos.get(i));
                controller.setAlumno(new Alumno().obtenerAlumno(pagos.get(i).getIdAlumno()));
                this.panelAlumnos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
