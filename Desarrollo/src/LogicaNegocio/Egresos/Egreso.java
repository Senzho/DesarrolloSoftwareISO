/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Egresos;

import Accesodatos.Egresos.EgresoDAOSql;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desktop
 */
public class Egreso {
    private int idEgreso;
    private String monto;
    private String descripcion;
    private Date fecha;
    private EgresoDAOSql egresoDao;

    public Egreso(){
        this.egresoDao = new EgresoDAOSql();
    }
    public Egreso(int idEgreso, String monto, String descripcion, Date fecha) {
        this.idEgreso = idEgreso;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.egresoDao = new EgresoDAOSql();
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean registrarEgreso() {
        return egresoDao.registrarEgreso(this);
    }

    public boolean editarEgreso() {
        return egresoDao.editarEgreso(this);
    }

    public List<Egreso> obtenerEgresos() {
        return this.egresoDao.obtenerEgresos();
    }
    
}
