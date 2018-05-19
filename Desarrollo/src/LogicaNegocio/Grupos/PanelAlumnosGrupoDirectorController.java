package LogicaNegocio.Grupos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Catalogos.PanelAlumnoDirectorController;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelAlumnosGrupoDirectorController implements Initializable {
    @FXML
    private Label nombreProfesor;
    @FXML
    private ComboBox comboGrupos;
    @FXML
    private VBox panelAlumnos;
    @FXML
    private ImageView imagen;
    
    private List<Grupo> listaGrupos;
    
    private void cargarCombo(){
        this.listaGrupos.forEach((grupo) -> {
            this.comboGrupos.getItems().add(grupo.getNombre());
        });
    }
    private void cargarGrupo(Grupo grupo){
        this.nombreProfesor.setText(grupo.getProfesor().getNombre());
        this.cargarImagen(grupo.getProfesor().getIdProfesor());
        List<Alumno> alumnos = new AlumnoDAOSql().obtenerAlumnos(grupo.getId());
        Collections.sort(alumnos);
        this.panelAlumnos.getChildren().clear();
        alumnos.forEach((alumno) -> {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/PanelAlumnoDirector.fxml"));
                AnchorPane pane = loader.load();
                PanelAlumnoDirectorController controller = loader.getController();
                controller.setAlumno(alumno);
                this.panelAlumnos.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(PanelAlumnosGrupoDirectorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private Grupo getGrupo(String nombre){
        Grupo grupo = null;
        for (Grupo grupoLista : this.listaGrupos) {
            if (grupoLista.getNombre().equals(nombre)){
                grupo = grupoLista;
                break;
            }
        }
        return grupo;
    }
    private void cargarImagen(int idProfesor){
        Image imagenProfesor = CopiarArchivo.obtenerFotoUsuario("profesor", idProfesor);
        if (imagenProfesor != null) {
            this.imagen.setImage(imagenProfesor);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void iniciar(Grupo grupo){
        this.listaGrupos = new GrupoDAOSql().obtenerGrupos();
        this.cargarCombo();
        this.comboGrupos.setValue(grupo.getNombre());
        this.cargarGrupo(grupo);
    }
    
    public void comboGrupos_onAction(){
        this.cargarGrupo(this.getGrupo(this.comboGrupos.getValue().toString()));
    }
}
