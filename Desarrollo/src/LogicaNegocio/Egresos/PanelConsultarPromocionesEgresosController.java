/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import Accesodatos.Entidades.Promocion;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.panelEgresos.setVgap(5);
        this.panelPromociones.setVgap(5);
        this.cargarEgresos(new Egreso().obtenerEgresos());
        this.cargarPromociones(new GastoPromocional().obtenerGastos());
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
