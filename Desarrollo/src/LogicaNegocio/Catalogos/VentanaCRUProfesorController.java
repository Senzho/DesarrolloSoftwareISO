package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Sesiones.Hasher;
import LogicaNegocio.Sesiones.Usuario;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
    private Profesor profesor;
    
    private static final String TIPO_PAGO_MENSUAL = "Mensual";
    private static final String TIPO_PAGO_QUINCENAL = "Quincenal";
    
    private CatalogoEnum validarDatos(){
        CatalogoEnum validacion = CatalogoEnum.DATOS_VALIDOS;
        if (this.nombre.getText().isEmpty()){
            validacion = CatalogoEnum.NOMBRE_VACIO;
        }else if (this.nombre.getText().length() > 150){
            validacion = CatalogoEnum.NOMBRE_LARGO;
        }else if (this.telefono.getText().isEmpty()){
            validacion = CatalogoEnum.TELEFONO_VACIO;
        }else if (this.telefono.getText().length() > 10){
            validacion = CatalogoEnum.TELEFONO_LARGO;
        }else if (!OperacionesString.telefonoValido(this.telefono.getText())){
            validacion = CatalogoEnum.TELEFONO_NO_VALIDO;
        }else if(this.correo.getText().isEmpty()){
            validacion = CatalogoEnum.CORREO_VACIO;
        }else if (this.correo.getText().length() > 150){
            validacion = CatalogoEnum.CORREO_LARGO;
        }else if (!OperacionesString.emailValido(this.correo.getText())){
            validacion = CatalogoEnum.CORREO_NO_VALIDO;
        }else if (this.monto.getText().isEmpty()){
            validacion = CatalogoEnum.MONTO_VACIO;
        }else if (this.monto.getText().length() > 15){
            validacion = CatalogoEnum.MONTO_LARGO;
        }else if (!OperacionesString.montoValido(this.monto.getText())){
            validacion = CatalogoEnum.MONTO_NO_VALIDO;
        }
        return validacion;
    }
    private void mostrarMensajeError(CatalogoEnum profesorEnum){
        String mensaje;
        switch(profesorEnum){
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
    }
    
    public void cargarComboPago(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.add(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
        lista.add(VentanaCRUProfesorController.TIPO_PAGO_QUINCENAL);
        this.tipoPago.setItems(lista);
        this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
    }
    public void cargarProfesor(){
        this.nombre.setText(this.profesor.getNombre());
        this.telefono.setText(this.profesor.getTelefono());
        this.correo.setText(this.profesor.getCorreo());
        this.direccion.setText(this.profesor.getDireccion());
        this.monto.setText(this.profesor.getMonto());
        if (this.profesor.isTipoPago()){
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
        }else{
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_QUINCENAL);
        }
    }
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
        if (profesor != null){
            this.cargarProfesor();
            this.registrar.setText("Guardar");
            if (this.profesor.isEstado()){
                this.activo.setSelected(true);
            }else{
                this.inactivo.setSelected(true);
            }
        }else{
            this.tipoPago.setValue(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL);
            this.inactivo.setSelected(true);
            this.registrar.setText("Registrar");
        }
        this.cargarImagen();
    }
    public void cargarImagen(){
        //Validar si existe una imágen, en caso contrario:
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    public boolean registrarProfesor(){
        boolean realizado = false;
        this.profesor = new Profesor();
        this.profesor.setNombre(this.nombre.getText());
        this.profesor.setTelefono(this.telefono.getText());
        this.profesor.setCorreo(this.correo.getText());
        this.profesor.setDireccion(this.direccion.getText());
        this.profesor.setEstado(this.activo.isSelected());
        this.profesor.setMonto(this.monto.getText());
        this.profesor.setFecha(new Date());
        this.profesor.setTipoPago(this.tipoPago.getValue().equals(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL));
        if (this.profesor.registrarProfesor()){
            realizado = true;
            this.registrar.setText("Guardar");
        }
        return realizado;
    }
    public boolean editarProfesor(){
        boolean realizado = false;
        this.profesor.setNombre(this.nombre.getText());
        this.profesor.setTelefono(this.telefono.getText());
        this.profesor.setCorreo(this.correo.getText());
        this.profesor.setDireccion(this.direccion.getText());
        this.profesor.setEstado(this.activo.isSelected());
        this.profesor.setMonto(this.monto.getText());
        this.profesor.setTipoPago(this.tipoPago.getValue().equals(VentanaCRUProfesorController.TIPO_PAGO_MENSUAL));
        if (this.profesor.editarProfesor())
            realizado = true;
        return realizado;
    }
    
    public void registrar_OnClick(){
        CatalogoEnum catalogoEnum = this.validarDatos();
        if (catalogoEnum.equals(CatalogoEnum.DATOS_VALIDOS)){
            boolean realizado = false;
            String mensajeUsuario = "";
            if (this.profesor != null){
                realizado = this.editarProfesor();
            }else{
                if (this.registrarProfesor()){
                    String contraseña = Hasher.hash(OperacionesString.sinAcentosYMayusculas(this.profesor.getNombre()));
                    String usuario = OperacionesString.obtenerNombreUsuario(this.profesor.getCorreo());
                    if (!new Usuario(1, contraseña, this.profesor.getIdProfesor(), usuario, 1).crearUsuario()){
                        mensajeUsuario = ". No se pudo crear un usuario para el profesor, deberá crearse manualmente";
                    }
                    realizado = true;
                }
            }
            if (!realizado){
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el profesor", Alert.AlertType.ERROR);
            }else{
                MessageFactory.showMessage("Éxito", "Registro", "El profesor se guardó exitosamente" + mensajeUsuario, Alert.AlertType.INFORMATION);
            }
        }else{
            this.mostrarMensajeError(catalogoEnum);
        }
    }
    public void activo_OnClick(){
        if (this.activo.isSelected()){
            this.inactivo.setSelected(false);
        }
    }
    public void inactivo_OnClick(){
        if (this.inactivo.isSelected()){
            this.activo.setSelected(false);
        }
    }
}
