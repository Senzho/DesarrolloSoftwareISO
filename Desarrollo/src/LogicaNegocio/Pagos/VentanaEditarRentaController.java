package LogicaNegocio.Pagos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class VentanaEditarRentaController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private Label nombre;
    @FXML
    private ComboBox dias;
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    @FXML
    private ComboBox salon;
    @FXML
    private TextField monto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void guardar_onClick(){
        
    }
}
