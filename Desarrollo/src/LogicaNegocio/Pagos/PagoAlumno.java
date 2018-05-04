/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import Accesodatos.Pagos.PagoAlumnoDAOSql;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desktop
 */
public class PagoAlumno {

    private Date fecha;
    private int idPagoAlumno;
    private String monto;
    private int tipoPago;
    private int idPromocion;
    private PagoAlumnoDAOSql pagoAlumnoDao;
    private int idProfesor; 
    
    public PagoAlumno() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
    }

    public PagoAlumno(Date fecha, int idPagoAlumno, String monto, int tipoPago, int idProfesor) {
        this.fecha = fecha;
        this.idProfesor = idProfesor;
        this.idPagoAlumno = idPagoAlumno;
        this.monto = monto;
        this.tipoPago = tipoPago;
        pagoAlumnoDao = new PagoAlumnoDAOSql();
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public List<PagoAlumno> obtenerPagos(int idAlumno, int idProfesor) {
       return pagoAlumnoDao.obtenerPagos(idAlumno, idProfesor);
    }
    
    public boolean registrarPago(int idAlumno, int idPromocion) {
        return pagoAlumnoDao.registrarPago(this, idAlumno, idPromocion);
    }
}
