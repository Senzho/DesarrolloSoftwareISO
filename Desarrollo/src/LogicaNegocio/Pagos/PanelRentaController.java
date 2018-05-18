package LogicaNegocio.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelRentaController implements Initializable {
    @FXML
    private Label nombreCliente;
    @FXML
    private Label horas;
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
    
    public void iniciar(Renta renta){
        this.renta = renta;
        this.horas.setText(this.renta.getDia().getHoraInicio() + " - " + this.renta.getDia().getHoraFin());
    }
}
