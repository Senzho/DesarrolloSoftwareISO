package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

public class PanelConsultarPromocionesEgresosController implements Initializable {
    @FXML
    private VBox panelEgresos;
    @FXML
    private VBox panelPromociones;
    @FXML
    private ComboBox comboMes;
    @FXML
    private ComboBox comboAño;
    private String[] meses;
    private List<GastoPromocional> listaGastos;
    private List<Egreso> listaEgresos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaGastos = new GastoPromocional().obtenerGastos();
        listaEgresos = new Egreso().obtenerEgresos();
        cargarCombos();
        establecerFechaActual();
    }
    public void establecerFechaActual(){
        Date fecha = new Date();
        String mes = Dates.getMonth(Dates.getMonth(fecha));
        String año = String.valueOf(Dates.getYear(fecha));
        this.comboAño.setValue(año);
        this.comboMes.setValue(mes);
        buscarEgresos(mes, año);
    }
    public void cargarCombos() {
        meses = Dates.monthsList();
        comboMes.getItems().clear();
        for (int i = 0; i < meses.length; i++) {
            this.comboMes.getItems().add(meses[i]);
        }
        int año = 2014;//solo para que funcione el metodo de busqueda
        this.comboAño.getItems().clear();
        for (int i = 0; i < 10; i++) {
            this.comboAño.getItems().add(año);
            año++;
        }
    }
    public void cargarEgresos(List<Egreso> listaEgresos) {
        this.panelEgresos.getChildren().clear();
        listaEgresos.forEach((egresoObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelEgreso.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelEgresoController controller = loader.getController();
                controller.setEgreso(egresoObtenido);
                this.panelEgresos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelEgresoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void btnBuscar_onClick() {
        boolean valido = buscarEgresos(this.comboMes.getSelectionModel().getSelectedItem().toString(), this.comboAño.getSelectionModel().getSelectedItem().toString());
        if(valido){
            MessageFactory.showMessage("Aviso", "Registros", "No existen registros de la fecha seleccionada", Alert.AlertType.INFORMATION);
        }
    }

    public boolean buscarEgresos(String mesCombo, String añoCombo) {
        boolean vacio = false;
        List<GastoPromocional> promociones = new ArrayList();
        List<Egreso> egresos = new ArrayList();
        for (GastoPromocional gasto : this.listaGastos) {
            if (Dates.getMonth(Dates.getMonth(gasto.getFechaInicio())).equals(mesCombo) && Dates.getYear(gasto.getFechaInicio()) == Integer.parseInt(añoCombo)) {
                promociones.add(gasto);
            }
        }
        for (Egreso egreso : listaEgresos) {
            if (Dates.getMonth(Dates.getMonth(egreso.getFecha())).equals(mesCombo) && Dates.getYear(egreso.getFecha()) == Integer.parseInt(añoCombo)) {
                egresos.add(egreso);
            }
        }
        if (!egresos.isEmpty()) {
            cargarEgresos(egresos);
        }
        if (!promociones.isEmpty()) {
            cargarPromociones(promociones);
        }
        if(egresos.isEmpty() && promociones.isEmpty()){
            vacio = true;
        }
        return vacio;
    }

    public void cargarPromociones(List<GastoPromocional> listaGastos) {
        this.panelPromociones.getChildren().clear();
        listaGastos.forEach((promocionObtenida) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelGastoPromocional.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelGastoPromocionalController controller = loader.getController();
                controller.setPromocion(promocionObtenida);
                this.panelPromociones.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelEgresoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
