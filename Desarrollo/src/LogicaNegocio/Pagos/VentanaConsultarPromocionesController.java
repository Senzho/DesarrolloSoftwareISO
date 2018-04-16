/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaConsultarPromocionesController implements Initializable {
    @FXML
    private FlowPane srollPromociones;
    @FXML
    private Button btnCrearPromocion;
    private int idProfesor;
    private Stage stage;
    private Promocion promocion;
    private List<Promocion> promociones;
    private VentanaRegistrarPagoAlumnoController controller;
    /**
     * Initializes the controller class.
     */
    public void setVentanaRegistrarPagoAlumnoController(VentanaRegistrarPagoAlumnoController controller){
        this.controller = controller;
    }
    
    public void setIdProfesor(int idProfesor){
        this.idProfesor = idProfesor;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setPromocion(Promocion promocion){
        this.promocion = promocion;
        System.out.println(promocion.getDescripcion());
        this.controller.setPromocion(promocion);
        MessageFactory.showMessage("Promocion seleccionada", "Promocion seleccionada", promocion.getDescripcion(), Alert.AlertType.INFORMATION);
    }
    public void inicializarPanelPromociones(){
       promociones = new Promocion().obtenerPromociones(idProfesor);
        this.srollPromociones.getChildren().clear();
        promociones.forEach((promocion) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPromocion.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPromocionController controller = loader.getController();
                controller.setPromocion(promocion);
                controller.setIdProfesor(this.idProfesor);
                controller.setVentanaConsultarPromocionesController(this);
                this.srollPromociones.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelPromocionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.srollPromociones.setVgap(5);
        this.srollPromociones.setVgap(5);
    }    
    public void btnCrearPromocion_onClick(){
        new VentanaCRUPromocion(this.idProfesor);
    }
}
