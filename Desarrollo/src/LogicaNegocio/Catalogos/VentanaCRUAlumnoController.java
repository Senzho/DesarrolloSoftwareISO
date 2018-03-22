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
    
    private AlumnoEnum validarDatos(){
        AlumnoEnum validacion = AlumnoEnum.DATOS_VALIDOS;
        if (this.nombre.getText().isEmpty()){
            validacion = AlumnoEnum.NOMBRE_VACIO;
        }else if (this.nombre.getText().length() > 150){
            validacion = AlumnoEnum.NOMBRE_LARGO;
        }else if (this.telefono.getText().isEmpty()){
            validacion = AlumnoEnum.TELEFONO_VACIO;
        }else if (this.telefono.getText().length() > 10){
            validacion = AlumnoEnum.TELEFONO_LARGO;
        }else if(this.correo.getText().isEmpty()){
            validacion = AlumnoEnum.CORREO_VACIO;
        }else if (this.correo.getText().length() > 150){
            validacion = AlumnoEnum.CORREO_LARGO;
        }else if (!OperacionesString.emailValido(this.correo.getText())){
            validacion = AlumnoEnum.CORREO_NO_VALIDO;
        }
        return validacion;
    }
    private void mostrarMensajeError(AlumnoEnum alumnoEnum){
        String mensaje;
        switch(alumnoEnum){
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
        MessageFactory.showMessage("Error", "Datos no válidos", mensaje, Alert.AlertType.INFORMATION);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
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
        this.cargarImagen();
    }
    public void cargarImagen(){
        //Validar si existe una imágen, en caso contrario:
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    
    public void registrar_OnClick(){
        AlumnoEnum alumnoEnum = this.validarDatos();
        if (alumnoEnum.equals(AlumnoEnum.DATOS_VALIDOS)){
            boolean realizado = false;
            if (this.alumno != null){
                this.alumno.setNombre(this.nombre.getText());
                this.alumno.setTeléfono(this.telefono.getText());
                this.alumno.setCorreo(this.correo.getText());
                this.alumno.setDireccion(this.direccion.getText());
                this.alumno.setFecha(new Date());
                this.alumno.setEstado(this.activo.isSelected());
                if (this.alumno.editarAlumno())
                    realizado = true;
            }else{
                this.alumno = new Alumno();
                this.alumno.setNombre(this.nombre.getText());
                this.alumno.setTeléfono(this.telefono.getText());
                this.alumno.setCorreo(this.correo.getText());
                this.alumno.setDireccion(this.direccion.getText());
                this.alumno.setFecha(new Date());
                this.alumno.setEstado(this.activo.isSelected());
                if (this.alumno.registrarAlumno())
                    realizado = true;
            }
            if (!realizado){
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el alumno", Alert.AlertType.ERROR);
            }else{
                MessageFactory.showMessage("Éxito", "Registro", "El alumno se guardó exitosamente", Alert.AlertType.CONFIRMATION);
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
