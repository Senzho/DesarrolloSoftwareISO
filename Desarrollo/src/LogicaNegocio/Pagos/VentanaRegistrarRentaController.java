package LogicaNegocio.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.HorarioException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaRegistrarRentaController implements Initializable {
    @FXML
    private ComboBox clientes;
    @FXML
    private DatePicker fecha;
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    @FXML
    private ComboBox salon;
    @FXML
    private TextField monto;
    @FXML
    private Button rentar;
    
    private List<Cliente> listaClientes;
    private Stage stage;
    private Renta renta;
    
    private Cliente obtenerCliente(){
        Cliente cliente = null;
        for (Cliente clienteLista : this.listaClientes){
            if (clienteLista.getNombre().equals(this.clientes.getValue().toString())){
                cliente = clienteLista;
            }
        }
        return cliente;
    }
    private String getDia(){
        int valorDia = this.fecha.getValue().getDayOfWeek().getValue();
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
        this.fecha.setValue(LocalDate.now());
    }
    
    public void inicializar(Stage stage){
        if (this.cargarClientes()){
            this.stage = stage;
            this.cargarSalones();
            this.cargarHoras();
        }else{
            MessageFactory.showMessage("Error", "Clientes", "No existen clientes registrados", Alert.AlertType.INFORMATION);
            stage.close();
        }
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
    public void cargarSalones(){
        this.salon.getItems().add("X");
        this.salon.setValue("X");
    }
    public boolean cargarClientes(){
        boolean hay;
        this.listaClientes = new ClienteDAOSql().obtenerClientes();
        if (this.listaClientes.isEmpty()){
            hay = false;
        }else{
            this.listaClientes.forEach((cliente) -> {
                this.clientes.getItems().add(cliente.getNombre());
            });
            this.clientes.setValue(this.listaClientes.get(0).getNombre());
            hay = true;
        }
        return hay;
    }
    public void rentar(){
        String monto = this.monto.getText().trim();
        Date fecha = Dates.toDate(this.fecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        if (OperacionesString.montoValido(monto)){
            if (Dates.getDiference(new Date(), fecha) > 0){
                this.renta = new Renta();
                this.renta.setIdRenta(0);
                this.renta.setCliente(this.obtenerCliente());
                this.renta.setFecha(fecha);
                this.renta.setMonto(monto);
                Dia dia = new Dia();
                dia.setDia(this.getDia());
                dia.setHoraFin(this.horaFin.getValue().toString());
                dia.setHoraInicio(this.horaInicio.getValue().toString());
                dia.setId(0);
                dia.setTipo(false);
                dia.setSalon(this.salon.getValue().toString());
                this.renta.setDia(dia);
                try {
                    if (this.renta.rentar()){
                        MessageFactory.showMessage("Exito", "Renta", "La renta fue registrada con éxito", Alert.AlertType.INFORMATION);
                    }else{
                        MessageFactory.showMessage("Error", "Renta", "La renta no fue registrada", Alert.AlertType.ERROR);
                    }
                    this.stage.close();
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
    
    public void rentar_onClick(){
        this.rentar();
    }
}
