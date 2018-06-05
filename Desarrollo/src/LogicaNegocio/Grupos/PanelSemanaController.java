package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import Accesodatos.Pagos.RentaDAOSql;
import InterfazGrafica.Grupos.VentanaCRUGrupo;
import InterfazGrafica.Pagos.VentanaRegistrarRenta;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelRentaController;
import LogicaNegocio.Pagos.Renta;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    private List<Calendarizable> panelesLunes;
    private List<Calendarizable> panelesMartes;
    private List<Calendarizable> panelesMiercoles;
    private List<Calendarizable> panelesJueves;
    private List<Calendarizable> panelesViernes;
    private List<Calendarizable> panelesSabado;
    private List<Calendarizable> panelesDomingo;
    private AnchorPane pane;
    
    private PanelGrupoDirectorController getControllerGrupo(String horaInicio, String horaFin, Grupo grupo){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelGrupoDirector.fxml"));
        PanelGrupoDirectorController controller = null;
        try {
            AnchorPane pane = loader.load();
            controller = loader.getController();
            controller.iniciar(horaInicio, horaFin, grupo, this.lanzador, pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelSemanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }
    private PanelRentaController getControllerRenta(Renta renta){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelRenta.fxml"));
        PanelRentaController controller = null;
        try {
            AnchorPane pane = loader.load();
            controller = loader.getController();
            controller.iniciar(renta, pane);
        } catch (IOException ex) {
            Logger.getLogger(PanelSemanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }
    private void limpiar(){
        this.listaDomingo.getChildren().clear();
        this.listaJueves.getChildren().clear();
        this.listaLunes.getChildren().clear();
        this.listaMartes.getChildren().clear();
        this.listaMiercoles.getChildren().clear();
        this.listaSabado.getChildren().clear();
        this.listaViernes.getChildren().clear();
        this.panelesDomingo.clear();
        this.panelesJueves.clear();
        this.panelesLunes.clear();
        this.panelesMartes.clear();
        this.panelesMiercoles.clear();
        this.panelesSabado.clear();
        this.panelesViernes.clear();
    }
    private void agregarControladorRenta(PanelRentaController controller, int dia){
        switch(dia){
            case 0:
                this.panelesDomingo.add(controller);
                break;
            case 1:
                this.panelesLunes.add(controller);
                break;
            case 2:
                this.panelesMartes.add(controller);
                break;
            case 3:
                this.panelesMiercoles.add(controller);
                break;
            case 4:
                this.panelesJueves.add(controller);
                break;
            case 5:
                this.panelesViernes.add(controller);
                break;
            default:
                this.panelesSabado.add(controller);
                break;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.grupos = new GrupoDAOSql().obtenerGrupos();
        this.panelesLunes = new ArrayList();
        this.panelesMartes = new ArrayList();
        this.panelesMiercoles = new ArrayList();
        this.panelesJueves = new ArrayList();
        this.panelesViernes = new ArrayList();
        this.panelesSabado = new ArrayList();
        this.panelesDomingo = new ArrayList();
    }
    public void iniciar(Lanzador lanzador, AnchorPane pane){
        this.lanzador = lanzador;
        this.pane = pane;
        this.cargarTodo();
    }
    public void cargarTodo(){
        this.limpiar();
        this.cargarGrupos();
        this.cargarRentas();
        this.cargarPaneles();
    }
    public void cargarGrupos(){
        this.grupos.forEach((grupo) -> {
            grupo.getHorario().getDias().forEach((dia) -> {
                PanelGrupoDirectorController controller = this.getControllerGrupo(dia.getHoraInicio(), dia.getHoraFin(), grupo);
                switch (dia.getDia()) {
                    case "Lunes":
                        this.panelesLunes.add(controller);
                        break;
                    case "Martes":
                        this.panelesMartes.add(controller);
                        break;
                    case "Miercoles":
                        this.panelesMiercoles.add(controller);
                        break;
                    case "Jueves":
                        this.panelesJueves.add(controller);
                        break;
                    case "Viernes":
                        this.panelesViernes.add(controller);
                        break;
                    case "Sabado":
                        this.panelesSabado.add(controller);
                        break;
                    case "Domingo":
                        this.panelesDomingo.add(controller);
                        break;
                    default:
                        break;
                }
            });
        });
    }
    public void cargarRentas(){
        List<Renta> rentas = new RentaDAOSql().obtenerRentas();
        Date fechaActual = new Date();
        rentas.forEach((renta) -> {
            int dia = LocalDate.of(Dates.getYear(fechaActual), Dates.getMonth(fechaActual), Dates.getDay(fechaActual)).getDayOfWeek().getValue();
            int dif = Dates.getDiference(fechaActual, renta.getFecha());
            PanelRentaController controller = this.getControllerRenta(renta);
            if (dif > -1){
                if ((dif < 7 - dia)){
                    Date fechRen = renta.getFecha();
                    int diaRen = LocalDate.of(Dates.getYear(fechRen), Dates.getMonth(fechRen), Dates.getDay(fechRen)).getDayOfWeek().getValue();
                    this.agregarControladorRenta(controller, diaRen);
                }
            }
        });
    }
    public void cargarPaneles(){
        Collections.sort(this.panelesLunes);
        Collections.sort(this.panelesMartes);
        Collections.sort(this.panelesMiercoles);
        Collections.sort(this.panelesJueves);
        Collections.sort(this.panelesViernes);
        Collections.sort(this.panelesSabado);
        Collections.sort(this.panelesDomingo);
        this.panelesLunes.forEach((controller) -> {
            this.listaLunes.getChildren().add(0, controller.getPane());
        });
        this.panelesMartes.forEach((controller) -> {
            this.listaMartes.getChildren().add(0, controller.getPane());
        });
        this.panelesMiercoles.forEach((controller) -> {
            this.listaMiercoles.getChildren().add(0, controller.getPane());
        });
        this.panelesJueves.forEach((controller) -> {
            this.listaJueves.getChildren().add(0, controller.getPane());
        });
        this.panelesViernes.forEach((controller) -> {
            this.listaViernes.getChildren().add(0, controller.getPane());
        });
        this.panelesSabado.forEach((controller) -> {
            this.listaSabado.getChildren().add(0, controller.getPane());
        });
        this.panelesDomingo.forEach((controller) -> {
            this.listaDomingo.getChildren().add(0, controller.getPane());
        });
    }
    public void grupoAgregado(Grupo grupo){
        this.grupos.add(grupo);
        this.cargarTodo();
    }
    public void grupoEditado(Grupo grupo){
        for (int i = 0; i < this.grupos.size(); i ++){
            if (this.grupos.get(i).getId() == grupo.getId()){
                this.grupos.set(i, grupo);
                this.cargarTodo();
                break;
            }
        }
    }
    public void profesorEditado(Profesor profesor){
        List<List<Calendarizable>> lista = new ArrayList();
        lista.add(this.panelesDomingo);
        lista.add(this.panelesJueves);
        lista.add(this.panelesLunes);
        lista.add(this.panelesMartes);
        lista.add(this.panelesMiercoles);
        lista.add(this.panelesSabado);
        lista.add(this.panelesViernes);
        lista.forEach((listaCal) -> {
            listaCal.forEach((calendarizable) -> {
                if (calendarizable instanceof PanelGrupoDirectorController){
                    PanelGrupoDirectorController controller = (PanelGrupoDirectorController) calendarizable;
                    Grupo grupo = controller.getGrupo();
                    if (grupo.getProfesor().getIdProfesor() == profesor.getIdProfesor()){
                        grupo.setProfesor(profesor);
                        controller.recargar();
                    }
                }
            });
        });
    }
    public AnchorPane getPane(){
        return this.pane;
    }
    
    public void nuevaRenta_onClick(){
        new VentanaRegistrarRenta();
    }
    public void nuevoGrupo_onClick(){
        new VentanaCRUGrupo(this.lanzador);
    }
    public void botonConsultarRentas_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelConsultarRentas.fxml");
    }
}
