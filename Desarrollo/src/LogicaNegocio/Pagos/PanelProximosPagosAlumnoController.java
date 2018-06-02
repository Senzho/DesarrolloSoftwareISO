package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Egresos.LogicCalendar;
import LogicaNegocio.Grupos.Grupo;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PanelProximosPagosAlumnoController implements Initializable {
    @FXML
    private ComboBox comboGrupos;
    @FXML
    private VBox panelPagos;
    
    private List<Grupo> listaGrupos;
    private HashMap<Alumno, Date> mapaAlumnos;
    
    private void obtenerProximosPagos(){
        this.mapaAlumnos = new HashMap();
        this.listaGrupos.forEach((grupo) -> {
            List<Alumno> alumnosGrupo = new Alumno().obtenerAlumnos(grupo.getId());
            alumnosGrupo.forEach((alumno) -> {
                PagoAlumno primeraInscripcion = new PagoAlumno().obtenerPrimeraInscripcion(alumno.getIdAlumno(), grupo.getId());
                if (primeraInscripcion != null) {
                    int primerDia = Dates.getDay(primeraInscripcion.getFecha());
                    int diasMesActual = LogicCalendar.getMonthDaysNumber(Dates.getMonth(new Date()), Dates.getYear(new Date()));
                    if (primerDia > diasMesActual){
                        primerDia = diasMesActual;
                    }
                    Date fecha = Dates.toDate(Dates.getYear(new Date()) + "-" + Dates.getMonth(new Date()) + "-" + primerDia);
                    int dif = Dates.getDiference(new Date(), fecha);
                    if (dif < 7 && dif > -1) {
                        this.mapaAlumnos.put(alumno, fecha);
                    }
                }
            });
        });
    }
    private void cargarComboGrupos(){
        this.listaGrupos.forEach((grupo) -> {
            this.comboGrupos.getItems().add(grupo.getNombre());
        });
        this.comboGrupos.setValue(this.listaGrupos.get(0).getNombre());
    }
    private Grupo obtenerGrupoSeleccionado(){
        Grupo grupo = null;
        for (Grupo grupoLista : this.listaGrupos) {
            if (grupoLista.getNombre().equals(this.comboGrupos.getValue().toString())){
                grupo = grupoLista;
                break;
            }
        }
        return grupo;
    }
    private void mostrarPagosGrupo(){
        this.panelPagos.getChildren().clear();
        int idGrupo = this.obtenerGrupoSeleccionado().getId();
        this.mapaAlumnos.keySet().forEach((alumno) -> {
            List<Grupo> gruposInscrito = new Grupo().obtenerGruposAlumno(alumno.getIdAlumno());
            for (Grupo grupo : gruposInscrito){
                if (grupo.getId() == idGrupo){
                    this.agregarPago(alumno, this.mapaAlumnos.get(alumno));
                    break;
                }
            }
        });
    }
    private void mostrarPagos(){
        this.panelPagos.getChildren().clear();
        this.mapaAlumnos.keySet().forEach((alumno) -> {
            this.agregarPago(alumno, this.mapaAlumnos.get(alumno));
        });
    }
    private void agregarPago(Alumno alumno, Date fecha){
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoProximoAlumno.fxml"));
            AnchorPane pane = loader.load();
            PanelPagoProximoAlumnoController controller = loader.getController();
            controller.iniciar(alumno, fecha);
            this.panelPagos.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelProximosPagosAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void iniciar(int idProfesor){
        this.listaGrupos = new Grupo().obtenerGruposProfesor(idProfesor);
        if (this.listaGrupos.isEmpty()){
            MessageFactory.showMessage("Información", "Grupos", "No hay grupos", Alert.AlertType.WARNING);
        }else{
            this.cargarComboGrupos();
            this.obtenerProximosPagos();
            this.mostrarPagos();
        }
    }
    
    //Eventos:
    
    public void comboGrupos_onAction(){
        this.mostrarPagosGrupo();
    }
    public void botonTodos_onClick(){
        if (this.listaGrupos.isEmpty()){
            MessageFactory.showMessage("Información", "Grupos", "No hay grupos", Alert.AlertType.WARNING);
        }else{
            this.mostrarPagos();
        }
    }
}
