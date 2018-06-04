package LogicaNegocio;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.PanelCatalogoAlumnosController;
import LogicaNegocio.Sesiones.VentanaPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Lanzador {
    private VentanaPrincipal ventanaPrincipal;
    private BorderPane panel;
    private FXMLLoader cargador;
    private AnchorPane panelActual;
    
    private void catalogoAlumnos(String evento, Object[] objetos){
        PanelCatalogoAlumnosController controller = this.ventanaPrincipal.getCatalogoAlumnos();
        if (controller != null){
            if (evento.equals("editado")){
                controller.alumnoEditado((Alumno) objetos[0]);
            }else if (evento.equals("agregado")){
                controller.alumnoAgregado((Alumno) objetos[0]);
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
    public void enviarEvento(Paneles panel, String evento, Object[] objetos){
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
                break;
            case PAGOS_CLIENTE:
                break;
            case PAGOS_TEMPORALES:
                break;
            case GRUPOS_Y_RENTAS:
                break;
            case RENTAS:
                break;
            case CATALOGO_ALUMNOS:
                this.catalogoAlumnos(evento, objetos);
                break;
            case CATALOGO_PROFESORES:
                break;
            case CATALOGO_CLIENTES:
                break;
            case EGRESOS:
                break;
            case REPORTE:
                break;
        }
    }
}
