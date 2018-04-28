package LogicaNegocio.Grupos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import Accesodatos.Grupos.GrupoDAOSql;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelAlumnosGrupoProfesorController implements Initializable {
    @FXML
    private VBox panelAlumnos;
    @FXML
    private ComboBox comboGrupos;
    @FXML
    private Button registrarAsistencia;
    
    private List<Grupo> grupos;
    private Lanzador lanzador;
    private int idProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setIdProfesor(int idProfesor){
        this.idProfesor = idProfesor;
        this.grupos = new GrupoDAOSql().obtenerGruposProfesor(idProfesor);
        for(Grupo grupo : this.grupos){
            this.comboGrupos.getItems().add(grupo.getNombre());
        }
    }
    public void setIdGrupo(int idGrupo){
        this.comboGrupos.setValue(this.getGrupo(idGrupo).getNombre());
        this.cargarAlumnos(idGrupo);
    }
    public void cargarAlumnos(int idGrupo){
        this.panelAlumnos.getChildren().clear();
        List<Alumno> alumnos = new AlumnoDAOSql().obtenerAlumnos(idGrupo);
        Collections.sort(alumnos);
        alumnos.forEach((alumno) -> {
            if(alumno.isEstado()){
                this.agregarAlumno(alumno);
            }
        });
    }
    public void agregarAlumno(Alumno alumno){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelAlumnoProfesor.fxml"));
        try {
            AnchorPane pane = loader.load();
            pane.setStyle("-fx-background-color: #D8D8D8;");
            PanelAlumnoProfesorController controller = loader.getController();
            controller.setAlumno(alumno);
            controller.setIdProfesor(this.idProfesor);
            controller.setLanzador(this.lanzador);
            this.panelAlumnos.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelGruposProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Grupo getGrupo(){
        Grupo grupo = new Grupo();
        for (Grupo grupoLista : this.grupos){
            if (grupoLista.getNombre().equals(this.comboGrupos.getValue())){
                grupo = grupoLista;
                break;
            }
        }
        return grupo;
    }
    public Grupo getGrupo(int idGrupo){
        Grupo grupo = new Grupo();
        for (Grupo grupoLista : this.grupos){
            if (grupoLista.getId() == idGrupo){
                grupo = grupoLista;
                break;
            }
        }
        return grupo;
    }
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    
    public void comboGrupos_onAction(){
        this.cargarAlumnos(this.getGrupo().getId());
    }
}
