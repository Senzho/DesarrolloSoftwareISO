package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Alumno;
import java.util.ArrayList;

public interface AlumnoDAO {
    public Accesodatos.Entidades.Alumno obtenerEntidad(Alumno alumno);
    public boolean registrarAlumno(Alumno alumno);
    public boolean editarAlumno(Alumno alumno);
    public ArrayList<Alumno> obtenerAlumnos();
    public ArrayList obtenerPagos();//la lista regresa entidades de PagoAlumno
    public int[] obtenerGrupos();
}
