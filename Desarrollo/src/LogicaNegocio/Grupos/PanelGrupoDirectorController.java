package LogicaNegocio.Grupos;

import InterfazGrafica.Grupos.VentanaCRUGrupo;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Paneles;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PanelGrupoDirectorController extends Calendarizable implements Initializable {
    @FXML
    private Label nombreGrupo;
    @FXML
    private Label nombreProfesor;
    @FXML
    private Label horas;
    @FXML
    private ImageView verAlumnos;
    @FXML
    private ImageView editar;
    @FXML
    private ImageView darDeBaja;
    
    private String horaInicio;
    private String horaFin;
    private Grupo grupo;
    private Lanzador lanzador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.verAlumnos.setImage(new Image("/RecursosGraficos/darkGroupIcon.png"));
        this.editar.setImage(new Image("/RecursosGraficos/darkPencilIcon.png"));
        this.darDeBaja.setImage(new Image("/RecursosGraficos/darkCrossIcon.png"));
    }
    
    public void iniciar(String horaInicio, String horaFin, Grupo grupo, Lanzador lanzador, AnchorPane pane){
        this.setPane(pane);
        this.lanzador = lanzador;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.grupo = grupo;
        super.setValor(Horas.getSegundos(this.horaInicio));
        this.recargar();
        this.horas.setText(this.horaInicio + " - " + this.horaFin);
    }
    public Grupo getGrupo(){
        return this.grupo;
    }
    public void recargar(){
        this.nombreGrupo.setText(this.grupo.getNombre());
        this.nombreProfesor.setText(this.grupo.getProfesor().getNombre());
    }
    
    public void verAlumnos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelAlumnosGrupoDirector.fxml");
        PanelAlumnosGrupoDirectorController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.grupo, this.lanzador);
    }
    public void editar_onClick(){
        new VentanaCRUGrupo(this.grupo, this.lanzador);
    }
    public void darDeBaja_onClick(){
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de acción");
        alerta.setHeaderText("Eliminar grupo");
        alerta.setContentText("¿Está seguro de eliminar el grupo " + this.grupo.getNombre() + "?");
        alerta.showAndWait();
        if (alerta.getResult().getButtonData().isDefaultButton()){
            this.grupo.setEstado(0);
            try {
                if (this.grupo.editarGrupo(this.grupo.getHorario().getDias())){
                    Object[] objetos = {this.grupo};
                    this.lanzador.enviarEvento(Paneles.GRUPOS_Y_RENTAS, "baja", objetos);
                    MessageFactory.showMessage("Éxito", "Eliminar grupo", "El grupo fue eliminado", AlertType.INFORMATION);
                }else{
                    this.grupo.setEstado(1);
                    MessageFactory.showMessage("Error", "Eliminar grupo", "El grupo no fue eliminado", AlertType.ERROR);
                }
            } catch (HorarioException ex) {
                Logger.getLogger(PanelGrupoDirectorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
