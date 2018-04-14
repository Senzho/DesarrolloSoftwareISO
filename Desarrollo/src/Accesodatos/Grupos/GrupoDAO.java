package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.HorarioException;
import java.util.List;

public interface GrupoDAO {
    public boolean registrarGrupo(Grupo grupo, int idProfesor) throws HorarioException;
    public boolean editarGrupo(Grupo grupo);
    public List<Grupo> obtenerGruposProfesor(int idProfesor);
    public List<Grupo> obtenerGruposAlumno(int idAlumnos);
    public List<Grupo> obtenerGrupos();
}
