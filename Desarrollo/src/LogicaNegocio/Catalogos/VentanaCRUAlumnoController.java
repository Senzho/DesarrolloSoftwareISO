package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VentanaCRUAlumnoController implements Initializable {
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
    
    private Alumno alumno;
    
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
        }
        return validacion;
    }
    private void mostrarMensajeError(CatalogoEnum catalogoEnum){
        String mensaje;
        switch(catalogoEnum){
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
        }
        MessageFactory.showMessage("Error", "Datos no válidos", mensaje, Alert.AlertType.ERROR);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarImagen();
    }
    
    public void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
        this.direccion.setText(this.alumno.getDireccion());
    }
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        if (alumno != null){
            this.cargarAlumno();
            this.registrar.setText("Guardar");
            if (this.alumno.isEstado()){
                this.activo.setSelected(true);
            }else{
                this.inactivo.setSelected(true);
            }
        }else{
            this.inactivo.setSelected(true);
            this.registrar.setText("Registrar");
        }
    }
    public void cargarImagen(){
        //Validar si existe una imágen, en caso contrario:
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    public boolean registrarAlumno(){
		boolean realizado = false;
		this.alumno = new Alumno();
        this.alumno.setNombre(this.nombre.getText());
        this.alumno.setTeléfono(this.telefono.getText());
        this.alumno.setCorreo(this.correo.getText());
        this.alumno.setDireccion(this.direccion.getText());
        this.alumno.setFecha(new Date());
        this.alumno.setEstado(this.activo.isSelected());
        if (this.alumno.registrarAlumno()){
            realizado = true;
            this.registrar.setText("Guardar");
        }
        return realizado;
    }
    public boolean editarAlumno(){
    	boolean realizado = false;
    	this.alumno.setNombre(this.nombre.getText());
        this.alumno.setTeléfono(this.telefono.getText());
        this.alumno.setCorreo(this.correo.getText());
        this.alumno.setDireccion(this.direccion.getText());
        this.alumno.setEstado(this.activo.isSelected());
        if (this.alumno.editarAlumno())
            realizado = true;
        return realizado;
    }
    
    public void registrar_OnClick(){
        CatalogoEnum alumnoEnum = this.validarDatos();
        if (alumnoEnum.equals(CatalogoEnum.DATOS_VALIDOS)){
            boolean realizado = false;
            if (this.alumno != null){
                realizado = this.editarAlumno();
            }else{
                realizado = this.registrarAlumno();
            }
            if (!realizado){
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el alumno", Alert.AlertType.ERROR);
            }else{
                MessageFactory.showMessage("Éxito", "Registro", "El alumno se guardó exitosamente", Alert.AlertType.INFORMATION);
            }
        }else{
            this.mostrarMensajeError(alumnoEnum);
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
