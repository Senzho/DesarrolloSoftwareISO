/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import Accesodatos.Pagos.PromocionDAOSql;

/**
 *
 * @author Desktop
 */
public class Promocion {

    private String descripcion;
    private int idPromocion;
    private int idProfesor;
    private String nombre;
    private int porcentaje;
    private PromocionDAOSql promocionDao;

    public Promocion() {
        promocionDao = new PromocionDAOSql();
    }

    public Promocion(String descripcion, int idPromocion, int idProfesor, String nombre, int porcentaje) {
        this.descripcion = descripcion;
        this.idPromocion = idPromocion;
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        promocionDao = new PromocionDAOSql();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean editarPromocion() {
        return promocionDao.editarPromocion(this);
    }

    public boolean registrarPromocion() {
        return promocionDao.registrarPromocion(this);
    }

}
