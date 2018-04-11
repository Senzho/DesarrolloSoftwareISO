package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Grupo;
import java.util.List;

public interface GrupoDAO {
    public boolean registrarGrupo(Grupo grupo);
    public boolean editarGrupo(Grupo grupo);
    public List<Grupo> obtenerGruposProfesor(int idProfesor);
    public List<Grupo> obtenerGruposAlumno(int idAlumnos);
}
