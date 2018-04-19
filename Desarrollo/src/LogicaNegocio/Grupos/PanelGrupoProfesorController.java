package LogicaNegocio.Grupos;

import LogicaNegocio.Lanzador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelGrupoProfesorController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private Label danza;
    @FXML
    private ListView horario;
    @FXML
    private ImageView verAlumnos;
    
    private Grupo grupo;
    private Lanzador lanzador;
    
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.verAlumnos.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    
    public void setGrupo(Grupo grupo){
        this.grupo = grupo;
        this.cargarGrupo();
    }
    public void cargarGrupo(){
        this.nombre.setText(this.grupo.getNombre());
        this.danza.setText(this.grupo.getDanza());
        this.grupo.getHorario().getDias().forEach((dia) -> {
            this.horario.getItems().add(dia.getDia() + " " + dia.getHoraInicio() + " - " + dia.getHoraFin() + " salón " + dia.getSalon());
        });
    }
    public void verAlumnos_onClick(){
        lanzador.lanzar("/InterfazGrafica/Grupos/PanelAlumnosGrupoProfesor.fxml");
        PanelAlumnosGrupoProfesorController controller = this.lanzador.getCargador().getController();
        controller.setLanzador(this.lanzador);
        controller.setIdProfesor(this.grupo.getProfesor().getIdProfesor());
        controller.setIdGrupo(grupo.getId());
    }
}
