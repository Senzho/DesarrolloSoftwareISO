package LogicaNegocio.Catalogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VentanaCRUClienteController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private TextField correo;
    @FXML
    private TextField telefono;
    @FXML
    private TextField domicilio;
    @FXML
    private Button registrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void registrar_OnClick(){
        
    }
}
