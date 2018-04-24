package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.OperacionesString;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VentanaCRUEgresoController implements Initializable {
    @FXML
    private DatePicker fecha;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtCosto;
    @FXML
    private Button btnRegistrar;
    private Egreso egreso;
    private Date fechaRegistro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fecha.setValue(LocalDate.now());
    }

    public boolean validarCampos() {
        boolean valido = true;
        String descripcion = txtDescripcion.getText().trim();
        String monto = this.txtCosto.getText().trim();
        if (descripcion.equals("") || !OperacionesString.montoValido(monto)) {
                valido = false;
        }    
        return valido;
    }
    public void cargarDatosEgreso() {
        this.txtCosto.setText(egreso.getMonto());
        this.txtDescripcion.setText(egreso.getDescripcion());
        Date fechaEgreso = this.egreso.getFecha();
        this.fecha.setValue(LocalDate.of(Dates.getYear(fechaEgreso), Dates.getMonth(fechaEgreso), Dates.getDay(fechaEgreso)));
    }
    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
        if (egreso != null) {
            cargarDatosEgreso();
            this.btnRegistrar.setText("Guardar");
        }
    }
    public void editarEgreso() {
        this.fechaRegistro = Dates.toDate(this.fecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        Egreso egresoRegistro = new Egreso(egreso.getIdEgreso(), txtCosto.getText().trim(), txtDescripcion.getText().trim(), fechaRegistro);
        boolean registroExitoso = false;
        if (validarCampos()) {
            registroExitoso = egresoRegistro.editarEgreso();
            if (registroExitoso) {
                MessageFactory.showMessage("Aviso", "Registro egreso", "Egreso editado exitosamente", Alert.AlertType.CONFIRMATION);
            } else {
                MessageFactory.showMessage("Aviso", "Registro egreso", "No se pudo crear el egreso", Alert.AlertType.ERROR);
            }
        } else {
            MessageFactory.showMessage("Aviso", "Registro egreso", "Faltan datos por llenar", Alert.AlertType.ERROR);
        }
    }
    public void agregarEgreso() {
        this.fechaRegistro = Dates.toDate(this.fecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
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
