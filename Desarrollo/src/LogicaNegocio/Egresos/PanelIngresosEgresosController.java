/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.PagoProfesor;
import LogicaNegocio.Pagos.PanelPagoAlumnoController;
import LogicaNegocio.Pagos.PanelPagoProfesorController;
import LogicaNegocio.Pagos.PanelPromocionController;
import LogicaNegocio.Pagos.Promocion;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelIngresosEgresosController implements Initializable {

    @FXML
    private VBox panelAlumnos;
    @FXML
    private VBox panelProfesores;
    @FXML
    private VBox panelPromociones;
    @FXML
    private VBox panelOtros;
    @FXML
    private Button btnExportar;
    @FXML
    private ComboBox comboPeriodo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void btnexportar_onClick() {

    }

    public void inicializarPaneles() {
        inicializarPanelProfesores();
        inicializarPanelAlumnos();
        this.inicializarPanelPromociones();
        this.inicializarPanelOtros();
    }

    public void inicializarPanelOtros() {
        List<Egreso> egresos = new Egreso().obtenerEgresos();
        this.panelOtros.getChildren().clear();
        for (int i = egresos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelEgreso.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelEgresoController controller = loader.getController();
                controller.setEgreso(egresos.get(i));
                this.panelOtros.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarPanelPromociones() {
        List<Promocion> promociones = new Promocion().obtenerPromociones();
        this.panelPromociones.getChildren().clear();
        for (int i = promociones.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPromocion.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPromocionController controller = loader.getController();
                controller.setPromocion(promociones.get(i));
                this.panelPromociones.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarPanelProfesores() {
        List<PagoProfesor> pagos = new PagoProfesor().obtenerPagos();
        this.panelProfesores.getChildren().clear();
        for (int i = pagos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoProfesor.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPagoProfesorController controller = loader.getController();
                controller.setPagoProfesor(pagos.get(i));
                controller.setProfesor(new Profesor().obtenerProfesor(pagos.get(i).getIdProfesor()));//.setAlumno(new Alumno().obtenerAlumno(pagos.get(i).getIdAlumno()));
                this.panelProfesores.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarPanelAlumnos() {
        List<PagoAlumno> pagos = new PagoAlumno().obtenerPagos();
        this.panelAlumnos.getChildren().clear();
        for (int i = pagos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoAlumno.fxml"));
            AnchorPane panel;

            try {
                panel = loader.load();

                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelPagoAlumnoController controller = loader.getController();
                controller.setPago(pagos.get(i));
                controller.setAlumno(new Alumno().obtenerAlumno(pagos.get(i).getIdAlumno()));
                this.panelAlumnos.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
