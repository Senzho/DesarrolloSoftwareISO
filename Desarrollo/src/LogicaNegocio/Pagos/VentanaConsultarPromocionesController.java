package LogicaNegocio.Pagos;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaConsultarPromocionesController implements Initializable, PromocionListener {
    @FXML
    private VBox srollPromociones;
    @FXML
    private Button btnCrearPromocion;
    
    private int idProfesor;
    private Stage stage;
    private Promocion promocion;
    private List<Promocion> promociones;
    private VentanaRegistrarPagoAlumnoController controller;
    
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
        this.controller.setPromocion(promocion);
        this.stage.close();
    }
    public void agregarPromocion(Promocion promocion){
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
    }
    public void inicializarPanelPromociones(){
        promociones = new Promocion().obtenerPromociones(idProfesor);
        this.srollPromociones.getChildren().clear();
        promociones.forEach((promocion) -> {
            this.agregarPromocion(promocion);
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    public void btnCrearPromocion_onClick(){
        new VentanaCRUPromocion(this.idProfesor, this);
    }

    @Override
    public void promocionAgregada(Promocion promocion) {
        this.agregarPromocion(promocion);
    }
    @Override
    public void promocionEditada(Promocion promocion) {
        
    }
}
