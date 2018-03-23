package LogicaNegocio.Catalogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PanelClienteController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private ImageView editar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void editar_OnClick(){
        
    }
}
