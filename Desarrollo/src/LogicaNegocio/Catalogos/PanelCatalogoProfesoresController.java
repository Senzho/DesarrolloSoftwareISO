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
import javafx.scene.layout.VBox;

public class PanelCatalogoProfesoresController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private VBox panelProfesores;
    
    private Lanzador lanzador;
    private AnchorPane pane;
    private List<Profesor> listaProfesores;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void inicar(Lanzador lanzador, AnchorPane pane){
        this.lanzador = lanzador;
        this.pane = pane;
        this.listaProfesores = new Profesor().obtenerProfesores();
        this.cargarProfesores(this.listaProfesores);
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
                controller.iniciar(profesorObtenido, this.lanzador);
                this.panelProfesores.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelCatalogoAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public AnchorPane getPane(){
        return this.pane;
    }
    public void profesorAgregado(Profesor profesor){
        this.listaProfesores.add(profesor);
        Collections.sort(this.listaProfesores);
        this.cargarProfesores(this.listaProfesores);
    }
    public void profesorEditado(Profesor profesor){
        for (int i = 0; i < this.listaProfesores.size(); i ++){
            if (this.listaProfesores.get(i).getIdProfesor() == profesor.getIdProfesor()){
                this.listaProfesores.set(i, profesor);
                Collections.sort(this.listaProfesores);
                this.cargarProfesores(this.listaProfesores);
                break;
            }
        }
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
