package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Lanzador;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private int idProfesor;
    private List<Grupo> listaGrupos;
    private AnchorPane pane;
    
    private void mostrarGrupos(){
        this.panelGrupos.getChildren().clear();
        this.listaGrupos.forEach((grupo) -> {
            this.agregarGrupo(grupo);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void iniciar(int idProfesor, Lanzador lanzador, AnchorPane pane){
        this.lanzador = lanzador;
        this.idProfesor = idProfesor;
        this.pane = pane;
        this.listaGrupos = new GrupoDAOSql().obtenerGruposProfesor(idProfesor);
        this.mostrarGrupos();
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
    public AnchorPane getPane(){
        return this.pane;
    }
    public void grupoAgregado(Grupo grupo){
        if (grupo.getProfesor().getIdProfesor() == this.idProfesor){
            this.listaGrupos.add(grupo);
            this.agregarGrupo(grupo);
        }
    }
    public void grupoEditado(Grupo grupo){
        if (grupo.getProfesor().getIdProfesor() == this.idProfesor){
            for (int i = 0; i < this.listaGrupos.size(); i ++){
                if (this.listaGrupos.get(i).getId() == grupo.getId()){
                    this.listaGrupos.set(i, grupo);
                    this.mostrarGrupos();
                    break;
                }
            }
        }
    }
    public void grupoBaja(Grupo grupo){
        if (grupo.getProfesor().getIdProfesor() == this.idProfesor){
            for (Grupo grupoLista : this.listaGrupos){
                if (grupoLista.getId() == grupo.getId()){
                    this.listaGrupos.remove(grupoLista);
                    this.mostrarGrupos();
                    break;
                }
            }
        }
    }
}
