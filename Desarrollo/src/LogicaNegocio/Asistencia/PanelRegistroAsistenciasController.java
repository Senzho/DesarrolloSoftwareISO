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
import LogicaNegocio.Pagos.PanelPromocionController;
import LogicaNegocio.Pagos.Promocion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private List<Asistencia> listaAsistencias;
    private List<Grupo> listaGruposProfesor;
    private List<Alumno> listaAlumnos;

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        this.listaGruposProfesor = new Grupo().obtenerGruposProfesor(profesor.getIdProfesor());
        for (Grupo grupo : listaGruposProfesor) {
            this.comboCursos.getItems().add(grupo.getNombre());
            comboCursos.setValue(grupo.getNombre());
        }
    }

    public void btnBuscar_onClick() {
        String nombreGrupo = (String) this.comboCursos.getSelectionModel().getSelectedItem();
        Grupo grupo = null;
        for (Grupo grupo1 : listaGruposProfesor) {
            if (grupo1.getNombre().equals(nombreGrupo)) {
                grupo = grupo1;
                break;
            }
        }
        listaAlumnos = new Alumno().obtenerAlumnos(grupo.getId());
        listaAsistencias = new Asistencia().obtenerAsistencias(grupo.getId());
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
        List<Asistencia> asistenciasAlumno = new ArrayList<>();
        for (Asistencia asistencia : listaAsistencias) {
            List<Alumno> alumnos = asistencia.getAlumnos();
            Date fecha = asistencia.getFecha();
            for (int i = 0; i < alumnos.size(); i++) {
                if (alumnos.get(i).getNombre().equalsIgnoreCase(alumno.getNombre())) {

                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Asistencia/panelAsistenciaAlumno.fxml"));
                    AnchorPane panel;
                    try {
                        panel = loader.load();
                        panel.setStyle("-fx-background-color: #DAD9D5;");
                        PanelAsistenciaAlumnoController controller = loader.getController();
                        controller.setDatos(alumno.getCorreo(), Dates.getSentence(fecha));
                        this.panelAsistencias.getChildren().add(panel);
                    } catch (IOException ex) {
                        Logger.getLogger(PanelAsistenciaAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("asistencia de " + alumno.getNombre() + "//" + Dates.getSentence(fecha));
                }
            }
            break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
