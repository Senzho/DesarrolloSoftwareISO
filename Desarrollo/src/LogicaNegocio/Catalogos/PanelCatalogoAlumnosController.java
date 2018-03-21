package LogicaNegocio.Catalogos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PanelCatalogoAlumnosController implements Initializable {
    @FXML
    private TextField busqueda;
    @FXML
    private Button buscar;
    @FXML
    private AnchorPane panelAlumnos;

    public void initialize(URL url, ResourceBundle rb) {
        this.cargarAlumnos();
    }    
    
    public void cargarAlumnos(){
        AlumnoDAOSql alumnoDAO = new AlumnoDAOSql();
        alumnoDAO.obtenerAlumnos().forEach((alumno) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelAlumnoDirector.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                PanelAlumnoDirectorController controller = loader.getController();
                controller.setAlumno(alumno);
                this.panelAlumnos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelCatalogoAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void buscar_OnClick(){
        
    }
}
