package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.Grupos.VentanaCRUGrupo;
import InterfazGrafica.Pagos.VentanaRegistrarRenta;
import LogicaNegocio.Egresos.Dates;
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
import javafx.scene.layout.VBox;

public class PanelSemanaController implements Initializable {
    @FXML
    private VBox listaDomingo;
    @FXML
    private VBox listaLunes;
    @FXML
    private VBox listaMartes;
    @FXML
    private VBox listaMiercoles;
    @FXML
    private VBox listaJueves;
    @FXML
    private VBox listaViernes;
    @FXML
    private VBox listaSabado;
    
    private List<Grupo> grupos;
    private Lanzador lanzador;
    
    private AnchorPane getPane(String horaInicio, String horaFin, Grupo grupo){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelGrupoDirector.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
            PanelGrupoDirectorController controller = loader.getController();
            controller.iniciar(horaInicio, horaFin, grupo, this.lanzador);
        } catch (IOException ex) {
            Logger.getLogger(PanelSemanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pane;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.grupos = new GrupoDAOSql().obtenerGrupos();
    }
    public void iniciar(Lanzador lanzador){
        this.lanzador = lanzador;
        this.grupos.forEach((grupo) -> {
            grupo.getHorario().getDias().forEach((dia) -> {
                AnchorPane pane = this.getPane(dia.getHoraInicio(), dia.getHoraFin(), grupo);
                switch (dia.getDia()) {
                    case "Lunes":
                        this.listaLunes.getChildren().add(pane);
                        break;
                    case "Martes":
                        this.listaMartes.getChildren().add(pane);
                        break;
                    case "Miercoles":
                        this.listaMiercoles.getChildren().add(pane);
                        break;
                    case "Jueves":
                        this.listaJueves.getChildren().add(pane);
                        break;
                    case "Viernes":
                        this.listaViernes.getChildren().add(pane);
                        break;
                    case "Sabado":
                        this.listaSabado.getChildren().add(pane);
                        break;
                    case "Domingo":
                        this.listaDomingo.getChildren().add(pane);
                        break;
                    default:
                        break;
                }
            });
        });
    }
    
    public void nuevaRenta_onClick(){
        new VentanaRegistrarRenta();
    }
    public void nuevoGrupo_onClick(){
        new VentanaCRUGrupo();
    }
}
