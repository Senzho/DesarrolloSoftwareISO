package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelHistorialPagoProfesoresController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelProfesorController implements Initializable {

    @FXML
    private ImageView imagen;
    @FXML
    private Label nombre;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private ImageView baja;
    @FXML
    private ImageView editar;
    @FXML
    private Button pagos;

    private Profesor profesor;
    private Lanzador lanzador;

    private void establecerIconos() {
        if (this.profesor.isEstado()) {
            this.baja.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
        } else {
            this.baja.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
        }
        this.editar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void iniciar(Profesor profesor, Lanzador lanzador){
        this.profesor = profesor;
        this.lanzador = lanzador;
        this.cargarProfesor();
        this.establecerIconos();
        this.cargarImagen();
    }

    public void cargarProfesor() {
        this.nombre.setText(this.profesor.getNombre());
        this.correo.setText(this.profesor.getCorreo());
        this.telefono.setText(this.profesor.getTelefono());
    }

    public void cargarImagen() {
        Image imagenProfesor = CopiarArchivo.obtenerFotoUsuario("profesor", profesor.getIdProfesor());
        if (imagenProfesor != null) {
            this.imagen.setImage(imagenProfesor);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }

    public void baja_OnClick() {
        this.profesor.setEstado(!this.profesor.isEstado());
        if (this.profesor.editarProfesor()) {
            this.establecerIconos();
            String estado;
            if (this.profesor.isEstado()) {
                estado = "activo";
            } else {
                estado = "inactivo";
            }
            MessageFactory.showMessage("Éxito", "Registro", "El estado del profesor cambió a " + estado, Alert.AlertType.INFORMATION);
        } else {
            MessageFactory.showMessage("Error", "Registro", "No se pudo cambiar el estado del profesor", Alert.AlertType.ERROR);
        }
    }

    public void editar_OnClick() {
        new VentanaCRUProfesor(this.profesor, this.lanzador);
    }

    public void pagos_OnClick() {
        lanzador.lanzar("/InterfazGrafica/Pagos/PanelHistorialPagoProfesores.fxml");
        PanelHistorialPagoProfesoresController controller = lanzador.getCargador().getController();
        controller.iniciar(this.profesor, this.lanzador);
    }
}
