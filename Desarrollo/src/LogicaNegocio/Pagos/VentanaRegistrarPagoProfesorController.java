package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Profesor;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class VentanaRegistrarPagoProfesorController implements Initializable {
    @FXML
    private ComboBox pofesores;
    @FXML
    private Button registrar;
    
    private Stage stage;
    private List<Profesor> listaProfesores;
    private PagoProfesor pagoProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> nombresProfesores = FXCollections.observableArrayList();
        this.listaProfesores = new Profesor().obtenerProfesores();
        this.listaProfesores.forEach((profesor) -> {
            nombresProfesores.add(profesor.getNombre());
        });
        this.pofesores.setItems(nombresProfesores);
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Profesor getProfesorSeleccionado(){
        Profesor profesor = null;
        String nombre = (String) this.pofesores.getValue();
        for (Profesor profesorLista : this.listaProfesores) {
            if (profesorLista.getNombre().equals(nombre)){
                profesor = profesorLista;
            }
        }
        return profesor;
    }
    
    public void registrar_OnClick(){
        Profesor profesor = this.getProfesorSeleccionado();
        this.pagoProfesor = new PagoProfesor();
        this.pagoProfesor.setIdPago(0);
        this.pagoProfesor.setFecha(new Date());
        this.pagoProfesor.setMonto(profesor.getMonto());
        this.pagoProfesor.setTipoPago(profesor.isTipoPago());
        if (this.pagoProfesor.registrarPago(profesor.getIdProfesor())){
            MessageFactory.showMessage("Registro", "Éxito", "El pago fue registrado con éxito", Alert.AlertType.INFORMATION);
            this.stage.close();
        }else{
            MessageFactory.showMessage("Registro", "Error", "El pago no se pudo registrar", Alert.AlertType.ERROR);
        }
    }
}
