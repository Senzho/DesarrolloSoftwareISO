package LogicaNegocio.Pagos;

import Accesodatos.Pagos.PagoProfesorDAOSql;
import java.util.Date;
import java.util.List;

public class PagoProfesor {
    private int idPago;
    private boolean tipoPago;
    private String monto;
    private Date fecha;
    
    private PagoProfesorDAOSql pagoProfesorDAO;

    public PagoProfesor() {
        this.pagoProfesorDAO = new PagoProfesorDAOSql();
    }
    public PagoProfesor(int idPago, boolean tipoPago, String monto, Date fecha) {
        this.idPago = idPago;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.fecha = fecha;
        this.pagoProfesorDAO = new PagoProfesorDAOSql();
    }

    public int getIdPago() {
        return idPago;
    }
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    public boolean isTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(boolean tipoPago) {
        this.tipoPago = tipoPago;
    }
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean registrarPago(int idProfesor){
        return this.pagoProfesorDAO.registrarPago(this, idProfesor);
    }
    public List<PagoProfesor> obtenerPagos(){
        return this.pagoProfesorDAO.obtenerPagos(this.idPago);
    }
}
