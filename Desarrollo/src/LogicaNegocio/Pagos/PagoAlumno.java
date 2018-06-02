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
    private int idAlumno;
    private int idGrupo;
    
    public PagoAlumno() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
    }

    public PagoAlumno(Date fecha, int idPagoAlumno, String monto, int tipoPago, int idProfesor, int idAlumno, int idGrupo) {
        this.fecha = fecha;
        this.idProfesor = idProfesor;
        this.idPagoAlumno = idPagoAlumno;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.idAlumno = idAlumno;
        this.idGrupo = idGrupo;
        pagoAlumnoDao = new PagoAlumnoDAOSql();
    }

    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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
    public int getIdGrupo(){
        return this.idGrupo;
    }
    public void setIdGrupo(int idGrupo){
        this.idGrupo = idGrupo;
    }

    public List<PagoAlumno> obtenerPagos(int idAlumno, int idProfesor) {
       return pagoAlumnoDao.obtenerPagos(idAlumno, idProfesor);
    }
    public List<PagoAlumno> obtenerPagos(int idProfesor){
        return pagoAlumnoDao.obtenerPagos(idProfesor);
    }
    public boolean registrarPago(int idAlumno, int idPromocion) {
        return pagoAlumnoDao.registrarPago(this, idAlumno, idPromocion);
    }
    public PagoAlumno obtenerPrimeraInscripcion(int idAlumno, int idGrupo){
        return this.pagoAlumnoDao.obtenerPrimeraInscripcion(idAlumno, idGrupo);
    }
}
