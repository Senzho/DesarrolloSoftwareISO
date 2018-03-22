package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Alumno;
import java.util.List;

public interface AlumnoDAO {
    public boolean registrarAlumno(Alumno alumno);
    public boolean editarAlumno(Alumno alumno);
    public List<Alumno> obtenerAlumnos();
    public List<Alumno> obtenerAlumnos(String nombre);
    public List obtenerPagos();//la lista regresa entidades de PagoAlumno
    public int[] obtenerGrupos();
}
