package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PanelGruposProfesorController implements Initializable {
    @FXML
    FlowPane panelGrupos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setIdProfesor(int idProfesor){
        GrupoDAOSql grupoDAO = new GrupoDAOSql();
        this.panelGrupos.getChildren().clear();
        for(Grupo grupo : grupoDAO.obtenerGruposProfesor(idProfesor)){
            this.agregarGrupo(grupo);
        }
    }
    public void agregarGrupo(Grupo grupo){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelGrupoProfesor.fxml"));
        try {
            AnchorPane pane = loader.load();
            pane.setStyle("-fx-background-color: #F5A9E1;");
            PanelGrupoProfesorController controller = loader.getController();
            controller.setGrupo(grupo);
            this.panelGrupos.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelGruposProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
