package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Lanzador;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PanelCatalogoProfesoresController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private FlowPane panelProfesores;
    private Lanzador lanzador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador; 
    }
    
    public void cargarProfesores(List<Profesor> profesores){
        this.panelProfesores.getChildren().clear();
        Collections.sort(profesores);
        profesores.forEach((profesorObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelProfesor.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #D8D8D8;");
                PanelProfesorController controller = loader.getController();
                controller.setProfesor(profesorObtenido);
                controller.setLanzador(this.lanzador);
                this.panelProfesores.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelCatalogoAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void buscar_OnClick(){
        List<Profesor> profesores = new Profesor().obtenerProfesores(this.busqueda.getText());
        if (!profesores.isEmpty()){
            this.cargarProfesores(profesores);
        }else{
            MessageFactory.showMessage("Información", "Búsqueda", "No hay resultados", Alert.AlertType.INFORMATION);
        }
    }
}
