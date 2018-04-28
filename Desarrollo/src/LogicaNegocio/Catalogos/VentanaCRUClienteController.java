package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    @FXML
    private ImageView imagen;

    private Cliente cliente;
    private String rutaImagen;

    private CatalogoEnum validarDatos() {
        CatalogoEnum validacion = CatalogoEnum.DATOS_VALIDOS;
        String nombre = this.nombre.getText().trim();
        String telefono = this.telefono.getText().trim();
        String correo = this.correo.getText().trim();
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
        }
        return validacion;
    }

    private void mostrarMensajeError(CatalogoEnum clienteEnum) {
        String mensaje;
        switch (clienteEnum) {
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
        this.cargarImagen();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) {
            this.cargarCliente();
            this.registrar.setText("Guardar");
        } else {
            this.registrar.setText("Registrar");
        }
    }

    public void cargarCliente() {
        this.nombre.setText(this.cliente.getNombre());
        this.correo.setText(this.cliente.getCorreo());
        this.telefono.setText(this.cliente.getTelefono());
        this.domicilio.setText(this.cliente.getDireccion());
        Image imagenCliente = CopiarArchivo.obtenerFotoUsuario("Cliente", cliente.getIdCliente());
        if (imagenCliente != null) {
            this.imagen.setImage(imagenCliente);
        }
    }

    public void cargarImagen() {
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }

    public boolean registrarCliente() {
        boolean realizado = false;
        this.cliente = new Cliente();
        this.cliente.setNombre(this.nombre.getText().trim());
        this.cliente.setTelefono(this.telefono.getText().trim());
        this.cliente.setCorreo(this.correo.getText().trim());
        this.cliente.setDireccion(this.domicilio.getText().trim());
        this.cliente.setFecha(new Date());
        if (this.cliente.registrarCliente()) {
            realizado = true;
            this.registrar.setText("Guardar");
        }
        return realizado;
    }

    public boolean editarCliente() {
        boolean realizado = false;
        this.cliente.setNombre(this.nombre.getText().trim());
        this.cliente.setTelefono(this.telefono.getText().trim());
        this.cliente.setCorreo(this.correo.getText().trim());
        this.cliente.setDireccion(this.domicilio.getText().trim());
        if (this.cliente.editarCliente()) {
            realizado = true;
        }
        return realizado;
    }

    public void registrar_OnClick() {
        CatalogoEnum alumnoEnum = this.validarDatos();
        if (alumnoEnum.equals(CatalogoEnum.DATOS_VALIDOS)) {
            boolean realizado = false;
            if (this.cliente != null) {
                realizado = this.editarCliente();
            } else {
                realizado = this.registrarCliente();
            }
            if (!realizado) {
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el cliente", Alert.AlertType.ERROR);
            } else {
                try {
                    if (rutaImagen != null) {
                        CopiarArchivo.guardar("cliente", rutaImagen, this.cliente.getIdCliente());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VentanaCRUClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                MessageFactory.showMessage("Éxito", "Registro", "El cliente se guardó exitosamente", Alert.AlertType.INFORMATION);
            }
        } else {
            this.mostrarMensajeError(alumnoEnum);
        }
    }

    public void imagen_onClick() {
        Image imagenBusqueda = CopiarArchivo.buscarFoto();
        if (imagenBusqueda != null) {
            this.rutaImagen = CopiarArchivo.rutaImagen();
            this.imagen.setImage(imagenBusqueda);
        }
    }
}
