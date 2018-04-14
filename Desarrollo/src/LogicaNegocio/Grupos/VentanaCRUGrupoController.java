package LogicaNegocio.Grupos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TextField danza;
    @FXML
    private ComboBox profesores;
    @FXML
    private FlowPane panelHorario;
    @FXML
    private ImageView agregarDia;
    @FXML
    private Button guardar;
    
    private List<Dia> dias;
    private Grupo grupo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dias = new ArrayList();
        this.agregarDia.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
    }
    
    public void setGrupo(Grupo grupo){
        this.grupo = grupo;
        this.cargarGrupo();
    }
    public void cargarGrupo(){
        this.nombre.setText(this.grupo.getNombre());
        this.danza.setText(this.grupo.getDanza());
        this.profesores.setValue(String.valueOf(this.grupo.getProfesor().getNombre()));
        this.grupo.getHorario().getDias().forEach((dia) -> {
            this.nuevoDia(dia);
        });
    }
    public void nuevoDia(Dia dia){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelDiaGrupo.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            PanelDiaGrupoController controller = loader.getController();
            if (dia != null){
                controller.setDia(dia);
            }
            this.dias.add(controller.getDia());
            this.panelHorario.getChildren().add(panel);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCRUGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarDia_onClick(){
        this.nuevoDia(null);
    }
}
