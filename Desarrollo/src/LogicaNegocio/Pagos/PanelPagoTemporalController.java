package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelPagoTemporalController implements Initializable {
    @FXML
    private ImageView imagenProfesor;
    @FXML
    private ImageView imagenAlumno;
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
    
    private void cargarPago(){
        this.labelFecha.setText(Dates.getSentence(this.pagoTemporal.getFecha()));
        this.labelMonto.setText("$" + this.pagoTemporal.getMonto());
        this.labelNombreAlumno.setText(this.pagoTemporal.getAlumno().getNombre());
        this.labelNombreProfesor.setText(this.pagoTemporal.getProfesor().getNombre());
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
        
    }
    public void iniciar(PagoTemporal pago){
        this.pagoTemporal = pago;
        this.cargarPago();
    }
}
