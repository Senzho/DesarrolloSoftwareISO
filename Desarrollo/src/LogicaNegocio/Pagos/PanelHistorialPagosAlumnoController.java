package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelHistorialPagosAlumnoController implements Initializable {
    private Stage stage;
    private Alumno alumno;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCorreo;
    @FXML
    private VBox panelPagos;
    @FXML
    private ImageView imagenAlumno;
    
    private List<PagoAlumno> pagos;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void inicializarPanelPagos() {
        lblNombre.setText(alumno.getNombre());
        lblCorreo.setText(alumno.getCorreo());
        this.imagenAlumno.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        pagos = new PagoAlumno().obtenerPagos(alumno.getIdAlumno());
        this.panelPagos.getChildren().clear();
        for(int i = pagos.size() - 1; i > - 1; i--){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoAlumno.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPagoAlumnoController controller = loader.getController();
                controller.setPago(pagos.get(i));
                this.panelPagos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelPromocionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
