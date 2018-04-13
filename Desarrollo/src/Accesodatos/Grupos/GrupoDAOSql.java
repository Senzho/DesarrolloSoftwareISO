package Accesodatos.Grupos;

import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class GrupoDAOSql implements GrupoDAO{
    private Grupo obtenerEntidad(Accesodatos.Entidades.Grupo grupoJpa){
        Grupo grupo = new Grupo();
        grupo.setDanza(grupoJpa.getDanza());
        grupo.setId(grupoJpa.getIdGrupo());
        grupo.setNombre(grupoJpa.getNombre());
        return grupo;
    }
    
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
        List<Grupo> grupos = new ArrayList();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        for (Accesodatos.Entidades.Grupo grupoJpa : profesorController.findProfesor(idProfesor).getGrupoCollection()){
            Grupo grupo = this.obtenerEntidad(grupoJpa);
            grupo.setHorario(new Horario(grupo.getId()));
            grupos.add(grupo);
        }
        return grupos;
    }
    @Override
    public List<Grupo> obtenerGruposAlumno(int idAlumnos) {
        return new ArrayList();
    }
}
