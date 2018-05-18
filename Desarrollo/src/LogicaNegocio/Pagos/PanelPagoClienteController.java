/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Egresos.Dates;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelPagoClienteController implements Initializable {
    private Renta renta;
    private Cliente cliente;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblMonto;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFin;
    Font fuenteBold = new Font(Font.FontFamily.COURIER,10,Font.BOLD);
    Font fuenteNormal = new Font(Font.FontFamily.COURIER,8,Font.NORMAL);
    Font fuenteItalic = new Font(Font.FontFamily.COURIER,8,Font.ITALIC);
    /**
     * Initializes the controller class.
     */
    
    public void setPago(Renta renta){
        this.renta = renta;
        this.lblMonto.setText("Monto: "+renta.getMonto());
        this.lblHoraInicio.setText("Inicio: "+this.renta.getDia().getHoraInicio());
        this.lblHoraFin.setText("Fin: "+this.renta.getDia().getHoraFin());
        this.lblFecha.setText("Fecha: "+Dates.getSentence(this.renta.getFecha()));
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //imagenCancelar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    public void generarReciboPago(){
       
        String rutaGuardar = "C:\\recibos";
        File recibos = new File(rutaGuardar);
        if(!recibos.exists()){
            recibos.mkdir();
        }
        try {
            Document doc = new Document(PageSize.A7,36,36,10,10);
            FileOutputStream output = new FileOutputStream(rutaGuardar+"\\"+cliente.getNombre()+"_reciboPDF.pdf");
            PdfWriter.getInstance(doc, output);
            doc.open();
            doc.add(this.getCabecera("Centro de control ared espacio"));
            //String ruta = "RecursosGraficos\\AredImagen.png";
            //Image imagen = Image.getInstance(ruta);
            //imagen.scaleAbsolute(100, 100);
            //imagen.setAlignment(Element.ALIGN_CENTER);
            //doc.add(imagen);
            doc.add(this.getInformacion("Nombre: "+this.cliente.getNombre()));
            doc.add(this.getInformacion("Correo: "+this.cliente.getCorreo()));
            doc.add(this.getPiePagina("Monto."+renta.getMonto()+"$"));
            doc.add(this.getPiePagina("Fecha: "+Dates.getSentence(renta.getFecha())));
            doc.close();
            MessageFactory.showMessage("Archivo creado", "Se ha guardado el archivo", 
                    "El archivo ha sido creado con exito, revise la carpeta:"+rutaGuardar+" para mas inforación", Alert.AlertType.INFORMATION);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PanelPagoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PanelPagoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanelPagoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private Paragraph getCabecera(String cabecera){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(cabecera);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }
    
    private Paragraph getInformacion(String informacion){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(informacion);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    
    private Paragraph getPiePagina(String piePagina){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_LEFT);
        c.append(piePagina);
        c.setFont(fuenteItalic);
        p.add(c);
        return p;
    }
}
