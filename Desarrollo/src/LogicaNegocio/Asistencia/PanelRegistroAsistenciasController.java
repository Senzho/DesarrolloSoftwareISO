/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Asistencia;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Grupo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelRegistroAsistenciasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tablaAlumnos;
    @FXML
    private ComboBox comboCursos;
    @FXML
    private VBox panelAsistencias;
    @FXML
    private Button btnBuscar;
    private Profesor profesor;
    private List<Grupo> listaGruposProfesor;
    private List<Alumno> listaAlumnos;
    private Grupo grupo;

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        this.listaGruposProfesor = new Grupo().obtenerGruposProfesor(profesor.getIdProfesor());
        for (Grupo grupo : listaGruposProfesor) {
            this.comboCursos.getItems().add(grupo.getNombre());
            comboCursos.setValue(grupo.getNombre());
        }
    }

    public void btnBuscar_onClick() {
        panelAsistencias.getChildren().clear();
        String nombreGrupo = (String) this.comboCursos.getSelectionModel().getSelectedItem();
        for (Grupo grupo1 : listaGruposProfesor) {
            if (grupo1.getNombre().equals(nombreGrupo)) {
                grupo = grupo1;
                break;
            }
        }
        listaAlumnos = new Alumno().obtenerAlumnos(grupo.getId());
        inicializarTabla();
    }

    public void inicializarTabla() {
        this.tablaAlumnos.getColumns().clear();
        TableColumn<Alumno, String> nombreAlumnos = new TableColumn<>("Nombre");
        nombreAlumnos.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
        tablaAlumnos.getColumns().add(nombreAlumnos);
        tablaAlumnos.setItems(FXCollections.observableArrayList(listaAlumnos));

    }

    public void btnVerAsistencias_onClick() {
        panelAsistencias.getChildren().clear();
        Alumno alumno = (Alumno) tablaAlumnos.getSelectionModel().getSelectedItem();
        List<Date> fechasAsistencia = new Asistencia().obtenerAsistencias(grupo.getId(), alumno.getIdAlumno());
        for (Date fecha : fechasAsistencia) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Asistencia/panelAsistenciaAlumno.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelAsistenciaAlumnoController controller = loader.getController();
                int dia = LocalDate.of(Dates.getYear(fecha), Dates.getMonth(fecha), Dates.getDay(fecha)).getDayOfWeek().getValue();
                controller.setDatos(this.obtenerDia(dia), Dates.getSentence(fecha));
                this.panelAsistencias.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelRegistroAsistenciasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String obtenerDia(int numeroDia){
        String nombreDia = "";
        switch (numeroDia) {
            case 1:
                nombreDia = "Lunes";
                break;
            case 2:
                nombreDia = "Martes";
                break;
            case 3:
                nombreDia = "Miercoles";
                break;
            case 4:
                nombreDia = "Jueves";
                break;
            case 5:
                nombreDia = "Viernes";
                break;
            case 6:
                nombreDia = "Sabado";
                break;
            default:
                nombreDia = "Domingo";
                break;
        }
        return nombreDia;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
