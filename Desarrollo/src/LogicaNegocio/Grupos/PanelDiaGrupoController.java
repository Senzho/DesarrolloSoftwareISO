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
    private ComboBox dia;
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    @FXML
    private ComboBox salon;
    @FXML
    private ImageView eliminar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarDias();
        this.cargarHoras();
        this.eliminar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    }
    
    public void cargarDias(){
        String dias[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        ObservableList items = this.dia.getItems();
        for(String nombre : dias){
            items.add(nombre);
        }
        this.dia.setValue("Lunes");
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
}
