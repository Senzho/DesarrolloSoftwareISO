package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Paneles;
import LogicaNegocio.Sesiones.Hasher;
import LogicaNegocio.Sesiones.Usuario;
import LogicaNegocio.Sesiones.VentanaPrincipalDirector;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VentanaCRUProfesorController implements Initializable {

    @FXML
    private ImageView imagen;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private TextArea direccion;
    @FXML
    private Button registrar;
    @FXML
    private RadioButton activo;
    @FXML
    private RadioButton inactivo;
    @FXML
    private ComboBox tipoPago;
    @FXML
    private TextField monto;
    @FXML
    private DatePicker fechaInicio;

    private Profesor profesor;
    private String rutaImagen;
    private Lanzador lanzador;
    private boolean primerUsuario;
    private Stage stage;

    private static final String TIPO_PAGO_MENSUAL = "Mensual";
    private static final String TIPO_PAGO_QUINCENAL = "Quincenal";

    private CatalogoEnum validarDatos() {
        CatalogoEnum validacion = CatalogoEnum.DATOS_VALIDOS;
        String nombre = this.nombre.getText().trim();
        String telefono = this.telefono.getText().trim();
        String correo = this.correo.getText().trim();
        String monto = this.monto.getText().trim();
        if (nombre.isEmpty()) {
            validacion = CatalogoEnum.NOMBRE_VACIO;
        } else if (nombre.length() > 150) {
            validacion = CatalogoEnum.NOMBRE_LARGO;
        } else if (telefono.isEmpty()) {
            validacion = CatalogoEnum.TELEFONO_VACIO;
        } else if (telefono.length() > 10) {
            validacion = CatalogoEnum.TELEFONO_LARGO;
        } else if (!OperacionesString.telefonoValido(telefono)) {
            validacion = CatalogoEnum.TELEFONO_NO_VALIDO;
        } else if (correo.isEmpty()) {
            validacion = CatalogoEnum.CORREO_VACIO;
        } else if (correo.length() > 150) {
            validacion = CatalogoEnum.CORREO_LARGO;
        } else if (!OperacionesString.emailValido(correo)) {
            validacion = CatalogoEnum.CORREO_NO_VALIDO;
        } else if (monto.isEmpty()) {
            validacion = CatalogoEnum.MONTO_VACIO;
        } else if (monto.length() > 15) {
            validacion = CatalogoEnum.MONTO_LARGO;
        } else if (!OperacionesString.montoValido(monto)) {
            validacion = CatalogoEnum.MONTO_NO_VALIDO;
        }
        return validacion;
    }

    private void mostrarMensajeError(CatalogoEnum profesorEnum) {
        String mensaje;
        switch (profesorEnum) {
            default:
                mensaje = "";
                break;
            case NOMBRE_VACIO:
                mensaje = "El campo nombre es requerido";
                break;
            case NOMBRE_LARGO:
                mensaje = "El campo nombre es demasiado largo";
                break;
            case TELEFONO_VACIO:
                mensaje = "El campo telefono es requerido";
                break;
            case TELEFONO_LARGO:
                mensaje = "El campo telefono es demasiado largo";
                break;
            case TELEFONO_NO_VALIDO:
                mensaje = "El campo telefono no es válido";
                break;
            case CORREO_VACIO:
                mensaje = "El campo correo es requerido";
                break;
            case CORREO_LARGO:
                mensaje = "El campo correo es demasiado largo";
                break;
            case CORREO_NO_VALIDO:
                mensaje = "El campo correo no es válido";
                break;
            case MONTO_VACIO:
                mensaje = "El campo monto no es válido";
                break;
            case MONTO_LARGO:
                mensaje = "El campo monto es demasiado largo";
                break;
            case MONTO_NO_VALIDO:
                mensaje = "El campo monto no es válido";
                break;
        }
        MessageFactory.showMessage("Error", "Datos no válidos", mensaje, Alert.AlertType.ERROR);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarComboPago();
        this.cargarImagen();
        this.fechaInicio.setValue(LocalDate.now());
    }

    public void cargarComboPago() {
        ObservableList lista = FXCollections.observableArrayList();
        lista.add(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
        lista.add(VentanaCRUProfesorController.TIPO_PAGO_QUINCENAL);
        this.tipoPago.setItems(lista);
        this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
    }

    public void cargarProfesor() {
        this.nombre.setText(this.profesor.getNombre());
        this.telefono.setText(this.profesor.getTelefono());
        this.correo.setText(this.profesor.getCorreo());
        this.direccion.setText(this.profesor.getDireccion());
        this.monto.setText(this.profesor.getMonto());
        if (this.profesor.isTipoPago()) {
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
        } else {
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_QUINCENAL);
        }
        Date fechaInicioPago = this.profesor.getFechaInicio();
        this.fechaInicio.setValue(LocalDate.of(Dates.getYear(fechaInicioPago), Dates.getMonth(fechaInicioPago), Dates.getDay(fechaInicioPago)));
    }

    public void iniciar(Profesor profesor, Lanzador lanzador, Stage stage){
        this.profesor = profesor;
        this.lanzador = lanzador;
        this.stage = stage;
        if (lanzador == null){
            this.primerUsuario = true;
        }else{
            this.primerUsuario = false;
        }
        if (profesor != null) {
            this.cargarProfesor();
            this.registrar.setText("Guardar");
            if (this.profesor.isEstado()) {
                this.activo.setSelected(true);
            } else {
                this.inactivo.setSelected(true);
            }
            Image imagenProfesor = CopiarArchivo.obtenerFotoUsuario("profesor", profesor.getIdProfesor());
            if (imagenProfesor != null) {
                this.imagen.setImage(imagenProfesor);
            }
        } else {
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
            this.inactivo.setSelected(true);
            this.registrar.setText("Registrar");
        }
    }

    public void cargarImagen() {
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }

    public boolean registrarProfesor() {
        boolean realizado = false;
        this.profesor = new Profesor();
        this.profesor.setNombre(this.nombre.getText().trim());
        this.profesor.setTelefono(this.telefono.getText().trim());
        this.profesor.setCorreo(this.correo.getText().trim());
        this.profesor.setDireccion(this.direccion.getText().trim());
        this.profesor.setEstado(this.activo.isSelected());
        this.profesor.setMonto(this.monto.getText().trim());
        this.profesor.setFecha(new Date());
        this.profesor.setTipoPago(this.tipoPago.getValue().equals(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL));
        this.profesor.setFechaInicio(Dates.toDate(this.fechaInicio.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        if (this.profesor.registrarProfesor()) {
            if (this.lanzador != null){
                Object[] objetos = {this.profesor};
                this.lanzador.enviarEvento(Paneles.CATALOGO_PROFESORES, "agregado", objetos);
            }
            realizado = true;
            this.registrar.setText("Guardar");
        }
        return realizado;
    }

    public boolean editarProfesor() {
        boolean realizado = false;
        this.profesor.setNombre(this.nombre.getText().trim());
        this.profesor.setTelefono(this.telefono.getText().trim());
        this.profesor.setCorreo(this.correo.getText().trim());
        this.profesor.setDireccion(this.direccion.getText().trim());
        this.profesor.setEstado(this.activo.isSelected());
        this.profesor.setMonto(this.monto.getText().trim());
        this.profesor.setTipoPago(this.tipoPago.getValue().equals(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL));
        this.profesor.setFechaInicio(Dates.toDate(this.fechaInicio.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        if (this.profesor.editarProfesor()) {
            if (this.lanzador != null){
                Object[] objetos = {this.profesor};
                this.lanzador.enviarEvento(Paneles.CATALOGO_PROFESORES, "editado", objetos);
            }   
            realizado = true;
        }
        return realizado;
    }

    public void registrar_OnClick() {
        CatalogoEnum catalogoEnum = this.validarDatos();
        if (catalogoEnum.equals(CatalogoEnum.DATOS_VALIDOS)) {
            boolean realizado = false;
            String mensajeUsuario = "";
            if (this.profesor != null) {
                realizado = this.editarProfesor();
            } else {
                if (this.registrarProfesor()) {
                    String contraseña = Hasher.hash(OperacionesString.sinAcentosYMayusculas(this.profesor.getNombre()));
                    String nombreUsuario = OperacionesString.obtenerNombreUsuario(this.profesor.getCorreo());
                    Usuario usuario = new Usuario(1, contraseña, this.profesor.getIdProfesor(), nombreUsuario, this.primerUsuario?0:1);
                    if (!usuario.crearUsuario()) {
                        mensajeUsuario = ". No se pudo crear un usuario para el profesor, deberá crearse manualmente";
                    }else{
                        if (this.primerUsuario){
                            MessageFactory.showMessage("Infromación", "Registro", "Bienvenido(a) deirector(a), por default su usuario y contraseña es: " + nombreUsuario + ", " + OperacionesString.sinAcentosYMayusculas(this.profesor.getNombre()), Alert.AlertType.INFORMATION);
                            this.stage.close();
                            new VentanaPrincipalDirector(usuario, this.profesor);
                        } 
                    }
                    realizado = true;
                }
            }
            if (!realizado) {
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el profesor", Alert.AlertType.ERROR);
            } else {
                try {
                    if (rutaImagen != null) {
                        CopiarArchivo.guardar("profesor", rutaImagen, profesor.getIdProfesor());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VentanaCRUProfesorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                MessageFactory.showMessage("Éxito", "Registro", "El profesor se guardó exitosamente" + mensajeUsuario, Alert.AlertType.INFORMATION);
            }
        } else {
            this.mostrarMensajeError(catalogoEnum);
        }
    }

    public void imagenUsuario_onClick() {
        Image imagenUsuario = CopiarArchivo.buscarFoto();
        if (imagenUsuario != null) {
            this.rutaImagen = CopiarArchivo.rutaImagen();
            this.imagen.setImage(imagenUsuario);
        }
    }

    public void activo_OnClick() {
        if (this.activo.isSelected()) {
            this.inactivo.setSelected(false);
        }
    }

    public void inactivo_OnClick() {
        if (this.inactivo.isSelected()) {
            this.activo.setSelected(false);
        }
    }
}
