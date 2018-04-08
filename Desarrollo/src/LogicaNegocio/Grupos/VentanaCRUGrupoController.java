package LogicaNegocio.Grupos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class VentanaCRUGrupoController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox profesores;
    @FXML
    private FlowPane panelHorario;
    @FXML
    private ImageView agregarDia;
    @FXML
    private Button guardar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.agregarDia.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
    }
    
    public void nuevoDia(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelDiaGrupo.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            PanelDiaGrupoController controller = loader.getController();
            this.panelHorario.getChildren().add(panel);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCRUGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarDia_onClick(){
        this.nuevoDia();
    }
}
