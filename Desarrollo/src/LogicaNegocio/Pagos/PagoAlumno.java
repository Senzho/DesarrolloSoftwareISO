/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import Accesodatos.Entidades.Promocion;
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
    private String tipoPago;
    private PagoAlumnoDAOSql pagoAlumnoDao;
    
    public PagoAlumno() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
    }

    public PagoAlumno(Date fecha, int idPagoAlumno, String monto, String tipoPago) {
        this.fecha = fecha;
        this.idPagoAlumno = idPagoAlumno;
        this.monto = monto;
        this.tipoPago = tipoPago;
        pagoAlumnoDao = new PagoAlumnoDAOSql();
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

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<PagoAlumno> obtenerPagos(int idAlumno) {
       return pagoAlumnoDao.obtenerPagos(idAlumno);
    }
    
    public boolean registrarPago(int idAlumno, Promocion promocion) {
        return pagoAlumnoDao.registrarPago(this, idAlumno, promocion);
    }
}
