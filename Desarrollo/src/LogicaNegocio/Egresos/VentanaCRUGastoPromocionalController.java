package LogicaNegocio.Egresos;

import Accesodatos.Catalogos.ProfesorDAOSql;
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
import LogicaNegocio.Catalogos.Profesor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

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
    @FXML
    private ComboBox profesores;
    
    private GastoPromocional gastoPromocional;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Profesor> listaProfesores;
    
    private void cargarProfesores(){
        this.listaProfesores = new ProfesorDAOSql().obtenerProfesores();
        this.listaProfesores.forEach((profesor) -> {
            this.profesores.getItems().add(profesor.getNombre());
        });
        this.profesores.setValue(this.listaProfesores.get(0).getNombre());
    }
    private int getProfesorSeleccionado(){
        int id = 0;
        for (Profesor profesor : this.listaProfesores) {
            if (profesor.getNombre().equals(this.profesores.getValue().toString())){
                id = profesor.getIdProfesor();
                break;
            }
        }
        return id;
    }
    private String getProfesor(int idProfesor){
        String nombre = "";
        for(Profesor profesor : this.listaProfesores){
            if (profesor.getIdProfesor() == idProfesor){
                nombre = profesor.getNombre();
                break;
            }
        }
        return nombre;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fechaInicioPick.setValue(LocalDate.now());
        this.fechaFinPick.setValue(LocalDate.of(Dates.getYear(new Date()), Dates.getMonth(new Date()), Dates.getDay(new Date())));
        this.cargarProfesores();
    }

    public boolean validarCampos() {
        boolean valido = true;
        String descripcion = this.txtDescripcion.getText().trim();
        String monto = this.txtMonto.getText().trim();
        String enlace = this.txtEnlace.getText().trim();
        if (descripcion.equals("") || !OperacionesString.URLValida(enlace)
                || !OperacionesString.montoValido(monto) || Dates.getDiference(fechaInicio, fechaFin) < 1 || enlace.length() > 200) {
                valido = false;
        }
        return valido;
    }
    public void cargarDatosGastoPromocional() {
        this.txtEnlace.setText(gastoPromocional.getURL());
        this.txtDescripcion.setText(this.gastoPromocional.getDescripcion());
        this.txtMonto.setText(gastoPromocional.getMonto());
        Date fechaInicioGasto = this.gastoPromocional.getFechaInicio();
        Date fechaFinGasto = this.gastoPromocional.getFechaFin();
        this.fechaInicioPick.setValue(LocalDate.of(Dates.getYear(fechaInicioGasto), Dates.getMonth(fechaInicioGasto), Dates.getDay(fechaInicioGasto)));
        this.fechaFinPick.setValue(LocalDate.of(Dates.getYear(fechaFinGasto), Dates.getMonth(fechaFinGasto), Dates.getDay(fechaFinGasto)));
        this.profesores.setValue(this.getProfesor(gastoPromocional.getIdProfesor()));
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
        GastoPromocional gastoPromocional = new GastoPromocional(this.gastoPromocional.getIdGastoPromocional(), this.txtDescripcion.getText().trim(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText().trim(),this.txtEnlace.getText().trim(), this.getProfesorSeleccionado());
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
        GastoPromocional gasto = new GastoPromocional(0,this.txtDescripcion.getText().trim(), this.fechaFin,
                this.fechaInicio,this.txtMonto.getText().trim(),this.txtEnlace.getText().trim(), this.getProfesorSeleccionado());
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
