/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelConsultarPromocionesEgresosController implements Initializable {
    @FXML
    private FlowPane panelEgresos;
    @FXML
    private FlowPane panelPromociones;
    @FXML
    private ComboBox comboMes;
     @FXML
    private ComboBox comboAño;
    private String[] meses;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.panelEgresos.setVgap(5);
        this.panelPromociones.setVgap(5);
        this.cargarEgresos(new Egreso().obtenerEgresos());
        this.cargarPromociones(new GastoPromocional().obtenerGastos());
        cargarCombos();
    }
    public void cargarCombos(){
        meses = Dates.monthsList();
        comboMes.getItems().clear();
        for(int i =0; i < meses.length; i++){
            this.comboMes.getItems().add(meses[i]);
        }
        int año = 2014;
        comboMes.getItems().clear();
        for (int i = 0; i < 10; i++) {
            this.comboAño.getItems().add(año);
            año++;
        }
    }
    
    public void cargarEgresos(List<Egreso> listaEgresos){
        this.panelEgresos.getChildren().clear();
        listaEgresos.forEach((egresoObtenido) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelPromocion.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPromocionController controller = loader.getController();
                controller.setEgreso(egresoObtenido);
                this.panelEgresos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelPromocionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void comboMes_onClick(){
        cargarPromocionesFecha(this.comboMes.getSelectionModel().getSelectedItem().toString(),this.comboAño.getSelectionModel().getSelectedItem().toString());
    }
    public void cargarPromocionesFecha(String mesCombo, String añoCombo){
        int numeroMes = Dates.getMonth(mesCombo);
        List<GastoPromocional> listaPromociones = new GastoPromocional().obtenerGastos();
        List<GastoPromocional> listaFecha = new ArrayList();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString;
        String mes;
        String año;
        Date fecha;
        String[] caracteres;
        String limite = "-";
        for(int i = 0; i < listaPromociones.size(); i++){
            GastoPromocional gasto = listaPromociones.get(i);
            fecha = gasto.getFechaInicio();
            fechaString = formatoFecha.format(fecha);
            caracteres = fechaString.split(limite);
            if(caracteres[0].equals(añoCombo) && caracteres[1].equals(numeroMes)){
                listaFecha.add(gasto);
            }
        }
        cargarPromociones(listaFecha);
    }
    public void cargarPromociones(List<GastoPromocional> listaGastos){
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
                Logger.getLogger(PanelPromocionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
