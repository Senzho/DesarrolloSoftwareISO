package LogicaNegocio.Inscripciones;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Grupos.Grupo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class VentanaInscribirAlumnoController implements Initializable {
    @FXML
    private ComboBox alumnos;
    @FXML
    private ComboBox grupos;
    
    private List<Alumno> listaAlumnos;
    private List<Grupo> listaGrupos;
    private Stage stage;
    private Inscripcion inscripcion;
    
    private void cargarAlumnos(){
        this.listaAlumnos.forEach((alumno) -> {
            if (alumno.isEstado()){
                this.alumnos.getItems().add(alumno.getNombre());
            }
        });
    }
    private void cargarGrupos(int idAlumno){
        this.grupos.getItems().clear();
        List<Grupo> listaGruposAlumno = new GrupoDAOSql().obtenerGruposAlumno(idAlumno);
        this.listaGrupos.forEach((grupo) ->{
            boolean yaInscrito = false;
            for(Grupo grupoAlumno : listaGruposAlumno) {
                if (grupoAlumno.getId() == grupo.getId()){
                    yaInscrito = true;
                    break;
                }
            }
            if (!yaInscrito){
                this.grupos.getItems().add(grupo.getNombre());
            }
        });
        if (this.grupos.getItems().isEmpty()){
            MessageFactory.showMessage("Advertencia", "Grupos", "No hay grupos disponibles para inscribir al alumno", Alert.AlertType.WARNING);
        }else{
            this.grupos.setDisable(false);
        }
    }
    private int getAlumnoSeleccionado(){
        int id = 0;
        for (Alumno alumno : this.listaAlumnos){
            if (alumno.getNombre().equals(this.alumnos.getValue().toString())){
                id = alumno.getIdAlumno();
                break;
            }
        }
        return id;
    }
    private int getGrupoSeleccionado(){
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
        this.grupos.setDisable(true);
    }
    
    public void iniciar(int idProfesor, Stage stage){
        this.stage = stage;
        this.listaAlumnos = new AlumnoDAOSql().obtenerAlumnos();
        if (this.listaAlumnos.isEmpty()){
            MessageFactory.showMessage("Advertencia", "Inscripción", "No existen alumnos", Alert.AlertType.WARNING);
            this.stage.close();
        }else{
            this.listaGrupos = new GrupoDAOSql().obtenerGruposProfesor(idProfesor);
            this.cargarAlumnos();
        }
    }
    public void inscribir(){
        this.inscripcion = new Inscripcion();
        this.inscripcion.setIdAlumno(this.getAlumnoSeleccionado());
        this.inscripcion.setIdGrupo(this.getGrupoSeleccionado());
        this.inscripcion.setIdInscripcion(0);
        if (this.inscripcion.registrar()){
            MessageFactory.showMessage("Exito", "Inscripción", "El alumno se inscribió exitosamente", Alert.AlertType.INFORMATION);
            this.stage.close();
        }else{
            MessageFactory.showMessage("Error", "Inscripción", "El alumno no se pudo inscribir", Alert.AlertType.ERROR);
        }
    }
    
    public void inscribir_onClick(){
        if (this.alumnos.getValue() != null && this.grupos.getValue() != null){
            this.inscribir();
        }else{
            MessageFactory.showMessage("Error", "Inscripción", "Selecciona un alumno y un grupo", Alert.AlertType.INFORMATION);
        }
    }
    public void alumnos_onAction(){
        this.cargarGrupos(this.getAlumnoSeleccionado());
    }
}
