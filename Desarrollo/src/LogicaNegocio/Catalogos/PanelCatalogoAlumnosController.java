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

public class PanelCatalogoAlumnosController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private VBox panelAlumnos;
    
    private List<Alumno> listaAlumnos;
    private Lanzador lanzador;
    private AnchorPane pane;

    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void iniciar(Lanzador lanzador, AnchorPane pane){
        this.lanzador = lanzador;
        this.pane = pane;
        this.listaAlumnos = new Alumno().obtenerAlumnos();
        this.cargarAlumnos(this.listaAlumnos);
    }
    
    public void cargarAlumnos(List<Alumno> alumnos){
        this.panelAlumnos.getChildren().clear();
        Collections.sort(alumnos);
        alumnos.forEach((alumnoObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelAlumnoDirector.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                PanelAlumnoDirectorController controller = loader.getController();
                controller.iniciar(alumnoObtenido, this.lanzador);
                this.panelAlumnos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelCatalogoAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void alumnoAgregado(Alumno alumno){
        this.listaAlumnos.add(alumno);
        Collections.sort(this.listaAlumnos);
        this.cargarAlumnos(this.listaAlumnos);
    }
    public void alumnoEditado(Alumno alumno){
        for (int i = 0; i < this.listaAlumnos.size(); i ++) {
            if (this.listaAlumnos.get(i).getIdAlumno() == alumno.getIdAlumno()){
                this.listaAlumnos.set(i, alumno);
                break;
            }
        }
        Collections.sort(this.listaAlumnos);
        this.cargarAlumnos(this.listaAlumnos);
    }
    public AnchorPane getPane(){
        return this.pane;
    }
    
    public void buscar_OnClick(){
        List<Alumno> alumnos = new Alumno().obtenerAlumnos(this.busqueda.getText());
        if (!alumnos.isEmpty()){
            this.cargarAlumnos(alumnos);
        }else{
            MessageFactory.showMessage("Información", "Búsqueda", "No hay resultados", Alert.AlertType.INFORMATION);
        }
    }
}
