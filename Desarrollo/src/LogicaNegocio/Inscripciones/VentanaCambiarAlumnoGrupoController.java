/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Inscripciones;

import Accesodatos.Grupos.GrupoDAOSql;
import Accesodatos.Inscripciones.InscripcionDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Grupos.Grupo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaCambiarAlumnoGrupoController implements Initializable {

    @FXML
    private ComboBox comboGruposDisponibles;
    @FXML
    private ComboBox comboGruposInscritos;
    @FXML
    private Label txtNombre;
    @FXML
    private ImageView imagenUsuario;
    private int idProfesor;
    private Alumno alumno;
    private List<Grupo> listaGruposInscrito;
    private List<Grupo> listaGruposDisponibles;
    private GrupoDAOSql grupoDao;
    private InscripcionDAOSql inscripcionDao;

    /**
     * Initializes the controller class.
     */
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
        cargarGruposDisponibles();
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.txtNombre.setText(alumno.getNombre());
        this.cargarImagen();
    }

    public void cargarImagen() {
        Image imagen = CopiarArchivo.obtenerFotoUsuario("Alumno", alumno.getIdAlumno());
        if (imagen != null) {
            this.imagenUsuario.setImage(imagen);
        } else {
            this.imagenUsuario.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }

    public void cargarGruposDisponibles() {
        grupoDao = new GrupoDAOSql();
        listaGruposDisponibles = new ArrayList();
        List<Grupo> gruposProfesor = grupoDao.obtenerGruposProfesor(idProfesor);
        List<Grupo> listaGrupos = grupoDao.obtenerGruposAlumno(this.alumno.getIdAlumno());
        listaGruposInscrito = new ArrayList();
        if (!listaGrupos.isEmpty()) {
            for (Grupo grupo : listaGrupos) {
                System.out.println(grupo.getProfesor());
                if (grupo.getProfesor().getIdProfesor() == idProfesor) {
                    listaGruposInscrito.add(grupo);
                }
            }
        }
        cargarComboGruposDisponibles(gruposProfesor);
        this.cargarCombos();
    }

    public void cargarComboGruposDisponibles(List<Grupo> gruposProfesor) {
        listaGruposDisponibles.clear();
        for (int i = 0; i < gruposProfesor.size(); i++) {
            String nombreDanza = gruposProfesor.get(i).getDanza();
            for (int j = 0; j < listaGruposInscrito.size(); j++) {
                String nombreInscripcion = listaGruposInscrito.get(j).getDanza();
                if (!nombreDanza.equals(nombreInscripcion)) {
                    listaGruposDisponibles.add(gruposProfesor.get(i));
                }
            }
        }
    }

    public void cargarCombos() {
        for (Grupo grupoAlumno : listaGruposInscrito) {
            this.comboGruposInscritos.getItems().add(grupoAlumno.getDanza());
            comboGruposInscritos.setValue(grupoAlumno.getDanza());
        }
        for (Grupo grupoDisponible : listaGruposDisponibles) {
            this.comboGruposDisponibles.getItems().add(grupoDisponible.getDanza());
            comboGruposDisponibles.setValue(grupoDisponible.getDanza());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void btnCambiarCurso_onClick() {
        String nombre = this.comboGruposInscritos.getSelectionModel().getSelectedItem().toString();
        Grupo grupo = null;
        for (Grupo grupoInscripcion : listaGruposInscrito) {
            System.out.println(grupoInscripcion.getNombre());
            if (grupoInscripcion.getDanza().equalsIgnoreCase(nombre)) {
                grupo = grupoInscripcion;
                System.out.println(grupoInscripcion.getNombre());
                break;
            }
        }
        inscripcionDao = new InscripcionDAOSql();
        System.out.println(grupo.getId());
        boolean borrado = inscripcionDao.borrarRegistro(this.alumno.getIdAlumno(), grupo.getId());
        if (borrado) {
            Grupo grupoInscripcion = null;
            for (Grupo grupoD : listaGruposDisponibles) {
                if (grupoD.getDanza().equalsIgnoreCase(this.comboGruposDisponibles.getSelectionModel().getSelectedItem().toString())) {
                    grupoInscripcion = grupoD;
                    break;
                }
            }
            Inscripcion inscripcion = new Inscripcion(0, grupoInscripcion.getId(), this.alumno.getIdAlumno());
            boolean creado = inscripcionDao.registrar(inscripcion);
            if (creado) {
                MessageFactory.showMessage("Registro exitoso", "Creacion de registro", "Regitro creado correctamente", Alert.AlertType.CONFIRMATION);
            } else {
                MessageFactory.showMessage("Error de registro", "Creacion de registro", "El registro no pudo crearse", Alert.AlertType.ERROR);
            }
        }
    }
}

