package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Lanzador;
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
    private Lanzador lanzador;
    
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    
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
            PanelGrupoProfesorController controller = loader.getController();
            controller.setLanzador(lanzador);
            controller.setGrupo(grupo);
            this.panelGrupos.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelGruposProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
