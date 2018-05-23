package LogicaNegocio.Pagos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Grupo;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaRegistrarPagoTemporalController implements Initializable {
    @FXML
    private ComboBox comboProfesores;
    @FXML
    private ComboBox comboAlumnos;
    @FXML
    private TextField campoMonto;
    @FXML
    private RadioButton radioInscripcion;
    @FXML
    private RadioButton radioMensualidad;
    
    private List<Profesor> listaProfesores;
    private List<Alumno> listaAlumnos;
    private Stage stage;
    private PagoTemporal pagoTemporal;
    
    private void cargarProfesores(){
        this.listaProfesores.forEach((profesor) -> {
            this.comboProfesores.getItems().add(profesor.getNombre());
        });
    }
    private void cargarAlumnos(){
        this.listaAlumnos.clear();
        this.comboAlumnos.getItems().clear();
        List<Grupo> grupos = new GrupoDAOSql().obtenerGruposProfesor(this.getProfesorSeleccionado().getIdProfesor());
        grupos.forEach((grupo) -> {
            List<Alumno> alumnos = new AlumnoDAOSql().obtenerAlumnos(grupo.getId());
            alumnos.forEach((alumno) -> {
                this.comboAlumnos.getItems().add(alumno.getNombre());
                this.listaAlumnos.add(alumno);
            });
        });
    }
    private Profesor getProfesorSeleccionado(){
        Profesor profesorSeleccionado = null;
        for (Profesor profesor : this.listaProfesores){
            if (profesor.getNombre().equals(this.comboProfesores.getValue())){
                profesorSeleccionado = profesor;
                break;
            }
        }
        return profesorSeleccionado;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaAlumnos = new ArrayList();
    }
    public void inicializar(Stage stage){
        this.stage = stage;
        this.listaProfesores = new ProfesorDAOSql().obtenerProfesores();
        this.cargarProfesores();
        this.comboAlumnos.setDisable(true);
    }
    
    public void comboProfesores_onAction(){
        this.cargarAlumnos();
        this.comboAlumnos.setDisable(false);
    }
    public void botonRegistrar_onClick(){
        String monto = this.campoMonto.getText().trim();
        if (OperacionesString.montoValido(monto)){
            
        }else{
            MessageFactory.showMessage("Advertencia", "Monto", "El valor del monto no es válido", Alert.AlertType.WARNING);
        }
        this.stage.close();
    }
}
