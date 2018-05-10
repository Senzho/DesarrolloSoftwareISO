package LogicaNegocio.Asistencia;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Grupos.Grupo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelAsistenciaController implements Initializable {
    @FXML
    private ComboBox grupos;
    @FXML
    private DatePicker fecha;
    @FXML
    private VBox panelAlumnos;
    
    private List<Grupo> listaGrupos;
    private List<PanelAlumnoAsistenciaController> panelesAlumno;
    private boolean todoMarcado;
    
    private int obtenerIdGrupo(){
        int id = 0;
        for (Grupo grupo : this.listaGrupos){
            if (grupo.getNombre().equals(this.grupos.getValue().toString())){
                id = grupo.getId();
                break;
            }
        }
        return id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaGrupos = new ArrayList();
        this.panelesAlumno = new ArrayList();
        this.fecha.setValue(LocalDate.now());
        this.todoMarcado = false;
    }
    
    public void setProfesor(int idProfesor){
        new GrupoDAOSql().obtenerGruposProfesor(idProfesor).forEach((grupo) -> {
            this.listaGrupos.add(grupo);
            this.grupos.getItems().add(grupo.getNombre());
        });
        if (!this.listaGrupos.isEmpty()){
            this.grupos.setValue(this.listaGrupos.get(this.listaGrupos.size() - 1).getNombre());
            this.cargarAlumnos();
        }else{
            MessageFactory.showMessage("Información", "Grupos", "No tienes grupos", Alert.AlertType.INFORMATION);
        }
    }
    public void setGrupo(String nombreGrupo){
        this.grupos.setValue(nombreGrupo);
        this.cargarAlumnos();
    }
    public void cargarAlumnos(){
        this.todoMarcado = false;
        this.panelesAlumno.clear();
        this.panelAlumnos.getChildren().clear();
        List<Alumno> alumnos = new AlumnoDAOSql().obtenerAlumnos(this.obtenerIdGrupo());
        Collections.sort(alumnos);
        alumnos.forEach((alumno) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Asistencia/PanelAlumnoAsistencia.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #D8D8D8;");
                PanelAlumnoAsistenciaController controller = loader.getController();
                controller.setAlumno(alumno);
                this.panelesAlumno.add(controller);
                this.panelAlumnos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelAsistenciaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void grupos_onClick(){
        if (!this.listaGrupos.isEmpty()){
            this.cargarAlumnos();
        }
    }
    public void marcarTodo_onClick(){
        this.panelesAlumno.forEach((controller) -> {
            controller.marcar(!this.todoMarcado);
        });
        this.todoMarcado = !this.todoMarcado;
    }
}
