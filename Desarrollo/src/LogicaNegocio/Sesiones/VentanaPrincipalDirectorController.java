package LogicaNegocio.Sesiones;

import InterfazGrafica.Grupos.VentanaCRUGrupo;
import InterfazGrafica.Inscripciones.VentanaInscribirAlumno;
import InterfazGrafica.Pagos.VentanaRegistrarPagoTemporal;
import InterfazGrafica.Pagos.VentanaRegistrarRenta;
import LogicaNegocio.Asistencia.PanelAsistenciaController;
import LogicaNegocio.Catalogos.PanelCatalogoClientesController;
import LogicaNegocio.Catalogos.PanelCatalogoProfesoresController;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Catalogos.VentanaCRUAlumno;
import LogicaNegocio.Catalogos.VentanaCRUCliente;
import LogicaNegocio.Catalogos.VentanaCRUProfesor;
import LogicaNegocio.Egresos.PanelIngresosEgresosController;
import LogicaNegocio.Egresos.VentanaCRUEgreso;
import LogicaNegocio.Egresos.VentanaCRUGastoPromocional;
import LogicaNegocio.Grupos.PanelGruposProfesorController;
import LogicaNegocio.Grupos.PanelSemanaController;
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
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelSemana.fxml");
        PanelSemanaController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.lanzador);
    }    
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
    public void menuRegistrarAlumno_onClick(){
        new VentanaCRUAlumno();
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
    public void menuReporteMensual_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Egresos/PanelIngresosEgresos.fxml");
        PanelIngresosEgresosController controller =  lanzador.getCargador().getController();
        controller.setProfesor(profesor);
    }
    public void menuProfesor_onclick(){
        this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoProfesores.fxml");
        PanelCatalogoProfesoresController controller =  lanzador.getCargador().getController();
        controller.setLanzador(lanzador);
        controller.cargarProfesores(new Profesor().obtenerProfesores());
    }
    public void menuAlumno_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoAlumnos.fxml");
    }
    public void menuCliente_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoClientes.fxml");
        PanelCatalogoClientesController controller  = lanzador.getCargador().getController();
        controller.setLanzador(lanzador);
    }
    public void menuMisGrupos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
        PanelGruposProfesorController controller = this.lanzador.getCargador().getController();
        controller.setLanzador(lanzador);
        controller.setIdProfesor(this.profesor.getIdProfesor());
    }
    public void menuCrearGrupo_onClick(){
        new VentanaCRUGrupo();
    }
    public void menuConsultarEgresos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Egresos/PanelConsultarPromocionesEgresos.fxml");
    }
    public void menuInscripcion_onClick(){
        new VentanaInscribirAlumno(this.profesor.getIdProfesor());
    }
    public void menuAistencia_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Asistencia/PanelAsistencia.fxml");
        PanelAsistenciaController controller = this.lanzador.getCargador().getController();
        controller.setProfesor(this.profesor.getIdProfesor());
    }
    public void menuVerGruposRentas_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelSemana.fxml");
        PanelSemanaController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.lanzador);
    }
    public void menuNuevaRenta_onClick(){
        new VentanaRegistrarRenta();
    }
    public void menuPagosTemp_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelPagosTemporales.fxml");
    }
    public void menuPagoTemp_onClick(){
        new VentanaRegistrarPagoTemporal();
    }
}
