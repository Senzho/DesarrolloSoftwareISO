package LogicaNegocio.Grupos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarDias();
        this.cargarHoras();
        this.dia = new Dia();
        this.eliminar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    }
    
    public void setDia(Dia dia){
        this.dia = dia;
        this.cargarDia();
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
        this.horaFin.setValue("09:30");
    }
    public Dia getDia(){
        return this.dia;
    }
    
    public void nombreDia_onClick(){
        this.dia.setDia(String.valueOf(this.nombreDia.getValue()));
    }
    public void salon_onClick(){
        this.dia.setSalon(String.valueOf(this.salon.getValue()));
    }
    public void horaInicio_onClick(){
        this.dia.setHoraInicio(String.valueOf(this.horaInicio.getValue()));
    }
    public void horaFin_onClick(){
        this.dia.setHoraFin(String.valueOf(this.horaFin.getValue()));
    }
}
