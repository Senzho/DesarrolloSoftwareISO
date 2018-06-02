package Accesodatos.Asistencia;

import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Egresos.Dates;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AsistenciaDAOSql implements AsistenciaDAO {

    @Override
    public Asistencia obtenerAsistencia(int idGrupo, Date fecha) {
        return new Asistencia();
    }

    @Override
    public boolean asistenciaRegistrada(int idGrupo, Date fecha) {
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

    public List<Date> obtenerAsistencias(int idGrupo, int idAlumno){
        List<Date> fechasAsistencia = new ArrayList();
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Asistencia.findAllByIdCurso");
        query.setParameter("idGrupo", idGrupo);
        query.setParameter("idAlumno", idAlumno);
        List<Accesodatos.Entidades.Asistencia> resultado = query.getResultList();
        for(Accesodatos.Entidades.Asistencia asistenciaJpa:resultado){
            fechasAsistencia.add(asistenciaJpa.getFecha());
        }
        return fechasAsistencia;
    }
}
