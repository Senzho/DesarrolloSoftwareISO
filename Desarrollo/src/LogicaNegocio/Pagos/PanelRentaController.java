package LogicaNegocio.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import InterfazGrafica.MessageFactory;
import InterfazGrafica.Pagos.VentanaEditarRenta;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Calendarizable;
import LogicaNegocio.Grupos.Horas;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PanelRentaController extends Calendarizable implements Initializable {
    @FXML
    private Label nombreCliente;
    @FXML
    private Label horas;
    @FXML
    private Label fecha;
    @FXML
    private ImageView editar;
    @FXML
    private ImageView eliminar;
    
    private Renta renta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.editar.setImage(new Image("/RecursosGraficos/darkPencilIcon.png"));
        this.eliminar.setImage(new Image("/RecursosGraficos/darkCrossIcon.png"));
    }
    
    public void iniciar(Renta renta, AnchorPane pane){
        this.renta = renta;
        this.setPane(pane);
        this.setValor(Horas.getSegundos(this.renta.getDia().getHoraInicio()));
        this.horas.setText(this.renta.getDia().getHoraInicio() + " - " + this.renta.getDia().getHoraFin());
        this.nombreCliente.setText(this.renta.getCliente().getNombre());
    }
    public void habilitarFecha(boolean habilitada){
        if (habilitada){
            this.fecha.setText(Dates.getSentence(this.renta.getFecha()));
        }else{
            this.fecha.setText("");
        }
    }
    
    public void editar_onClick(){
        if (Dates.getDiference(new Date(), this.renta.getFecha()) == -1){
            MessageFactory.showMessage("Fecha", "Renta de espacio", "No se puede modificar la renta, la fecha ha pasado", Alert.AlertType.INFORMATION);
        }else{
            new VentanaEditarRenta(this.renta);
        }
    }
    public void eliminarRenta_onClick(){
       if(Dates.getDiference(new Date(),this.renta.getFecha()) > 0){
	        if (this.renta.eliminarRenta(renta.getIdRenta())){
	            MessageFactory.showMessage("Registro eliminado", "Renta eliminada", "El registro se ha eliminado exitosamente", Alert.AlertType.INFORMATION);
	        }else{
	            MessageFactory.showMessage("Error de eliminación", "Renta no eliminada", "El registro no ha sido eliminado", Alert.AlertType.ERROR);
	        }
	    }else{
	    	MessageFactory.showMessage("Error de eliminación", "Renta no eliminada", "La fecha de la renta es anterior al dia actual", Alert.AlertType.ERROR);
	    }
    }
}
