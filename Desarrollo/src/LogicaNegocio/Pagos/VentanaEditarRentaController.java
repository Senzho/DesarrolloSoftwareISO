package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.HorarioException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VentanaEditarRentaController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private Label nombre;
    @FXML
    private DatePicker dia;
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    @FXML
    private ComboBox salon;
    @FXML
    private TextField monto;
    
    private Renta renta;
    private Cliente cliente;
    
    private void caragarImagen(){
        Image imagenCliente = CopiarArchivo.obtenerFotoUsuario("cliente", this.cliente.getIdCliente());
        if (imagenCliente != null) {
            this.imagen.setImage(imagenCliente);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    private void cargarHoras(){
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
    }
    private void cargarSalones(){
        this.salon.getItems().add("X");
    }
    private String getDia(){
        int valorDia = this.dia.getValue().getDayOfWeek().getValue();
        String dia = "";
        String dias[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        for (int i = 0; i < dias.length; i ++){
            if (valorDia == i + 1){
                dia = dias[i];
                break;
            }
        }
        return dia;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarHoras();
        this.cargarSalones();
    }
    
    public void setRenta(Renta renta, Cliente cliente){
        this.renta = renta;
        this.cliente = cliente;
        this.caragarImagen();
        this.cargarRenta();
    }
    public void cargarRenta(){
        this.nombre.setText(this.cliente.getNombre());
        Date fecha = this.renta.getFecha();
        this.dia.setValue(LocalDate.of(Dates.getYear(fecha), Dates.getMonth(fecha), Dates.getDay(fecha)));
        this.monto.setText(this.renta.getMonto());
        this.horaInicio.setValue(this.renta.getDia().getHoraInicio());
        this.horaFin.setValue(this.renta.getDia().getHoraFin());
        this.salon.setValue(this.renta.getDia().getSalon());
    }
    
    public void guardar_onClick(){
        String monto = this.monto.getText().trim();
        Date fecha = Dates.toDate(this.dia.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        if (OperacionesString.montoValido(monto)){
            if (Dates.getDiference(new Date(), fecha) > 0){
                this.renta.setFecha(fecha);
                this.renta.setMonto(monto);
                Dia dia = this.renta.getDia();
                dia.setDia(this.getDia());
                dia.setHoraFin(this.horaFin.getValue().toString());
                dia.setHoraInicio(this.horaInicio.getValue().toString());
                dia.setTipo(false);
                dia.setSalon(this.salon.getValue().toString());
                try {
                    if (this.renta.editarRenta()){
                        MessageFactory.showMessage("Exito", "Renta", "La renta fue editada con éxito", Alert.AlertType.INFORMATION);
                    }else{
                        MessageFactory.showMessage("Error", "Renta", "La renta no fue editada", Alert.AlertType.ERROR);
                    }
                } catch (HorarioException ex) {
                    MessageFactory.showMessage("Advertencia", "Día", ex.getMessage(), Alert.AlertType.WARNING);
                }
            }else{
                MessageFactory.showMessage("Error", "Fecha", "La fecha seleccionada no es válida", Alert.AlertType.ERROR);
            }
        }else{
            MessageFactory.showMessage("Error", "Monto", "El monto no es válido", Alert.AlertType.INFORMATION);
        }
    }
}
