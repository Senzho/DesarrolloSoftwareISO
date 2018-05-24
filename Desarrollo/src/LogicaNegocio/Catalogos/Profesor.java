package LogicaNegocio.Catalogos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import java.util.Date;
import java.util.List;

public class Profesor implements Comparable<Profesor>{
    private int idProfesor;
    private boolean estado;
    private boolean tipoPago;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String monto;
    private Date fecha;
    private Date fechaInicio;
    
    private ProfesorDAOSql profesorDAO;

    public Profesor(){
        this.profesorDAO = new ProfesorDAOSql();
    }
    public Profesor(int idProfesor, String nombre, String correo, String telefono, String direccion, String monto, Date fecha) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.monto = monto;
        this.fecha = fecha;
        this.profesorDAO = new ProfesorDAOSql();
    }

    public int getIdProfesor() {
        return idProfesor;
    }
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public boolean isTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(boolean tipoPago) {
        this.tipoPago = tipoPago;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
    public Date getFechaInicio(){
        return this.fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    
    @Override
    public int compareTo(Profesor profesor) {
        return this.nombre.compareTo(profesor.getNombre());
    }
    
    public boolean registrarProfesor(){
        return this.profesorDAO.registrarProfesor(this);
    }
    public boolean editarProfesor(){
        return this.profesorDAO.editarProfesor(this);
    }
    public List<Profesor> obtenerProfesores(){
        return this.profesorDAO.obtenerProfesores();
    }
    public List<Profesor> obtenerProfesores(String nombre){
        return this.profesorDAO.obtenerProfesores(nombre);
    }
    public Profesor obtenerProfesor(int idProfesor){
        return this.profesorDAO.obtenerProfesor(idProfesor);
    }
}
