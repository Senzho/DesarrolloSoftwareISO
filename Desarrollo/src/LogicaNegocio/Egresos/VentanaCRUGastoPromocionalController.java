/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import LogicaNegocio.Catalogos.OperacionesString;
/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaCRUGastoPromocionalController implements Initializable {
    @FXML
    private TextField txtEnlace;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtMonto;
    @FXML
    private ComboBox comboFechaInicio;
    @FXML
    private ComboBox comboFechaFin;
    @FXML
    private Button btnRegistrar;
    private GastoPromocional gastoPromocional;
    private Date fechaInicio;
    private Date fechaFin;
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
            fechaInicio = formatofecha.parse(año + "-" + mes + "-" + dia);
            fechaFin = formatofecha.parse(año + "-" + mes + "-" + dia);
        } catch (ParseException ex) {
            Logger.getLogger(VentanaCRUGastoPromocionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> itemsInicio = FXCollections.observableArrayList();
        itemsInicio.addAll(fechaInicio.toString());
        comboFechaInicio.setItems(itemsInicio);
        ObservableList<String> itemsFin = FXCollections.observableArrayList();
        itemsFin.addAll(fechaFin.toString());
        comboFechaFin.setItems(itemsFin);
    }

    public boolean validarCampos() {
        boolean valido = true;
        if (txtDescripcion.getText().equals("") || !OperacionesString.URLValida(this.txtEnlace.getText())
                || !OperacionesString.montoValido(this.txtMonto.getText())) {
                valido = false;
        }
        return valido;
    }

    public void cargarDatosGastoPromocional() {
        this.txtEnlace.setText(gastoPromocional.getMonto());
        this.txtDescripcion.setText(this.gastoPromocional.getDescripcion());
        this.txtMonto.setText(gastoPromocional.getMonto());
    }

    public void setGastoPromocional(GastoPromocional gastoPromocional) {
        this.gastoPromocional = gastoPromocional;
        if (gastoPromocional != null) {
            cargarDatosGastoPromocional();
        }
        inicializarComponentes();
    }

    public void editarGastoPromocional() {
        GastoPromocional gastoPromocional = new GastoPromocional(this.gastoPromocional.getIdGastoPromocional(), this.txtDescripcion.getText(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText(),this.txtEnlace.getText());
        boolean registroExitoso = false;
        if (validarCampos()) {//idGastoPromocional,String descripcion, Date fechaFin, Date fechaInicio, String monto, String URL
            registroExitoso = gastoPromocional.editarGasto();
            if (registroExitoso) {
                MessageFactory.showMessage("Aviso", "Registro gasto promocional", "gasto promocional editado exitosamente", Alert.AlertType.CONFIRMATION);
            } else {
                MessageFactory.showMessage("Aviso", "Registro gasto promocional", "no se pudo crear el gasto promocional", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro gasto promocional", "faltan datos por llenar", Alert.AlertType.ERROR);
        }
    }

    public void agregarGastoPromocional() {
        GastoPromocional gasto = new GastoPromocional(0,this.txtDescripcion.getText(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText(),this.txtEnlace.getText());
        boolean registroExitoso = false;
        if (validarCampos()) {
            registroExitoso = gasto.registrarGasto();
            if (registroExitoso) {
                this.gastoPromocional = gasto;
                MessageFactory.showMessage("Aviso", "Registro egreso", "egreso creado exitosamente", Alert.AlertType.CONFIRMATION);
            }else{
                MessageFactory.showMessage("Aviso", "Registro egreso", "no se pudo crear el egreso", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro egreso", "faltan datos por llenar", Alert.AlertType.ERROR);
        }
    }

    public void btnRegistrar_onClick() {
        if (this.gastoPromocional != null) {
            this.editarGastoPromocional();
        } else {
            this.agregarGastoPromocional();
        }
    }
}
