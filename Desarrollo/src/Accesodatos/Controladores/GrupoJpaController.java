/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Controladores;

import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Accesodatos.Entidades.Profesor;
import Accesodatos.Entidades.Inscripcion;
import java.util.ArrayList;
import java.util.Collection;
import Accesodatos.Entidades.Asistencia;
import Accesodatos.Entidades.Grupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getInscripcionCollection() == null) {
            grupo.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        if (grupo.getAsistenciaCollection() == null) {
            grupo.setAsistenciaCollection(new ArrayList<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor idProfesor = grupo.getIdProfesor();
            if (idProfesor != null) {
                idProfesor = em.getReference(idProfesor.getClass(), idProfesor.getIdProfesor());
                grupo.setIdProfesor(idProfesor);
            }
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : grupo.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getIdInscripcion());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            grupo.setInscripcionCollection(attachedInscripcionCollection);
            Collection<Asistencia> attachedAsistenciaCollection = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionAsistenciaToAttach : grupo.getAsistenciaCollection()) {
                asistenciaCollectionAsistenciaToAttach = em.getReference(asistenciaCollectionAsistenciaToAttach.getClass(), asistenciaCollectionAsistenciaToAttach.getIdAsistencia());
                attachedAsistenciaCollection.add(asistenciaCollectionAsistenciaToAttach);
            }
            grupo.setAsistenciaCollection(attachedAsistenciaCollection);
            em.persist(grupo);
            if (idProfesor != null) {
                idProfesor.getGrupoCollection().add(grupo);
                idProfesor = em.merge(idProfesor);
            }
            for (Inscripcion inscripcionCollectionInscripcion : grupo.getInscripcionCollection()) {
                Grupo oldIdGrupoOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getIdGrupo();
                inscripcionCollectionInscripcion.setIdGrupo(grupo);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldIdGrupoOfInscripcionCollectionInscripcion != null) {
                    oldIdGrupoOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldIdGrupoOfInscripcionCollectionInscripcion = em.merge(oldIdGrupoOfInscripcionCollectionInscripcion);
                }
            }
            for (Asistencia asistenciaCollectionAsistencia : grupo.getAsistenciaCollection()) {
                Grupo oldIdGrupoOfAsistenciaCollectionAsistencia = asistenciaCollectionAsistencia.getIdGrupo();
                asistenciaCollectionAsistencia.setIdGrupo(grupo);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
                if (oldIdGrupoOfAsistenciaCollectionAsistencia != null) {
                    oldIdGrupoOfAsistenciaCollectionAsistencia.getAsistenciaCollection().remove(asistenciaCollectionAsistencia);
                    oldIdGrupoOfAsistenciaCollectionAsistencia = em.merge(oldIdGrupoOfAsistenciaCollectionAsistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdGrupo());
            Profesor idProfesorOld = persistentGrupo.getIdProfesor();
            Profesor idProfesorNew = grupo.getIdProfesor();
            Collection<Inscripcion> inscripcionCollectionOld = persistentGrupo.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = grupo.getInscripcionCollection();
            Collection<Asistencia> asistenciaCollectionOld = persistentGrupo.getAsistenciaCollection();
            Collection<Asistencia> asistenciaCollectionNew = grupo.getAsistenciaCollection();
            if (idProfesorNew != null) {
                idProfesorNew = em.getReference(idProfesorNew.getClass(), idProfesorNew.getIdProfesor());
                grupo.setIdProfesor(idProfesorNew);
            }
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getIdInscripcion());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            grupo.setInscripcionCollection(inscripcionCollectionNew);
            Collection<Asistencia> attachedAsistenciaCollectionNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionNewAsistenciaToAttach : asistenciaCollectionNew) {
                asistenciaCollectionNewAsistenciaToAttach = em.getReference(asistenciaCollectionNewAsistenciaToAttach.getClass(), asistenciaCollectionNewAsistenciaToAttach.getIdAsistencia());
                attachedAsistenciaCollectionNew.add(asistenciaCollectionNewAsistenciaToAttach);
            }
            asistenciaCollectionNew = attachedAsistenciaCollectionNew;
            grupo.setAsistenciaCollection(asistenciaCollectionNew);
            grupo = em.merge(grupo);
            if (idProfesorOld != null && !idProfesorOld.equals(idProfesorNew)) {
                idProfesorOld.getGrupoCollection().remove(grupo);
                idProfesorOld = em.merge(idProfesorOld);
            }
            if (idProfesorNew != null && !idProfesorNew.equals(idProfesorOld)) {
                idProfesorNew.getGrupoCollection().add(grupo);
                idProfesorNew = em.merge(idProfesorNew);
            }
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    inscripcionCollectionOldInscripcion.setIdGrupo(null);
                    inscripcionCollectionOldInscripcion = em.merge(inscripcionCollectionOldInscripcion);
                }
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Grupo oldIdGrupoOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getIdGrupo();
                    inscripcionCollectionNewInscripcion.setIdGrupo(grupo);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldIdGrupoOfInscripcionCollectionNewInscripcion != null && !oldIdGrupoOfInscripcionCollectionNewInscripcion.equals(grupo)) {
                        oldIdGrupoOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldIdGrupoOfInscripcionCollectionNewInscripcion = em.merge(oldIdGrupoOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            for (Asistencia asistenciaCollectionOldAsistencia : asistenciaCollectionOld) {
                if (!asistenciaCollectionNew.contains(asistenciaCollectionOldAsistencia)) {
                    asistenciaCollectionOldAsistencia.setIdGrupo(null);
                    asistenciaCollectionOldAsistencia = em.merge(asistenciaCollectionOldAsistencia);
                }
            }
            for (Asistencia asistenciaCollectionNewAsistencia : asistenciaCollectionNew) {
                if (!asistenciaCollectionOld.contains(asistenciaCollectionNewAsistencia)) {
                    Grupo oldIdGrupoOfAsistenciaCollectionNewAsistencia = asistenciaCollectionNewAsistencia.getIdGrupo();
                    asistenciaCollectionNewAsistencia.setIdGrupo(grupo);
                    asistenciaCollectionNewAsistencia = em.merge(asistenciaCollectionNewAsistencia);
                    if (oldIdGrupoOfAsistenciaCollectionNewAsistencia != null && !oldIdGrupoOfAsistenciaCollectionNewAsistencia.equals(grupo)) {
                        oldIdGrupoOfAsistenciaCollectionNewAsistencia.getAsistenciaCollection().remove(asistenciaCollectionNewAsistencia);
                        oldIdGrupoOfAsistenciaCollectionNewAsistencia = em.merge(oldIdGrupoOfAsistenciaCollectionNewAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getIdGrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            Profesor idProfesor = grupo.getIdProfesor();
            if (idProfesor != null) {
                idProfesor.getGrupoCollection().remove(grupo);
                idProfesor = em.merge(idProfesor);
            }
            Collection<Inscripcion> inscripcionCollection = grupo.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionInscripcion : inscripcionCollection) {
                inscripcionCollectionInscripcion.setIdGrupo(null);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
            }
            Collection<Asistencia> asistenciaCollection = grupo.getAsistenciaCollection();
            for (Asistencia asistenciaCollectionAsistencia : asistenciaCollection) {
                asistenciaCollectionAsistencia.setIdGrupo(null);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
