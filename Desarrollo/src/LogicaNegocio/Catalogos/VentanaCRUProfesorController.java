package LogicaNegocio.Catalogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class VentanaCRUProfesorController implements Initializable {
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
    @FXML
    private ComboBox tipoPago;
    @FXML
    private TextField monto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void registrar_OnClick(){
        
    }
    public void activo_OnClick(){
        
    }
    public void inactivo_OnClick(){
        
    }
}
