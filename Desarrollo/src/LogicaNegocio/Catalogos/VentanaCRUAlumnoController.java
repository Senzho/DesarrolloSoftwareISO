package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Paneles;
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
    private String rutaImagen;

    private Alumno alumno;
    private Lanzador lanzador;

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

    private void mostrarMensajeError(CatalogoEnum catalogoEnum) {
        String mensaje;
        switch (catalogoEnum) {
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

    public void cargarAlumno() {
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
        this.direccion.setText(this.alumno.getDireccion());
        Image imagenUsuario = CopiarArchivo.obtenerFotoUsuario("alumno", alumno.getIdAlumno());
        if (imagenUsuario != null) {
            this.imagen.setImage(imagenUsuario);
        }
    }

    public void iniciar(Alumno alumno, Lanzador lanzador) {
        this.alumno = alumno;
        this.lanzador = lanzador;
        if (alumno != null) {
            this.cargarAlumno();
            this.registrar.setText("Guardar");
            if (this.alumno.isEstado()) {
                this.activo.setSelected(true);
            } else {
                this.inactivo.setSelected(true);
            }
        } else {
            this.inactivo.setSelected(true);
            this.registrar.setText("Registrar");
        }
    }

    public void cargarImagen() {
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }

    public boolean registrarAlumno() {
        boolean realizado = false;
        this.alumno = new Alumno();
        this.alumno.setNombre(this.nombre.getText().trim());
        this.alumno.setTeléfono(this.telefono.getText().trim());
        this.alumno.setCorreo(this.correo.getText().trim());
        this.alumno.setDireccion(this.direccion.getText().trim());
        this.alumno.setFecha(new Date());
        this.alumno.setEstado(this.activo.isSelected());
        if (this.alumno.registrarAlumno()) {
            realizado = true;
            this.registrar.setText("Guardar");
        }
        return realizado;
    }

    public boolean editarAlumno() {
        boolean realizado = false;
        this.alumno.setNombre(this.nombre.getText().trim());
        this.alumno.setTeléfono(this.telefono.getText().trim());
        this.alumno.setCorreo(this.correo.getText().trim());
        this.alumno.setDireccion(this.direccion.getText().trim());
        this.alumno.setEstado(this.activo.isSelected());
        if (this.alumno.editarAlumno()) {
            realizado = true;
        }
        return realizado;
    }

    public void registrar_OnClick() {
        CatalogoEnum alumnoEnum = this.validarDatos();
        if (alumnoEnum.equals(CatalogoEnum.DATOS_VALIDOS)) {
            boolean realizado = false;
            if (this.alumno != null) {
                realizado = this.editarAlumno();
                if (realizado){
                    Object[] objetos = {this.alumno};
                    this.lanzador.enviarEvento(Paneles.CATALOGO_ALUMNOS, "editado", objetos);
                }
            } else {
                realizado = this.registrarAlumno();
                if (realizado){
                    Object[] objetos = {this.alumno};
                    this.lanzador.enviarEvento(Paneles.CATALOGO_ALUMNOS, "agregado", objetos);
                }
            }
            if (!realizado) {
                MessageFactory.showMessage("Error", "Registro", "No se pudo guardar el alumno", Alert.AlertType.ERROR);
            } else {
                try {
                    if (rutaImagen != null) {
                        CopiarArchivo.guardar("Alumno", rutaImagen, this.alumno.getIdAlumno());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VentanaCRUAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                MessageFactory.showMessage("Éxito", "Registro", "El alumno se guardó exitosamente", Alert.AlertType.INFORMATION);
            }
        } else {
            this.mostrarMensajeError(alumnoEnum);
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

    public void imagenUsuario_onClick() {
        Image imagenBusqueda = CopiarArchivo.buscarFoto();
        if (imagenBusqueda != null) {
            this.imagen.setImage(imagenBusqueda);
            rutaImagen = CopiarArchivo.rutaImagen();
        }
    }
}
