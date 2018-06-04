/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.PagoProfesor;
import LogicaNegocio.Pagos.ReciboPago;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private Label lblIngresosTotales;
    @FXML
    private Label lblEgresosTotales;
    @FXML
    private Label lblGananciasNetas;
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
    private ComboBox comboMes;
    @FXML
    private ComboBox comboAño;
    private Profesor profesor;
    private double gananciaTotal = 0;
    private double egresoTotal = 0;
    private List<Egreso> egresos;
    private List<Egreso> listaEgresos = new ArrayList();
    private List<GastoPromocional> promociones;
    private List<GastoPromocional> listaPromociones = new ArrayList();
    private List<PagoProfesor> pagosProfesores;
    private List<PagoProfesor> listaPagosProfesores = new ArrayList();
    private List<PagoAlumno> pagosAlumnos;
    private List<PagoAlumno> listaPagosAlumnos = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarCombos() {
        Date fecha = new Date();
        int año = Dates.getYear(fecha);
        int inicio = año - 15;
        for (int i = inicio; i <= año; i++) {
            this.comboAño.getItems().add(i);
            comboAño.setValue(año);
        }
        String[] meses = Dates.monthsList();
        for (String mes : meses) {
            this.comboMes.getItems().add(mes);
            comboMes.setValue(mes);
        }
    }

    /*----------------------*/
    public void btnExportar_onClick() {
        String nombreSistema = System.getProperty("os.name");
        String separador = "";
        String rutaPrincipal = "";
        if(nombreSistema.contains("Linux")){
            rutaPrincipal = System.getProperty("user.home");
            separador = "/";
        }else{
            rutaPrincipal = "C:";
            separador = "\\";
        }
        String rutaGuardar =  rutaPrincipal+separador+"Ared"+separador+"Reportes";
        File recibos = new File(rutaGuardar);
        if (!recibos.exists()) {
            recibos.mkdirs();
        }
        try {
            Document doc = new Document(PageSize.A7, 36, 36, 10, 10);
            FileOutputStream output = new FileOutputStream(rutaGuardar +separador+"registro_ingresos_Egresos_"
                    + this.comboMes.getSelectionModel().getSelectedItem().toString() + "_" + this.comboAño.getSelectionModel().getSelectedItem().toString() + ".pdf");
            PdfWriter.getInstance(doc, output);
            ReciboPago registroPago = new ReciboPago();
            doc.open();
            doc.add(registroPago.getCabecera("Ared espacio"));
            doc.add(registroPago.getInformacion("Registro de pagos del periodo " + this.comboMes.getSelectionModel().getSelectedItem().toString() + " del "
                    + this.comboAño.getSelectionModel().getSelectedItem().toString()));
            if (!listaPagosProfesores.isEmpty()) {
                doc.add(registroPago.getPiePagina("  "));
                doc.add(registroPago.getCabecera("Pagos de profesores"));
                for (int i = 0; i < this.listaPagosProfesores.size(); i++) {
                    doc.add(registroPago.getInformacion("Profesor: " + new Profesor().obtenerProfesor(pagosProfesores.get(i).getIdProfesor()).getNombre()));
                    doc.add(registroPago.getInformacion("Monto: $ " + listaPagosProfesores.get(i).getMonto()));
                    doc.add(registroPago.getInformacion("Fecha: " + Dates.getSentence(listaPagosProfesores.get(i).getFecha())));
                }
                doc.add(registroPago.getPiePagina(this.lblTotalProfesores.getText()));
            }
            if (!listaPagosAlumnos.isEmpty()) {
                doc.add(registroPago.getPiePagina("  "));
                doc.add(registroPago.getCabecera("Pagos de alumnos"));
                for (int i = 0; i < this.listaPagosAlumnos.size(); i++) {
                    doc.add(registroPago.getInformacion("Alumno: " + new Alumno().obtenerAlumno(listaPagosAlumnos.get(i).getIdAlumno()).getNombre()));
                    doc.add(registroPago.getInformacion("Monto: $ " + listaPagosAlumnos.get(i).getMonto()));
                    doc.add(registroPago.getInformacion("Fecha: " + Dates.getSentence(listaPagosAlumnos.get(i).getFecha())));
                }
                doc.add(registroPago.getPiePagina(this.lblTotalAlumnos.getText()));
            }
            if (!listaPromociones.isEmpty()) {
                doc.add(registroPago.getPiePagina("  "));
                doc.add(registroPago.getCabecera("Gastos promocionales"));
                for (int i = 0; i < this.listaPromociones.size(); i++) {
                    doc.add(registroPago.getInformacion("Descripción: " + listaPromociones.get(i).getDescripcion()));
                    doc.add(registroPago.getInformacion("Monto: $ " + listaPromociones.get(i).getMonto()));
                    doc.add(registroPago.getInformacion("Fecha: " + Dates.getSentence(listaPromociones.get(i).getFechaInicio())));
                }
                doc.add(registroPago.getPiePagina(this.lblTotalPromociones.getText()));
            }
            if (!listaEgresos.isEmpty()) {
                doc.add(registroPago.getPiePagina("  "));
                doc.add(registroPago.getCabecera("Egresos"));
                for (int i = 0; i < this.listaEgresos.size(); i++) {
                    doc.add(registroPago.getInformacion("Descripción: " + listaEgresos.get(i).getDescripcion()));
                    doc.add(registroPago.getInformacion("Monto: $ " + listaEgresos.get(i).getMonto()));
                    doc.add(registroPago.getInformacion("Fecha: " + Dates.getSentence(listaEgresos.get(i).getFecha())));
                }
            }
            doc.add(registroPago.getPiePagina(this.lblTotalPromociones.getText()));
            doc.add(registroPago.getPiePagina(" "));
            doc.add(registroPago.getPiePagina(this.lblIngresosTotales.getText()));
            doc.add(registroPago.getPiePagina(this.lblEgresosTotales.getText()));
            doc.add(registroPago.getPiePagina(this.lblGananciasNetas.getText()));
            doc.close();
            MessageFactory.showMessage("Recibo creado", "El recibo se ha creado exitosamente", "Mas información en"+rutaGuardar, Alert.AlertType.CONFIRMATION);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*----------------------*/
    public void btnBuscar_onClick() {
        this.gananciaTotal = 0;
        this.egresoTotal = 0;
        this.lblEgresosTotales.setText("");
        this.lblGananciasNetas.setText("");
        this.lblIngresosTotales.setText("");
        this.lblTotalAlumnos.setText("");
        this.lblTotalOtros.setText("");
        this.lblTotalProfesores.setText("");
        this.lblTotalPromociones.setText("");
        this.inicializarPaneles(this.comboMes.getSelectionModel().getSelectedItem().toString(),
                this.comboAño.getSelectionModel().getSelectedItem().toString());

    }

    public void inicializarPaneles(String mes, String año) {
        inicializarPanelProfesores(mes, año);
        inicializarPanelAlumnos(mes, año);
        this.inicializarPanelPromociones(mes, año);
        this.inicializarPanelOtros(mes, año);
        this.lblIngresosTotales.setText("Ingresos totales: $ " + this.gananciaTotal);
        this.lblEgresosTotales.setText("Egresos totales: $ " + this.egresoTotal);
        this.lblGananciasNetas.setText("Ganancias netas: $" + (gananciaTotal - egresoTotal));

    }

    public void inicializarPanelOtros(String mes, String año) {
        double total = 0;
        listaEgresos.clear();
        this.panelOtros.getChildren().clear();
        for (int i = egresos.size() - 1; i > - 1; i--) {
            if (Dates.getMonth(Dates.getMonth(egresos.get(i).getFecha())).trim().equalsIgnoreCase(mes)
                    && Dates.getYear(Dates.getYear(egresos.get(i).getFecha())).trim().equalsIgnoreCase(año)) {
                listaEgresos.add(egresos.get(i));
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panel.setStyle("-fx-background-color: #DAD9D5;");
                    PanelRegistroController controller = loader.getController();
                    total = total + Double.valueOf(egresos.get(i).getMonto());
                    controller.generarPanelEgresos(egresos.get(i));
                    this.panelOtros.getChildren().add(panel);
                    this.lblTotalOtros.setText("Total: $ " + total);
                } catch (IOException ex) {
                    Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.egresoTotal += total;
    }

    public void inicializarPanelPromociones(String mes, String año) {
        double total = 0;
        listaPromociones.clear();
        this.panelPromociones.getChildren().clear();
        for (int i = promociones.size() - 1; i > - 1; i--) {
            if (Dates.getMonth(Dates.getMonth(promociones.get(i).getFechaInicio())).trim().equalsIgnoreCase(mes)
                    && Dates.getYear(Dates.getYear(promociones.get(i).getFechaInicio())).trim().equalsIgnoreCase(año)) {
                listaPromociones.add(promociones.get(i));
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panel.setStyle("-fx-background-color: #DAD9D5;");
                    PanelRegistroController controller = loader.getController();
                    total = total + Double.valueOf(promociones.get(i).getMonto());
                    controller.generarPanelPromocion(promociones.get(i));
                    this.panelPromociones.getChildren().add(panel);
                    this.lblTotalPromociones.setText("Total: $ " + total);
                } catch (IOException ex) {
                    Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.egresoTotal += total;
    }

    public void inicializarPanelProfesores(String mes, String año) {
        double total = 0;
        listaPagosProfesores.clear();
        this.panelProfesores.getChildren().clear();
        for (int i = pagosProfesores.size() - 1; i > - 1; i--) {
            if (Dates.getMonth(Dates.getMonth(pagosProfesores.get(i).getFecha())).trim().equalsIgnoreCase(mes)
                    && Dates.getYear(Dates.getYear(pagosProfesores.get(i).getFecha())).trim().equalsIgnoreCase(año)) {
                listaPagosProfesores.add(pagosProfesores.get(i));
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panel.setStyle("-fx-background-color: #DAD9D5;");
                    PanelRegistroController controller = loader.getController();
                    controller.generarPanelPagoProfesor(pagosProfesores.get(i), new Profesor().obtenerProfesor(pagosProfesores.get(i).getIdProfesor()));
                    total = total + Double.valueOf(pagosProfesores.get(i).getMonto());
                    this.panelProfesores.getChildren().add(panel);
                    this.lblTotalProfesores.setText("Total: $ " + total);
                } catch (IOException ex) {
                    Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.gananciaTotal += total;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        this.egresos = new Egreso().obtenerEgresos();
        this.promociones = new GastoPromocional().obtenerGastos();
        this.pagosAlumnos = new PagoAlumno().obtenerPagos(this.profesor.getIdProfesor());
        this.pagosProfesores = new PagoProfesor().obtenerPagos();
        inicializarCombos();
    }

    public void inicializarPanelAlumnos(String mes, String año) {
        double total = 0;
        listaPagosAlumnos.clear();
        this.panelAlumnos.getChildren().clear();
        for (int i = pagosAlumnos.size() - 1; i > - 1; i--) {
            if (Dates.getMonth(Dates.getMonth(pagosAlumnos.get(i).getFecha())).trim().equalsIgnoreCase(mes)
                    && Dates.getYear(Dates.getYear(pagosAlumnos.get(i).getFecha())).trim().equalsIgnoreCase(año)) {
                listaPagosAlumnos.add(pagosAlumnos.get(i));
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Egresos/PanelRegistro.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panel.setStyle("-fx-background-color: #DAD9D5;");
                    PanelRegistroController controller = loader.getController();
                    controller.generarPanelPagoAlumno(pagosAlumnos.get(i), new Alumno().obtenerAlumno(pagosAlumnos.get(i).getIdAlumno()));
                    total = total + Double.valueOf(pagosAlumnos.get(i).getMonto());
                    this.panelAlumnos.getChildren().add(panel);
                    this.lblTotalAlumnos.setText("Total: $ " + total);
                } catch (IOException ex) {
                    Logger.getLogger(PanelIngresosEgresosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.gananciaTotal += total;
    }
}
