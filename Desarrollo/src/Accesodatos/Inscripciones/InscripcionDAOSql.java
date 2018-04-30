package Accesodatos.Inscripciones;

import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.GrupoJpaController;
import Accesodatos.Controladores.InscripcionJpaController;
import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import LogicaNegocio.Inscripciones.Inscripcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InscripcionDAOSql implements InscripcionDAO {

    @Override
    public boolean registrar(Inscripcion inscripcion) {
        boolean registrado = true;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("CentroDeControlAredPU");
        InscripcionJpaController inscripcionController = new InscripcionJpaController(managerFactory);
        Accesodatos.Entidades.Inscripcion inscripcionJpa = new Accesodatos.Entidades.Inscripcion();
        AlumnoJpaController alumnoController = new AlumnoJpaController(managerFactory);
        GrupoJpaController grupoController = new GrupoJpaController(managerFactory);
        try {
            Accesodatos.Entidades.Alumno alumnoJpa = alumnoController.findAlumno(inscripcion.getIdAlumno());
            Accesodatos.Entidades.Grupo grupoJpa = grupoController.findGrupo(inscripcion.getIdGrupo());
            if (alumnoJpa != null && grupoJpa != null) {
                inscripcionJpa.setIdAlumno(alumnoJpa);
                inscripcionJpa.setIdGrupo(grupoJpa);
                inscripcionJpa.setIdInscripcion(0);
                inscripcionController.create(inscripcionJpa);
            } else {
                registrado = false;
            }
        } catch (Exception exception) {
            registrado = false;
        }
        return registrado;
    }

    @Override
    public boolean borrarRegistro(int idAlumno, int idGrupo) {
        boolean eliminado = false;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Inscripcion.findByIdAlumno");
        query.setParameter("idGrupo", idGrupo);
        query.setParameter("idAlumno", idAlumno);
        try{
            Accesodatos.Entidades.Inscripcion inscripcionJpa = (Accesodatos.Entidades.Inscripcion) query.getSingleResult();
            InscripcionJpaController controller = new InscripcionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                controller.destroy(inscripcionJpa.getIdInscripcion());
                eliminado = true;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(InscripcionDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return eliminado;
    }
}