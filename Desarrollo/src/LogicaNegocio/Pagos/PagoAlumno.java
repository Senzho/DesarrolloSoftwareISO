/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Desktop
 */
public class PagoAlumno {

    private Date fecha;
    private int idAlumno;
    private int idPagoAlumno;
    private String monto;
    private String tipoPago;

    public PagoAlumno() {
    }

    public PagoAlumno(Date fecha, int idAlumno, int idPagoAlumno, String monto, String tipoPago) {
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.idPagoAlumno = idPagoAlumno;
        this.monto = monto;
        this.tipoPago = tipoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdPagoAlumno() {
        return idPagoAlumno;
    }

    public void setIdPagoAlumno(int idPagoAlumno) {
        this.idPagoAlumno = idPagoAlumno;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
