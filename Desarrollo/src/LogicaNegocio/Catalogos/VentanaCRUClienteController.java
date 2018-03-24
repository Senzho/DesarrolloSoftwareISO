package LogicaNegocio.Catalogos;

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

public class VentanaCRUClienteController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private TextField correo;
    @FXML
    private TextField telefono;
    @FXML
    private TextArea domicilio;
    @FXML
    private Button registrar;
    
    private Cliente cliente;
    
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
    private void mostrarMensajeError(CatalogoEnum alumnoEnum){
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        if (cliente != null){
            this.cargarCliente();
            this.registrar.setText("Guardar");
        }else{
            this.registrar.setText("Registrar");
        }
    }
    public void cargarCliente(){
        this.nombre.setText(this.cliente.getNombre());
        this.correo.setText(this.cliente.getCorreo());
        this.telefono.setText(this.cliente.getTelefono());
        this.domicilio.setText(this.cliente.getDireccion());
    }
    
    public void registrar_OnClick(){
        CatalogoEnum alumnoEnum = this.validarDatos();
        if (alumnoEnum.equals(CatalogoEnum.DATOS_VALIDOS)){
            boolean realizado = false;
            if (this.cliente != null){
                this.cliente.setNombre(this.nombre.getText());
                this.cliente.setTelefono(this.telefono.getText());
                this.cliente.setCorreo(this.correo.getText());
                this.cliente.setDireccion(this.domicilio.getText());
                if (this.cliente.editarCliente())
                    realizado = true;
            }else{
                this.cliente = new Cliente();
                this.cliente.setNombre(this.nombre.getText());
                this.cliente.setTelefono(this.telefono.getText());
                this.cliente.setCorreo(this.correo.getText());
                this.cliente.setDireccion(this.domicilio.getText());
                this.cliente.setFecha(new Date());
                if (this.cliente.registrarCliente()){
                    realizado = true;
                    this.registrar.setText("Guardar");
                }
            }
            if (!realizado){
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el cliente", Alert.AlertType.ERROR);
            }else{
                MessageFactory.showMessage("Éxito", "Registro", "El cliente se guardó exitosamente", Alert.AlertType.INFORMATION);
            }
        }else{
            this.mostrarMensajeError(alumnoEnum);
        }
    }
}
