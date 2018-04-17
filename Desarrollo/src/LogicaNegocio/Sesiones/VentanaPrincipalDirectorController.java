package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Catalogos.VentanaCRUAlumno;
import LogicaNegocio.Catalogos.VentanaCRUCliente;
import LogicaNegocio.Catalogos.VentanaCRUProfesor;
import LogicaNegocio.Egresos.VentanaCRUEgreso;
import LogicaNegocio.Egresos.VentanaCRUGastoPromocional;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.VentanaRegistrarPagoAlumno;
import LogicaNegocio.Pagos.VentanaRegistrarPagoProfesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VentanaPrincipalDirectorController implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    private Lanzador lanzador;
    
    @FXML
    private ImageView imagenCambiarCuenta;
    @FXML
    private BorderPane panelPrincipal;
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.imagenCambiarCuenta.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        this.usuario = usuario;
        this.profesor = profesor;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//1 activo 2 baja
        this.lanzador = new Lanzador(this.panelPrincipal);
    }    
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
        /*this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
        PanelGruposProfesorController controller = this.lanzador.getCargador().getController();
        controller.setIdProfesor(new ProfesorDAOSql().obtenerProfesores().get(0).getIdProfesor());*/
    }
    public void menuRegistrarAlumno_onClick(){
        new VentanaCRUAlumno();
        /*this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelAlumnosGrupoProfesor.fxml");
        PanelAlumnosGrupoProfesorController controller = this.lanzador.getCargador().getController();
        controller.setIdProfesor(9);
        controller.setIdGrupo(20);*/
    }
    public void menuRegistrarProfesor_onClick(){
        new VentanaCRUProfesor();
    }
    public void menuRegistrarCliente_onClick(){
        new VentanaCRUCliente();
    }
    public void menuPromocionFacebook_onClick(){
        new VentanaCRUGastoPromocional();
    }
    public void menuEgreso_onClick(){
        new VentanaCRUEgreso();
    }
    public void menuRegistroPagoProfesor_onClick(){
        new VentanaRegistrarPagoProfesor();
    }
    public void menuRegistroPagoAlumno_onClick(){
        new VentanaRegistrarPagoAlumno(this.profesor.getIdProfesor());
    }
}
