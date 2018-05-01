package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Catalogos.Profesor;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelHistorialPagoProfesoresController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private ImageView imagen;
    @FXML
    private VBox panelPagos;
    @FXML
    private Button registrar;
    
    private Profesor profesor;
    private PagoProfesor pagoProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pagoProfesor = new PagoProfesor();
    }
    
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
        this.nombre.setText(profesor.getNombre());
        this.cargarImagen();
        this.cargarPagos();
    }
    public void cargarImagen(){
        Image imagenProfesor = CopiarArchivo.obtenerFotoUsuario("profesor", this.profesor.getIdProfesor());
        if (imagenProfesor != null) {
            this.imagen.setImage(imagenProfesor);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    public void cargarPagos(){
        List<PagoProfesor> pagosProfesor = pagoProfesor.obtenerPagos(this.profesor.getIdProfesor());
        pagosProfesor.forEach((pago) -> {
            this.agregarPago(pago);
        });
    }
    public void agregarPago(PagoProfesor pago){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoProfesor.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            panel.setStyle("-fx-background-color: #D8D8D8;");
            PanelPagoProfesorController controller = loader.getController();
            controller.setPagoProfesor(pago);
            this.panelPagos.getChildren().add(0, panel);
        } catch (IOException ex) {
            Logger.getLogger(PanelHistorialPagoProfesoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrar_OnClick(){
        this.pagoProfesor.setIdPago(0);
        this.pagoProfesor.setFecha(new Date());
        this.pagoProfesor.setMonto(this.profesor.getMonto());
        this.pagoProfesor.setTipoPago(this.profesor.isTipoPago());
        if (this.pagoProfesor.registrarPago(this.profesor.getIdProfesor())){
            MessageFactory.showMessage("Registro", "Éxito", "El pago fue registrado con éxito", Alert.AlertType.INFORMATION);
            this.agregarPago(this.pagoProfesor);
        }else{
            MessageFactory.showMessage("Registro", "Error", "El pago no se pudo registrar", Alert.AlertType.ERROR);
        }
    }
}
