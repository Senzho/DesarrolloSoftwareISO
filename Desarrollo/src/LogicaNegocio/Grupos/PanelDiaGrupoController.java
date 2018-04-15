package LogicaNegocio.Grupos;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PanelDiaGrupoController implements Initializable {
    @FXML
    private ComboBox nombreDia;
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    @FXML
    private ComboBox salon;
    @FXML
    private ImageView eliminar;
    
    private Dia dia;
    private VentanaCRUGrupoController cruController;
    private AnchorPane pane;
    private String horaInicioSeleccionado;
    private String horaFinSeleccionado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarDias();
        this.cargarHoras();
        this.cargarSalones();
        this.dia = new Dia();
        this.dia.setId(0);
        this.dia.setHoraFin(String.valueOf(this.horaFin.getValue()));
        this.dia.setHoraInicio(String.valueOf(this.horaInicio.getValue()));
        this.dia.setDia(String.valueOf(this.nombreDia.getValue()));
        this.dia.setSalon(String.valueOf(this.salon.getValue()));
        this.eliminar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    }
    
    public void setDia(Dia dia){
        this.dia = dia;
        this.cargarDia();
    }
    public void setVentanaCRUGrupoController(VentanaCRUGrupoController controller){
        this.cruController = controller;
    }
    public void setPane(AnchorPane pane){
        this.pane = pane;
    }
    public void cargarDia(){
        this.nombreDia.setValue(this.dia.getDia());
        this.salon.setValue(this.dia.getSalon());
        this.horaInicio.setValue(this.dia.getHoraInicio());
        this.horaFin.setValue(this.dia.getHoraFin());
    }
    public void cargarDias(){
        String dias[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        ObservableList items = this.nombreDia.getItems();
        for(String nombre : dias){
            items.add(nombre);
        }
        this.nombreDia.setValue("Lunes");
    }
    public void cargarHoras(){
        ObservableList horasInicio = this.horaInicio.getItems();
        ObservableList horasFin = this.horaFin.getItems();
        String hora, horaMedia;
        for (int i = 9; i < 22; i ++){
            if (i < 10){
                hora = "0" + i + ":";
            }else{
                hora = i + ":";
            }
            horaMedia = hora + "30";
            hora = hora + "00";
            horasInicio.add(hora);
            horasInicio.add(horaMedia);
            horasFin.add(hora);
            horasFin.add(horaMedia);
        }
        this.horaInicio.setValue("09:00");
        this.horaInicioSeleccionado = "09:00";
        this.horaFin.setValue("09:30");
        this.horaFinSeleccionado = "09:30";
    }
    public void cargarSalones(){
        this.salon.getItems().add("X");
        this.salon.setValue("X");
    }
    public Dia getDia(){
        return this.dia;
    }
    
    public void nombreDia_onAction(){
        this.dia.setDia(String.valueOf(this.nombreDia.getValue()));
    }
    public void salon_onAction(){
        this.dia.setSalon(String.valueOf(this.salon.getValue()));
    }
    public void horaInicio_onAction(){
        String nueva = String.valueOf(this.horaInicio.getValue());
        if (Horas.getSegundos(nueva) >= Horas.getSegundos(this.horaFinSeleccionado)){
            this.horaInicio.setValue(this.horaInicioSeleccionado);
        }else{
            this.dia.setHoraInicio(nueva);
            this.horaInicioSeleccionado = nueva;
        }
    }
    public void horaFin_onAction(){
        String nueva = String.valueOf(this.horaFin.getValue());
        if (Horas.getSegundos(nueva) <= Horas.getSegundos(this.horaInicioSeleccionado)){
            this.horaFin.setValue(this.horaFinSeleccionado);
        }else{
            this.dia.setHoraFin(nueva);
            this.horaFinSeleccionado = nueva;
        }
    }
    public void eliminar_onClick(){
        if (this.cruController != null){
            this.cruController.borrarDia(this.pane, this.dia);
        }
    }
}
