package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Alumno;
import java.util.List;

public interface AlumnoDAO {
    public boolean registrarAlumno(Alumno alumno);
    public boolean editarAlumno(Alumno alumno);
    public List<Alumno> obtenerAlumnos();
    public List<Alumno> obtenerAlumnos(String nombre);
    public List<Alumno> obtenerAlumnos(int idGrupo);
    public int[] obtenerGrupos();
}
