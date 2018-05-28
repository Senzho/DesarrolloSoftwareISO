package Accesodatos.Asistencia;

import Accesodatos.Catalogos.AlumnoDAOSql;
import LogicaNegocio.Asistencia.Asistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AsistenciaDAOSql implements AsistenciaDAO{
    @Override
    public Asistencia obtenerAsistencia(int idGrupo, Date fecha) {
        return new Asistencia();
    }
    @Override
    public boolean asistenciaRegistrada(int idGrupo, Date fecha){
        boolean registrada;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Asistencia.findRegistrada");
        query.setParameter("idGrupo", idGrupo);
        query.setParameter("fecha", fecha);
        List resultado = query.getResultList();
        registrada = !resultado.isEmpty();
        return registrada;
    }
    private Asistencia obtenerEntidad(Accesodatos.Entidades.Asistencia asistenciaJpa) {
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(asistenciaJpa.getFecha());
        asistencia.setIdGrupo(asistenciaJpa.getIdGrupo().getIdGrupo());
        asistencia.setIdAsistencia(asistenciaJpa.getIdAsistencia());    
        return asistencia;
    }
    
    public List<Asistencia>  obtenerAsistencias(int idGrupo){
        List<Asistencia> listaAsistencias = new ArrayList<>();
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Asistencia.findAllByIdCurso");
        query.setParameter("idGrupo", idGrupo);
        List<Accesodatos.Entidades.Asistencia> asistenciasJpa = query.getResultList();
        for(Accesodatos.Entidades.Asistencia asistenciaJpa:asistenciasJpa){
            Asistencia asistencia = this.obtenerEntidad(asistenciaJpa);
            asistencia.setAlumnos(new AlumnoDAOSql().obtenerAlumnos());
            listaAsistencias.add(asistencia);
        }
        return listaAsistencias;
    }    
}
