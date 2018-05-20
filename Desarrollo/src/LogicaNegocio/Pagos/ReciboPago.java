/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.Dates;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desktop
 */
public class ReciboPago {

    private Font fuenteBold = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);
    private PagoAlumno pagoAlumno;
    private Alumno alumno;
    private Profesor profesor;
    private Cliente cliente;
    private Renta renta;

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setPagoAlumno(PagoAlumno pagoAlumno) {
        this.pagoAlumno = pagoAlumno;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setRenta(Renta renta) {
        this.renta = renta;
    }

    public boolean generarReciboPagoCliente() {
        boolean guardado = false;
        String rutaGuardar = "C:\\recibos";
        File recibos = new File(rutaGuardar);
        if (!recibos.exists()) {
            recibos.mkdir();
        }
        try {
            Document doc = new Document(PageSize.A7, 36, 36, 10, 10);
            FileOutputStream output = new FileOutputStream(rutaGuardar + "\\" + cliente.getNombre() + "_reciboPDF.pdf");
            PdfWriter.getInstance(doc, output);
            doc.open();
            doc.add(this.getCabecera("Centro de control ared espacio"));
            /**/
            doc.add(this.getInformacion("Nombre: " + this.cliente.getNombre()));
            doc.add(this.getInformacion("Correo: " + this.cliente.getCorreo()));
            doc.add(this.getPiePagina("Monto." + renta.getMonto() + "$"));
            doc.add(this.getPiePagina("Fecha: " + Dates.getSentence(renta.getFecha())));
            doc.close();
            guardado = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardado;
    }

    /**
     *
     * @param cabecera
     * @return
     */
    public boolean generarReciboPagoAlumno() {
        boolean guardado = false;
        String rutaGuardar = "C:\\recibos\\alumnos";
        File recibos = new File(rutaGuardar);
        File recibo = null;
        recibo = new File("C:\\recibos");
        if (!recibo.exists()) {
            recibo.mkdir();
        }
        if(!recibos.exists()){
            recibos.mkdir();
        }
        try {
            Document doc = new Document(PageSize.A7, 36, 36, 10, 10);
            FileOutputStream output = new FileOutputStream(rutaGuardar + "\\" + this.alumno.getNombre() + "_reciboPDF.pdf");
            PdfWriter.getInstance(doc, output);
            doc.open();
            doc.add(this.getCabecera("Ared espacio"));
            /**/
            doc.add(this.getInformacion("Fecha: " + Dates.getSentence(pagoAlumno.getFecha())));
            doc.add(this.getInformacion("Nombre: " + this.alumno.getNombre()));
            doc.add(this.getInformacion("Monto: " + pagoAlumno.getMonto() + "$"));
            String tipoPago = "";
            if (pagoAlumno.getTipoPago() == 0) {
                tipoPago = "inscripción";
            } else {
                tipoPago = "Mensualidad";
            }
            doc.add(this.getInformacion("Tipo pago: " + tipoPago));
            doc.close();
            guardado = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardado;
    }

    /**
     *
     * @param cabecera
     * @return
     */

    private Paragraph getCabecera(String cabecera) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(cabecera);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }

    private Paragraph getInformacion(String informacion) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(informacion);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }

    private Paragraph getPiePagina(String piePagina) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_LEFT);
        c.append(piePagina);
        c.setFont(fuenteItalic);
        p.add(c);
        return p;
    }
}
/**
 * MessageFactory.showMessage("Archivo creado", "Se ha guardado el archivo", "El
 * archivo ha sido creado con exito, revise la carpeta:"+rutaGuardar+" para mas
 * inforación", Alert.AlertType.INFORMATION);
 */
/**
 *             //String ruta = "RecursosGraficos\\AredImagen.png";
            //Image imagen = Image.getInstance(ruta);
            //imagen.scaleAbsolute(100, 100);
            //imagen.setAlignment(Element.ALIGN_CENTER);
            //doc.add(imagen);
 */
