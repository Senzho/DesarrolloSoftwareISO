/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Grupos.Grupo;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaRegistrarPagoAlumnoController implements Initializable {

    @FXML
    private ComboBox comboAlumno;
    @FXML
    private TextField txtMonto;
    @FXML
    private Button btnPromocion;
    @FXML
    private RadioButton radioInscripcion;
    @FXML
    private RadioButton radioMensualidad;
    @FXML
    private Button btnRegistrar;
    private PagoAlumno pagoAlumno;
    private int idProfesor;
    private List<Alumno> alumnos;
    private List<Grupo> listaGrupos;
    private Promocion promocion;
    private ToggleGroup grupo;
    @FXML
    private Label lblPromocion;
    @FXML
    private ImageView imagenCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenCancelar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
        imagenCancelar.setVisible(false);
    }

    public void inicializarCombo() {
        radioMensualidad.setSelected(true);
        this.grupo = new ToggleGroup();
        radioMensualidad.setToggleGroup(grupo);
        radioInscripcion.setToggleGroup(grupo);
        alumnos = new ArrayList();
        listaGrupos = new Grupo().obtenerGruposProfesor(idProfesor);
        for (Grupo grupo : listaGrupos) {
            List<Alumno> alumnosGrupo = new Alumno().obtenerAlumnos(grupo.getId());
            for (Alumno alumno : alumnosGrupo) {
                alumnos.add(alumno);
                comboAlumno.getItems().add(alumno.getNombre());
                comboAlumno.setValue(alumno.getNombre());
            }
        }
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
        this.imagenCancelar.setVisible(true);
        lblPromocion.setText(promocion.getDescripcion());
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    public void setAlumnoSeleccionado(String nombre){
        this.comboAlumno.setValue(nombre);
    }

    public void btnPromocion_onClick() {
        VentanaConsultarPromociones ventana = new VentanaConsultarPromociones(idProfesor, this);
    }

    public void btnRegistrar_onClick() {
        int tipoPago = 0;
        boolean registrado = false;
        if (radioInscripcion.isSelected()) {
            tipoPago = 0;
        } else if (radioMensualidad.isSelected()) {
            tipoPago = 1;
        }
        Alumno alumno = null;
        for (Alumno alumnoRegistro : alumnos) {
            if (alumnoRegistro.getNombre().equalsIgnoreCase(this.comboAlumno.getSelectionModel().getSelectedItem().toString())) {
                alumno = alumnoRegistro;
            }
        }
        if (OperacionesString.montoValido(this.txtMonto.getText())) {
            pagoAlumno = new PagoAlumno(new Date(), 0, this.txtMonto.getText(), tipoPago,this.idProfesor,alumno.getIdAlumno());
            
            if (this.promocion != null) {
                registrado = pagoAlumno.registrarPago(alumno.getIdAlumno(), promocion.getIdPromocion());
            } else {
                registrado = pagoAlumno.registrarPago(alumno.getIdAlumno(), 0);
            }
            if (registrado) {
                MessageFactory.showMessage("Confirmación", "Datos regstrados", "Datos registrados correctamente", Alert.AlertType.INFORMATION);
            } else {
                MessageFactory.showMessage("Información", "Datos no almacenados", "Los datos no pudieron almacenarse", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Error de datos", "Datos incorrectos", "Debe ingresar un monto", Alert.AlertType.WARNING);
        }
    }
    public void imagenCancelar_onClick(){
        this.promocion = null;
        this.imagenCancelar.setVisible(false);
        this.lblPromocion.setText("");
    }
}
