package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Grupo;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAOSql implements GrupoDAO{
    public GrupoDAOSql(){
        
    }
    
    @Override
    public boolean registrarGrupo(Grupo grupo) {
        return false;
    }
    @Override
    public boolean editarGrupo(Grupo grupo) {
        return false;
    }
    @Override
    public List<Grupo> obtenerGruposProfesor(int idProfesor) {
        return new ArrayList();
    }
    @Override
    public List<Grupo> obtenerGruposAlumno(int idAlumnos) {
        return new ArrayList();
    }
}
