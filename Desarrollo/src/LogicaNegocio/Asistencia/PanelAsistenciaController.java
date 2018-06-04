package LogicaNegocio.Asistencia;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Grupo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private List<Alumno> listaAlumnos;
    private boolean todoMarcado;
    private Asistencia asistencia;
    
    private Grupo obtenerGrupo(){
        Grupo grupo = null;
        for (Grupo grupoLista : this.listaGrupos){
            if (grupoLista.getNombre().equals(this.grupos.getValue().toString())){
                grupo = grupoLista;
                break;
            }
        }
        return grupo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaGrupos = new ArrayList();
        this.panelesAlumno = new ArrayList();
        this.listaAlumnos = new ArrayList();
        this.fecha.setValue(LocalDate.now());
        this.todoMarcado = false;
        this.asistencia = new Asistencia();
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
    public void mostrarAlumnos(List<Alumno> alumnos){
        this.panelesAlumno.clear();
        this.panelAlumnos.getChildren().clear();
        Collections.sort(alumnos);
        alumnos.forEach((alumno) -> {
            if (alumno.isEstado()){
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
            }  
        });
    }
    public void cargarAlumnos(){
        this.todoMarcado = false;
        this.listaAlumnos = new AlumnoDAOSql().obtenerAlumnos(this.obtenerGrupo().getId());
        this.mostrarAlumnos(this.listaAlumnos);
    }
    public void registrar(Grupo grupoSeleccionado){
        List<Alumno> alumnos = new ArrayList();
        this.panelesAlumno.forEach((panel) -> {
            if (panel.marcado()){
                alumnos.add(panel.getAlumno());
            }
        });
        if (alumnos.isEmpty()){
            MessageFactory.showMessage("Error", "Alumnos", "No marcaste ningún alumno", Alert.AlertType.NONE);
        }else{
            this.asistencia.setAlumnos(alumnos);
            if (grupoSeleccionado.registrarAsistencia(this.asistencia)){
                MessageFactory.showMessage("Éxito", "Registro", "La asistencia fue registrada con éxito", Alert.AlertType.INFORMATION);
            }else{
                MessageFactory.showMessage("Error", "Registro", "La asistencia no fue registrada", Alert.AlertType.ERROR);
            }
        }
    }
    public void alumnoEditado(Alumno alumno){
        for (int i = 0; i < this.listaAlumnos.size(); i ++){
            if (this.listaAlumnos.get(i).getIdAlumno() == alumno.getIdAlumno()){
                this.listaAlumnos.set(i, alumno);
                this.mostrarAlumnos(listaAlumnos);
                break;
            }
        }
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
    public void guardar_onClick(){
        Grupo grupoSeleccionado = this.obtenerGrupo();
        this.asistencia.setFecha(Dates.toDate(this.fecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        this.asistencia.setIdAsistencia(0);
        this.asistencia.setIdGrupo(grupoSeleccionado.getId());
        if (this.asistencia.asistenciaRegistrada()){
            MessageFactory.showMessage("Información", "Fecha", "La asistencia para la fecha seleccionada ya fue registrada", Alert.AlertType.INFORMATION);
        }else{
            this.registrar(grupoSeleccionado);
        }
    }
}
