/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import LogicaNegocio.Catalogos.OperacionesString;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
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
    private DatePicker fechaInicioPick;
    @FXML
    private DatePicker fechaFinPick;
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
        this.fechaInicioPick.setValue(LocalDate.now());
        this.fechaFinPick.setValue(LocalDate.of(Dates.getYear(new Date()), Dates.getMonth(new Date()), Dates.getDay(new Date())));
    }

    public boolean validarCampos() {
        boolean valido = true;
        if (txtDescripcion.getText().equals("") || !OperacionesString.URLValida(this.txtEnlace.getText())
                || !OperacionesString.montoValido(this.txtMonto.getText()) || Dates.getDiference(fechaInicio, fechaFin) < 1) {
                valido = false;
        }
        return valido;
    }

    public void cargarDatosGastoPromocional() {
        this.txtEnlace.setText(gastoPromocional.getMonto());
        this.txtDescripcion.setText(this.gastoPromocional.getDescripcion());
        this.txtMonto.setText(gastoPromocional.getMonto());
        Date fechaInicioGasto = this.gastoPromocional.getFechaInicio();
        Date fechaFinGasto = this.gastoPromocional.getFechaFin();
        this.fechaInicioPick.setValue(LocalDate.of(Dates.getYear(fechaInicioGasto), Dates.getMonth(fechaInicioGasto), Dates.getDay(fechaInicioGasto)));
        this.fechaFinPick.setValue(LocalDate.of(Dates.getYear(fechaFinGasto), Dates.getMonth(fechaFinGasto), Dates.getDay(fechaFinGasto)));
    }

    public void setGastoPromocional(GastoPromocional gastoPromocional) {
        this.gastoPromocional = gastoPromocional;
        if (gastoPromocional != null) {
            cargarDatosGastoPromocional();
        }
    }

    public void editarGastoPromocional() {
        this.fechaInicio = Dates.toDate(this.fechaInicioPick.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        this.fechaFin = Dates.toDate(this.fechaFinPick.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        GastoPromocional gastoPromocional = new GastoPromocional(this.gastoPromocional.getIdGastoPromocional(), this.txtDescripcion.getText(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText(),this.txtEnlace.getText());
        boolean registroExitoso = false;
        if (validarCampos()) {//idGastoPromocional,String descripcion, Date fechaFin, Date fechaInicio, String monto, String URL
            registroExitoso = gastoPromocional.editarGasto();
            if (registroExitoso) {
                MessageFactory.showMessage("Aviso", "Registro gasto promocional", "Gasto promocional editado exitosamente", Alert.AlertType.CONFIRMATION);
            } else {
                MessageFactory.showMessage("Aviso", "Registro gasto promocional", "No se pudo crear el gasto promocional", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro gasto promocional", "Faltan datos por llenar o algunos son incorrectos", Alert.AlertType.ERROR);
        }
    }

    public void agregarGastoPromocional() {
        this.fechaInicio = Dates.toDate(this.fechaInicioPick.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        this.fechaFin = Dates.toDate(this.fechaFinPick.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        GastoPromocional gasto = new GastoPromocional(0,this.txtDescripcion.getText(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText(),this.txtEnlace.getText());
        boolean registroExitoso = false;
        if (validarCampos()) {
            registroExitoso = gasto.registrarGasto();
            if (registroExitoso) {
                this.gastoPromocional = gasto;
                MessageFactory.showMessage("Aviso", "Registro egreso", "Gasto promocional creado exitosamente", Alert.AlertType.INFORMATION);
            }else{
                MessageFactory.showMessage("Aviso", "Registro egreso", "No se pudo crear el gasto promocional", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro egreso", "Faltan datos por llenar o algunos son incorrectos", Alert.AlertType.ERROR);
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
