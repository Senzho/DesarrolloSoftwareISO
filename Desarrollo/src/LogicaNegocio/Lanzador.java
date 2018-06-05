package LogicaNegocio;

import LogicaNegocio.Asistencia.PanelAsistenciaController;
import LogicaNegocio.Asistencia.PanelRegistroAsistenciasController;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.PanelCatalogoAlumnosController;
import LogicaNegocio.Catalogos.PanelCatalogoProfesoresController;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.PanelConsultarPromocionesEgresosController;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.PanelAlumnosGrupoDirectorController;
import LogicaNegocio.Grupos.PanelAlumnosGrupoProfesorController;
import LogicaNegocio.Grupos.PanelGruposProfesorController;
import LogicaNegocio.Grupos.PanelSemanaController;
import LogicaNegocio.Pagos.PagoProfesor;
import LogicaNegocio.Pagos.PanelHistorialPagoProfesoresController;
import LogicaNegocio.Sesiones.VentanaPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Lanzador {
    private VentanaPrincipal ventanaPrincipal;
    private BorderPane panel;
    private FXMLLoader cargador;
    private AnchorPane panelActual;
    
    private void catalogoAlumnos(String evento, Object[] objetos){
        PanelCatalogoAlumnosController catalogoController = this.ventanaPrincipal.getCatalogoAlumnos();
        if (catalogoController != null){
            if (evento.equals("editado")){
                catalogoController.alumnoEditado((Alumno) objetos[0]);
            }else if (evento.equals("agregado")){
                catalogoController.alumnoAgregado((Alumno) objetos[0]);
            }
        }
        PanelAlumnosGrupoDirectorController alumnosGrupoController = this.ventanaPrincipal.getAlumnosGrupoDirector();
        if (alumnosGrupoController != null){
            if (evento.equals("editado")){
                alumnosGrupoController.alumnoEditado((Alumno) objetos[0]);
            }
        }
        PanelAlumnosGrupoProfesorController alumnosProfesorController = this.ventanaPrincipal.getAlumnosGrupoProfesor();
        if (alumnosProfesorController != null){
            if (evento.equals("editado")){
                alumnosProfesorController.alumnoEditado((Alumno) objetos[0]);
            }
        }
        PanelAsistenciaController asistenciaController = this.ventanaPrincipal.getRegistrarAsistencia();
        if (asistenciaController != null){
            if (evento.equals("editado")){
                asistenciaController.alumnoEditado((Alumno) objetos[0]);
            }
        }
        PanelRegistroAsistenciasController asistenciasController = this.ventanaPrincipal.getHistorialAsistencia();
        if (asistenciasController != null){
            if (evento.equals("editado")){
                asistenciasController.alumnoEditado((Alumno) objetos[0]);
            }
        }
    }
    private void gruposRentas(String evento, Object[] objetos){
        PanelSemanaController semanaController = this.ventanaPrincipal.getGruposRentas();
        if (semanaController != null){
            if (evento.equals("agregado")){
                semanaController.grupoAgregado((Grupo) objetos[0]);
            }else if (evento.equals("editado")){
                semanaController.grupoEditado((Grupo) objetos[0]);
            }
        }
        PanelAlumnosGrupoDirectorController alumnosController = this.ventanaPrincipal.getAlumnosGrupoDirector();
        if (alumnosController != null){
            if (evento.equals("agregado")){
                alumnosController.grupoAgregado((Grupo) objetos[0]);
            }else if (evento.equals("editado")){
                
            }
        }
        PanelAlumnosGrupoProfesorController alumnosProfesorController = this.ventanaPrincipal.getAlumnosGrupoProfesor();
        if (alumnosProfesorController != null){
            if (evento.equals("agregado")){
                alumnosProfesorController.grupoAgregado((Grupo) objetos[0]);
            }else if (evento.equals("editado")){
                
            }
        }
        PanelGruposProfesorController gruposProfesor = this.ventanaPrincipal.getMisGrupos();
        if (gruposProfesor != null){
            if (evento.equals("agregado")){
                gruposProfesor.grupoAgregado((Grupo) objetos[0]);
            }else if (evento.equals("editado")){
                gruposProfesor.grupoEditado((Grupo) objetos[0]);
            }
        }
    }
    private void catalogoProfesores(String evento, Object[] objetos){
        PanelCatalogoProfesoresController controller = this.ventanaPrincipal.getCatalogoProfesores();
        if (controller != null){
            if (evento.equals("agregado")){
                controller.profesorAgregado((Profesor) objetos[0]);
            }else if (evento.equals("editado")){
                controller.profesorEditado((Profesor) objetos[0]);
            }
        }
        PanelSemanaController semanaController = this.ventanaPrincipal.getGruposRentas();
        if (semanaController != null){
            if (evento.equals("editado")){
                semanaController.profesorEditado((Profesor) objetos[0]);
            }
        }
        PanelAlumnosGrupoDirectorController alumnosController = this.ventanaPrincipal.getAlumnosGrupoDirector();
        if (alumnosController != null){
            if (evento.equals("editado")){
                alumnosController.profesorEditado((Profesor) objetos[0]);
            }
        }
    }
    private void pagosProfesor(String evento, Object[] objetos){
        PanelHistorialPagoProfesoresController controller = this.ventanaPrincipal.getPagosProfesor();
        if (controller != null){
            if (evento.equals("registrado")){
                controller.agregarPago((PagoProfesor) objetos[0]);
            }
        }
    }
    
    public Lanzador(BorderPane panel, VentanaPrincipal ventanaPrincipal){
        this.panel = panel;
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public boolean lanzar(String rutaFxml){
        boolean lanzado;
        this.cargador = new FXMLLoader(this.getClass().getResource(rutaFxml));
        try {
            AnchorPane pane = this.cargador.load();
            this.panelActual = pane;
            this.ventanaPrincipal.establecerControlador(this.cargador.getController());
            this.panel.setCenter(pane);
            lanzado = true;
        } catch (IOException ex) {
            lanzado = false;
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lanzado;
    }
    public FXMLLoader getCargador(){
        return this.cargador;
    }
    public AnchorPane getPanelActual(){
        return this.panelActual;
    }
    public VentanaPrincipal getVentanaPrincipal(){
        return this.ventanaPrincipal;
    }
    public void establecerPrincipal(AnchorPane pane){
        this.panelActual = pane;
        this.panel.setCenter(pane);
    }
    public void enviarEvento(Paneles panel, String evento, Object[] objetos){
        Platform.runLater(() -> {
            switch(panel){
                case REGISTRAR_ASISTENCIA:
                    break;
                case HISTORIAL_ASISTENCIA:
                    break;
                case PROXIMOS_PAGOS_PROFESOR:
                    break;
                case PROXIMOS_PAGOS_ALUMNO:
                    break;
                case MIS_GRUPOS:
                    break;
                case ALUMNOS_GRUPO_PROFESOR:
                    break;
                case ALUMNOS_GRUPO_DIRECTOR:
                    break;
                case PAGOS_ALUMNO:
                    break;
                case PAGOS_PROFESOR:
                    this.pagosProfesor(evento, objetos);
                    break;
                case PAGOS_CLIENTE:
                    break;
                case PAGOS_TEMPORALES:
                    break;
                case GRUPOS_Y_RENTAS:
                    this.gruposRentas(evento, objetos);
                    break;
                case RENTAS:
                    break;
                case CATALOGO_ALUMNOS:
                    this.catalogoAlumnos(evento, objetos);
                    break;
                case CATALOGO_PROFESORES:
                    this.catalogoProfesores(evento, objetos);
                    break;
                case CATALOGO_CLIENTES:
                    break;
                case EGRESOS:
                    break;
                case REPORTE:
                    break;
            }
        });
    }
}
