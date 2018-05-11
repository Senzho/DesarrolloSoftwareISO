package Accesodatos.Asistencia;

import LogicaNegocio.Asistencia.Asistencia;
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
}
