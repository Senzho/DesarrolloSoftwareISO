package LogicaNegocio.Pagos;

import Accesodatos.Pagos.PagoTemporalDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import java.util.Date;
import java.util.List;

public class PagoTemporal {
    private int idPago;
    private int tipoPago;
    private Date fecha;
    private String monto;
    private Profesor profesor;
    private Alumno alumno;
    private PagoTemporalDAOSql pagoTemporalDAO;
    
    public PagoTemporal(){
        this.pagoTemporalDAO = new PagoTemporalDAOSql();
    }

    public int getIdPago() {
        return idPago;
    }
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    public int getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    public Alumno getAlumno() {
        return alumno;
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public boolean registrarPago(){
        return this.pagoTemporalDAO.registrarPago(this);
    }
    public List<PagoTemporal> obtenerPagos(){
        return this.pagoTemporalDAO.obtenerPagos();
    }
    public boolean eliminarPago(){
        return this.pagoTemporalDAO.eliminarPago(this.idPago);
    }
}
