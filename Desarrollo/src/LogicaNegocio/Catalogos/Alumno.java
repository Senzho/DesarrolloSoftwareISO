package LogicaNegocio.Catalogos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import java.util.Date;
import java.util.List;

public class Alumno implements Comparable<Alumno>{
    private int idAlumno;
    private String nombre;
    private String correo;
    private String teléfono;
    private String direccion;
    private Date fecha;
    private boolean estado;
    private AlumnoDAOSql alumnoDAO;

    public Alumno(){
        this.alumnoDAO = new AlumnoDAOSql();
    }
    public Alumno(int idAlumno, String nombre, String correo, String teléfono, String direccion, Date fecha, boolean estado) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.correo = correo;
        this.teléfono = teléfono;
        this.direccion = direccion;
        this.fecha = fecha;
        this.estado = estado;
        this.alumnoDAO = new AlumnoDAOSql();
    }

    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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
    public String getTeléfono() {
        return teléfono;
    }
    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public int compareTo(Alumno alumno) {
        return this.nombre.compareTo(alumno.getNombre());
    }

    public boolean registrarAlumno() {
        return this.alumnoDAO.registrarAlumno(this);
    }
    public boolean editarAlumno() {
        return this.alumnoDAO.editarAlumno(this);
    }
    public List<Alumno> obtenerAlumnos() {
        return this.alumnoDAO.obtenerAlumnos();
    }
    public List<Alumno> obtenerAlumnos(String nombre) {
        return this.alumnoDAO.obtenerAlumnos(nombre);
    }
    public int[] obtenerGrupos() {
        return this.alumnoDAO.obtenerGrupos();
    }
    public List<Alumno> obtenerAlumnos(int idGrupo){
        return this.alumnoDAO.obtenerAlumnos(idGrupo);
    }
    public Alumno obtenerAlumno(int idAlumno){
        return this.alumnoDAO.obtenerAlumno(idAlumno);
    }
}
