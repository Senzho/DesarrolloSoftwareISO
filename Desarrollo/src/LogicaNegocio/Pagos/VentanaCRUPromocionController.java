/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.OperacionesString;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaCRUPromocionController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPorcentaje;
    @FXML
    private Button btnRegistrar;
    private int idProfesor;
    private Promocion promocion;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
        this.txtDescripcion.setText(promocion.getDescripcion());
        this.txtNombre.setText(promocion.getNombre());
        this.txtPorcentaje.setText(Integer.toString(promocion.getPorcentaje()));
    }

    public void btnRegistrar_onClick() {
        if (promocion == null) {
            if (!txtNombre.getText().equals("") && !txtDescripcion.getText().equals("")
                    && OperacionesString.porcentajeValido(this.txtPorcentaje.getText())) {
                Promocion promocionRegistro = new Promocion(txtDescripcion.getText(), 0, idProfesor,
                        txtNombre.getText(), Integer.parseInt(txtPorcentaje.getText()));
                boolean registrar = promocionRegistro.registrarPromocion();
                if (registrar) {
                    MessageFactory.showMessage("Registro exitoso", "Registro realizado", "Promocion creada correctamente", Alert.AlertType.CONFIRMATION);
                } else {
                    MessageFactory.showMessage("Información", "No se pudo crear el registro", "La promoció no pudo ser almacenada", Alert.AlertType.ERROR);
                }
            } else {
                MessageFactory.showMessage("Advertencia", "No se pudo registrar", "Algunos datos son incorrectos", Alert.AlertType.INFORMATION);
            }
        }else{
            editarPromocion();
        }
    }
    public void editarPromocion(){
        if (!txtNombre.getText().equals("") && !txtDescripcion.getText().equals("")
                    && OperacionesString.porcentajeValido(this.txtPorcentaje.getText())) {
            promocion.setDescripcion(txtDescripcion.getText());
            promocion.setNombre(txtNombre.getText());
            promocion.setPorcentaje(Integer.parseInt(txtPorcentaje.getText()));
                boolean editar = promocion.editarPromocion();
                if (editar) {
                    MessageFactory.showMessage("edicion exitoso", "Registro realizado", "Promocion editar correctamente", Alert.AlertType.CONFIRMATION);
                } else {
                    MessageFactory.showMessage("Información", "No se pudo editar el registro", "La promoció no pudo ser editada", Alert.AlertType.ERROR);
                }
            } else {
                MessageFactory.showMessage("Advertencia", "No se pudo editar", "Algunos datos son incorrectos", Alert.AlertType.INFORMATION);
            }
    }
}
