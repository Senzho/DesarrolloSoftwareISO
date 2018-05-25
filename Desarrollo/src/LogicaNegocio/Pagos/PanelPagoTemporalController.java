package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PanelPagoTemporalController implements Initializable {
    @FXML
    private ImageView imagenProfesor;
    @FXML
    private ImageView imagenAlumno;
    @FXML
    private ImageView imagenEliminar;
    @FXML
    private Label labelNombreProfesor;
    @FXML
    private Label labelNombreAlumno;
    @FXML
    private Label labelTipoPago;
    @FXML
    private Label labelMonto;
    @FXML
    private Label labelFecha;
    
    private PagoTemporal pagoTemporal;
    private AnchorPane pane;
    private PagoTempBorradoListener listener;
    
    private void cargarPago(){
        this.labelFecha.setText(Dates.getSentence(this.pagoTemporal.getFecha()));
        this.labelMonto.setText("$" + this.pagoTemporal.getMonto());
        this.labelNombreAlumno.setText("Alumno: " + this.pagoTemporal.getAlumno().getNombre());
        this.labelNombreProfesor.setText("Profesor: " + this.pagoTemporal.getProfesor().getNombre());
        this.labelTipoPago.setText(this.pagoTemporal.getTipoPago() == 0?"Inscripción":"Mensualidad");
        this.cargarImagen("profesor", this.pagoTemporal.getProfesor().getIdProfesor());
        this.cargarImagen("alumno", this.pagoTemporal.getAlumno().getIdAlumno());
    }
    private void cargarImagen(String tipo, int id){
        Image imagen = CopiarArchivo.obtenerFotoUsuario(tipo, id);
        if (imagen != null) {
            if (tipo.equals("profesor")){
                this.imagenProfesor.setImage(imagen);
            }else{
                this.imagenAlumno.setImage(imagen);
            }
        } else {
            this.imagenAlumno.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
            this.imagenProfesor.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.imagenEliminar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    }
    public void iniciar(PagoTemporal pago, AnchorPane pane, PagoTempBorradoListener listener){
        this.pagoTemporal = pago;
        this.pane = pane;
        this.listener = listener;
        this.cargarPago();
    }
    
    public void eliminar_onClick(){
        if (this.pagoTemporal.eliminarPago()){
            this.listener.pagoBorrado(this.pane, this.pagoTemporal.getIdPago());
            MessageFactory.showMessage("Exito", "Pago temporal", "El pago fue eliminado", Alert.AlertType.INFORMATION);
        }else{
            MessageFactory.showMessage("Error", "Pago temporal", "El pago no fue eliminado", Alert.AlertType.ERROR);
        }
    }
}
