/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelProximosPagosProfesorController implements Initializable {

    private List<Profesor> profesores;
    @FXML
    private VBox panelPagos;
    
    public void buscarProfesores() {
        profesores = new ArrayList<>();
        profesores = new Profesor().obtenerProfesores();
        profesores.forEach((profesor) -> {
            if (!profesor.isEstado()) {
                profesores.remove(profesor);
            }
        });
    }

    public void generarProximoPago() {
        Date fecha = new Date();
        for (Profesor profesor : profesores) {
            fecha = profesor.getFecha();
            int dif = Dates.getDiference(new Date(), fecha);
            if (dif < 7 && dif > -1) {
                try {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelProximoPagoProfesor.fxml"));
                    AnchorPane pane = loader.load();
                    PanelProximoPagoProfesorController controller = loader.getController();
                    controller.setDatosProfesor(profesor.getNombre(), profesor.getMonto(), profesor.isTipoPago(), profesor.getFechaInicio());
                    this.panelPagos.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelProximosPagosProfesorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
