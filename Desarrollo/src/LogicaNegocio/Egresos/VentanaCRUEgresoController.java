/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.OperacionesString;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaCRUEgresoController implements Initializable {

    @FXML
    private TextField txtFecha;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtCosto;
    @FXML
    private Button btnRegistrar;
    private Egreso egreso;
    private Date fechaRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarComponentes() {
        Calendar fecha = new GregorianCalendar();
        String dia = Integer.toString(fecha.get(Calendar.DATE));
        String mes = Integer.toString(fecha.get(Calendar.MONTH));
        String año = Integer.toString(fecha.get(Calendar.YEAR));
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaRegistro = formatofecha.parse(año + "-" + mes + "-" + dia);
        } catch (ParseException ex) {
            Logger.getLogger(VentanaCRUEgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtFecha.setText(fechaRegistro.toString());
    }

    public boolean validarCampos() {
        boolean valido = true;
        if (txtDescripcion.getText().equals("") || !OperacionesString.montoValido(txtCosto.getText())) {
                valido = false;
        }    
        return valido;
    }

    public void cargarDatosEgreso() {
        this.txtCosto.setText(egreso.getMonto());
        this.txtDescripcion.setText(egreso.getDescripcion());
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
        if (egreso != null) {
            cargarDatosEgreso();
        }
        inicializarComponentes();
    }

    public void editarEgreso() {
        Egreso egresoRegistro = new Egreso(egreso.getIdEgreso(), txtCosto.getText(), txtDescripcion.getText(), fechaRegistro);
        boolean registroExitoso = false;
        if (validarCampos()) {
            registroExitoso = egresoRegistro.editarEgreso();
            if (registroExitoso) {
                MessageFactory.showMessage("Aviso", "Registro egreso", "egreso editado exitosamente", Alert.AlertType.CONFIRMATION);
            } else {
                MessageFactory.showMessage("Aviso", "Registro egreso", "no se pudo crear el egreso", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro egreso", "faltan datos por llenar", Alert.AlertType.ERROR);
        }
    }

    public void agregarEgreso() {
        Egreso egresoRegistro = new Egreso(0, txtCosto.getText(), txtDescripcion.getText(), fechaRegistro);
        boolean registroExitoso = false;
        if (validarCampos()) {
            registroExitoso = egresoRegistro.registrarEgreso();
            if (registroExitoso) {
                this.egreso = egresoRegistro;
                MessageFactory.showMessage("Aviso", "Registro egreso", "egreso creado exitosamente", Alert.AlertType.CONFIRMATION);
            }else{
                MessageFactory.showMessage("Aviso", "Registro egreso", "no se pudo crear el egreso", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro egreso", "faltan datos por llenar", Alert.AlertType.ERROR);
        }
    }

    public void btnRegistrar_onClick() {
        if (egreso != null) {
            editarEgreso();
        } else {
            agregarEgreso();
        }
    }

}
