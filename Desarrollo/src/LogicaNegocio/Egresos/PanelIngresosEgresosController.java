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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelIngresosEgresosController implements Initializable {
    @FXML
    private Label lblTotalProfesores;
    
    @FXML
    private Label lblTotalAlumnos;
    
    @FXML
    private Label lblTotalPromociones;
    
    @FXML
    private Label lblTotalOtros;
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
    private Profesor profesor;
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
        double total = 0;
        List<Egreso> egresos = new Egreso().obtenerEgresos();
        this.panelOtros.getChildren().clear();
        for (int i = egresos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelRegistroController controller = loader.getController();
                total = total + Double.valueOf(egresos.get(i).getMonto());
                controller.generarPanelEgresos(egresos.get(i));
                this.panelOtros.getChildren().add(panel);
                this.lblTotalOtros.setText("Total: $ "+total);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarPanelPromociones() {
        double total = 0;
        List<Promocion> promociones = new Promocion().obtenerPromociones();
        this.panelPromociones.getChildren().clear();
        for (int i = promociones.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelRegistroController controller = loader.getController();
                total = total + Double.valueOf(promociones.get(i).getPorcentaje());
                controller.generarPanelPromocion(promociones.get(i));
                this.panelPromociones.getChildren().add(panel);
                this.lblTotalPromociones.setText("Total: $ "+total);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarPanelProfesores() {
        double total = 0;
        List<PagoProfesor> pagos = new PagoProfesor().obtenerPagos();
        this.panelProfesores.getChildren().clear();
        for (int i = pagos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelRegistroController controller = loader.getController();
                controller.generarPanelPagoProfesor(pagos.get(i), new Profesor().obtenerProfesor(pagos.get(i).getIdProfesor()));
                total = total + Double.valueOf(pagos.get(i).getMonto());
                this.panelProfesores.getChildren().add(panel);
                this.lblTotalProfesores.setText("Total: $ "+total);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
    }

    public void inicializarPanelAlumnos() {
        double total = 0;
        List<PagoAlumno> pagos = new PagoAlumno().obtenerPagos(this.profesor.getIdProfesor());
        this.panelAlumnos.getChildren().clear();
        for (int i = pagos.size() - 1; i > - 1; i--) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                panel.setStyle("-fx-background-color: #DAD9D5;");
                PanelRegistroController controller = loader.getController();
                controller.generarPanelPagoAlumno(pagos.get(i), new Alumno().obtenerAlumno(pagos.get(i).getIdAlumno()));
                 total = total + Double.valueOf(pagos.get(i).getMonto());
                this.panelAlumnos.getChildren().add(panel);
                this.lblTotalAlumnos.setText("Total: $ "+total);
            } catch (IOException ex) {
                Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
