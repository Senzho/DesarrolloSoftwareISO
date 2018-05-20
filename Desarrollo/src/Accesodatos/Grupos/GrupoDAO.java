package Accesodatos.Grupos;

import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.HorarioException;
import java.util.List;

public interface GrupoDAO {
    public boolean registrarGrupo(Grupo grupo) throws HorarioException;
    public boolean editarGrupo(Grupo grupo, List<Dia> listaOriginal) throws HorarioException;
    public List<Grupo> obtenerGruposProfesor(int idProfesor);
    public List<Grupo> obtenerGruposAlumno(int idAlumnos);
    public List<Grupo> obtenerGrupos();
    public boolean registrarAsistencia(Asistencia asistencia);
}
