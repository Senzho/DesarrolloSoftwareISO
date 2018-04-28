package LogicaNegocio.Egresos;

import Accesodatos.Egresos.GastoPromocionalDAOSql;
import java.util.Date;
import java.util.List;

public class GastoPromocional {
    private String descripcion;
    private Date fechaFin;
    private Date fechaInicio;
    private int idGastoPromocional;
    private String monto;
    private String URL;
    private int idProfesor;
    private GastoPromocionalDAOSql gastoPromocionalDao;

    public GastoPromocional(){
        gastoPromocionalDao = new GastoPromocionalDAOSql();
    }

    public GastoPromocional(int idGastoPromocional,String descripcion, Date fechaFin, Date fechaInicio, String monto, String URL, int idProfesor) {
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idGastoPromocional = idGastoPromocional;
        this.monto = monto;
        this.URL = URL;
        this.idProfesor = idProfesor;
        this.gastoPromocionalDao = new GastoPromocionalDAOSql();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public int getIdGastoPromocional() {
        return idGastoPromocional;
    }
    public void setIdGastoPromocional(int idGastoPromocional) {
        this.idGastoPromocional = idGastoPromocional;
    }
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public int getIdProfesor(){
        return this.idProfesor;
    }
    public void setIdProfesor(int idProfesor){
        this.idProfesor = idProfesor;
    }
    
    public boolean editarGasto() {
        return gastoPromocionalDao.editarGasto(this);
    }
    public List<GastoPromocional> obtenerGastos() {
        return gastoPromocionalDao.obtenerGastos();
    }
    public boolean registrarGasto() {
        return gastoPromocionalDao.registrarGasto(this);
    }
}
