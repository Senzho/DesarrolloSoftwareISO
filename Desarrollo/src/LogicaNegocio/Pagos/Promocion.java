/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

/**
 *
 * @author Desktop
 */
public class Promocion {
    private String descripcion;
    private int idPromocion;
    private int idProfesor;
    private String nombre;
    private double porcentaje;

    public Promocion() {
    }

    public Promocion(String descripcion, int idPromocion, int idProfesor, String nombre, double porcentaje) {
        this.descripcion = descripcion;
        this.idPromocion = idPromocion;
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
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

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
        
}