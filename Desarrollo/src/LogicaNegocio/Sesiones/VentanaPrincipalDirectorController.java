package LogicaNegocio.Sesiones;

import InterfazGrafica.Grupos.VentanaCRUGrupo;
import InterfazGrafica.Inscripciones.VentanaInscribirAlumno;
import InterfazGrafica.Pagos.VentanaRegistrarPagoTemporal;
import InterfazGrafica.Pagos.VentanaRegistrarRenta;
import LogicaNegocio.Asistencia.PanelAsistenciaController;
import LogicaNegocio.Asistencia.PanelRegistroAsistenciasController;
import LogicaNegocio.Catalogos.PanelCatalogoAlumnosController;
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
import LogicaNegocio.Pagos.PanelConsultarRentasController;
import LogicaNegocio.Pagos.PanelProximosPagosAlumnoController;
import LogicaNegocio.Pagos.PanelProximosPagosProfesorController;
import LogicaNegocio.Pagos.VentanaRegistrarPagoAlumno;
import LogicaNegocio.Pagos.VentanaRegistrarPagoProfesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VentanaPrincipalDirectorController extends VentanaPrincipal implements Initializable {
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
        this.lanzador = new Lanzador(this.panelPrincipal, this);
        this.menuVerGruposRentas_onClick();
    }    
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
    public void menuRegistrarAlumno_onClick(){
        new VentanaCRUAlumno(this.lanzador);
    }
    public void menuRegistrarProfesor_onClick(){
        new VentanaCRUProfesor(this.lanzador);
    }
    public void menuRegistrarCliente_onClick(){
        new VentanaCRUCliente(this.lanzador);
    }
    public void menuPromocionFacebook_onClick(){
        new VentanaCRUGastoPromocional();
    }
    public void menuEgreso_onClick(){
        new VentanaCRUEgreso();
    }
    public void menuRegistroPagoProfesor_onClick(){
        new VentanaRegistrarPagoProfesor(this.lanzador);
    }
    public void menuRegistroPagoAlumno_onClick(){
        new VentanaRegistrarPagoAlumno(this.profesor.getIdProfesor(), this.lanzador);
    }
    public void menuReporteMensual_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Egresos/PanelIngresosEgresos.fxml");
        PanelIngresosEgresosController controller =  lanzador.getCargador().getController();
        controller.setProfesor(profesor);
    }
    public void menuProximosPagosProfesor_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelProximosPagosProfesor.fxml");
        PanelProximosPagosProfesorController controller =  lanzador.getCargador().getController();
        controller.buscarProfesores();
        controller.generarProximoPago();
    }
    public void menuProfesor_onclick(){
        PanelCatalogoProfesoresController controller = this.getCatalogoProfesores();
        if (controller != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoProfesores.fxml");
            this.getCatalogoProfesores().inicar(this.lanzador, this.lanzador.getPanelActual());
        }
    }
    public void menuAlumno_onClick(){
        PanelCatalogoAlumnosController controller = this.getCatalogoAlumnos();
        if (controller != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoAlumnos.fxml");
            this.getCatalogoAlumnos().iniciar(this.lanzador, this.lanzador.getPanelActual());
        }  
    }
    public void menuCliente_onClick(){
        PanelCatalogoClientesController controller  = this.getCatalogoClientes();
        if (controller != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Catalogos/PanelCatalogoClientes.fxml");
            this.getCatalogoClientes().iniciar(this.lanzador, this.lanzador.getPanelActual());
        }
    }
    public void menuMisGrupos_onClick(){
        PanelGruposProfesorController controller = this.getMisGrupos();
        if (controller != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
            controller = this.getMisGrupos();
            controller.iniciar(this.profesor.getIdProfesor(), this.lanzador, this.lanzador.getPanelActual());
        }
    }
    public void menuCrearGrupo_onClick(){
        new VentanaCRUGrupo(this.lanzador);
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
    public void menuHistorialAsistencia_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Asistencia/PanelRegistroAsistencias.fxml");
        PanelRegistroAsistenciasController controller = this.lanzador.getCargador().getController();
        controller.setProfesor(this.profesor);
    }
    public void menuVerGruposRentas_onClick(){
        PanelSemanaController controller = this.getGruposRentas();
        if (this.getGruposRentas() != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelSemana.fxml");
            this.getGruposRentas().iniciar(this.lanzador, this.lanzador.getPanelActual());
        }
    }
    public void menuNuevaRenta_onClick(){
        new VentanaRegistrarRenta(this.lanzador);
    }
    public void menuPagosTemp_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelPagosTemporales.fxml");
    }
    public void menuPagoTemp_onClick(){
        new VentanaRegistrarPagoTemporal();
    }
    public void menuRentas_onClick(){
        PanelConsultarRentasController controller = this.getRentas();
        if (controller != null){
            this.panelPrincipal.setCenter(controller.getPane());
        }else{
            this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelConsultarRentas.fxml");
            this.getRentas().iniciar(this.lanzador, this.lanzador.getPanelActual());
        }
    }
    public void menuProximosAlumno_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelProximosPagosAlumno.fxml");
        PanelProximosPagosAlumnoController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.profesor.getIdProfesor());
    }
}
