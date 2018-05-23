/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Controladores;

import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import Accesodatos.Entidades.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Accesodatos.Entidades.Inscripcion;
import java.util.ArrayList;
import java.util.Collection;
import Accesodatos.Entidades.Asistencia;
import Accesodatos.Entidades.Pagoalumno;
import Accesodatos.Entidades.Pagotemporal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        if (alumno.getInscripcionCollection() == null) {
            alumno.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        if (alumno.getAsistenciaCollection() == null) {
            alumno.setAsistenciaCollection(new ArrayList<Asistencia>());
        }
        if (alumno.getPagoalumnoCollection() == null) {
            alumno.setPagoalumnoCollection(new ArrayList<Pagoalumno>());
        }
        if (alumno.getPagotemporalCollection() == null) {
            alumno.setPagotemporalCollection(new ArrayList<Pagotemporal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : alumno.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getIdInscripcion());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            alumno.setInscripcionCollection(attachedInscripcionCollection);
            Collection<Asistencia> attachedAsistenciaCollection = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionAsistenciaToAttach : alumno.getAsistenciaCollection()) {
                asistenciaCollectionAsistenciaToAttach = em.getReference(asistenciaCollectionAsistenciaToAttach.getClass(), asistenciaCollectionAsistenciaToAttach.getIdAsistencia());
                attachedAsistenciaCollection.add(asistenciaCollectionAsistenciaToAttach);
            }
            alumno.setAsistenciaCollection(attachedAsistenciaCollection);
            Collection<Pagoalumno> attachedPagoalumnoCollection = new ArrayList<Pagoalumno>();
            for (Pagoalumno pagoalumnoCollectionPagoalumnoToAttach : alumno.getPagoalumnoCollection()) {
                pagoalumnoCollectionPagoalumnoToAttach = em.getReference(pagoalumnoCollectionPagoalumnoToAttach.getClass(), pagoalumnoCollectionPagoalumnoToAttach.getIdPago());
                attachedPagoalumnoCollection.add(pagoalumnoCollectionPagoalumnoToAttach);
            }
            alumno.setPagoalumnoCollection(attachedPagoalumnoCollection);
            Collection<Pagotemporal> attachedPagotemporalCollection = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionPagotemporalToAttach : alumno.getPagotemporalCollection()) {
                pagotemporalCollectionPagotemporalToAttach = em.getReference(pagotemporalCollectionPagotemporalToAttach.getClass(), pagotemporalCollectionPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollection.add(pagotemporalCollectionPagotemporalToAttach);
            }
            alumno.setPagotemporalCollection(attachedPagotemporalCollection);
            em.persist(alumno);
            for (Inscripcion inscripcionCollectionInscripcion : alumno.getInscripcionCollection()) {
                Alumno oldIdAlumnoOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getIdAlumno();
                inscripcionCollectionInscripcion.setIdAlumno(alumno);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldIdAlumnoOfInscripcionCollectionInscripcion != null) {
                    oldIdAlumnoOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldIdAlumnoOfInscripcionCollectionInscripcion = em.merge(oldIdAlumnoOfInscripcionCollectionInscripcion);
                }
            }
            for (Asistencia asistenciaCollectionAsistencia : alumno.getAsistenciaCollection()) {
                Alumno oldIdAlumnoOfAsistenciaCollectionAsistencia = asistenciaCollectionAsistencia.getIdAlumno();
                asistenciaCollectionAsistencia.setIdAlumno(alumno);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
                if (oldIdAlumnoOfAsistenciaCollectionAsistencia != null) {
                    oldIdAlumnoOfAsistenciaCollectionAsistencia.getAsistenciaCollection().remove(asistenciaCollectionAsistencia);
                    oldIdAlumnoOfAsistenciaCollectionAsistencia = em.merge(oldIdAlumnoOfAsistenciaCollectionAsistencia);
                }
            }
            for (Pagoalumno pagoalumnoCollectionPagoalumno : alumno.getPagoalumnoCollection()) {
                Alumno oldIdAlumnoOfPagoalumnoCollectionPagoalumno = pagoalumnoCollectionPagoalumno.getIdAlumno();
                pagoalumnoCollectionPagoalumno.setIdAlumno(alumno);
                pagoalumnoCollectionPagoalumno = em.merge(pagoalumnoCollectionPagoalumno);
                if (oldIdAlumnoOfPagoalumnoCollectionPagoalumno != null) {
                    oldIdAlumnoOfPagoalumnoCollectionPagoalumno.getPagoalumnoCollection().remove(pagoalumnoCollectionPagoalumno);
                    oldIdAlumnoOfPagoalumnoCollectionPagoalumno = em.merge(oldIdAlumnoOfPagoalumnoCollectionPagoalumno);
                }
            }
            for (Pagotemporal pagotemporalCollectionPagotemporal : alumno.getPagotemporalCollection()) {
                Alumno oldIdAlumnoOfPagotemporalCollectionPagotemporal = pagotemporalCollectionPagotemporal.getIdAlumno();
                pagotemporalCollectionPagotemporal.setIdAlumno(alumno);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
                if (oldIdAlumnoOfPagotemporalCollectionPagotemporal != null) {
                    oldIdAlumnoOfPagotemporalCollectionPagotemporal.getPagotemporalCollection().remove(pagotemporalCollectionPagotemporal);
                    oldIdAlumnoOfPagotemporalCollectionPagotemporal = em.merge(oldIdAlumnoOfPagotemporalCollectionPagotemporal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getIdAlumno());
            Collection<Inscripcion> inscripcionCollectionOld = persistentAlumno.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = alumno.getInscripcionCollection();
            Collection<Asistencia> asistenciaCollectionOld = persistentAlumno.getAsistenciaCollection();
            Collection<Asistencia> asistenciaCollectionNew = alumno.getAsistenciaCollection();
            Collection<Pagoalumno> pagoalumnoCollectionOld = persistentAlumno.getPagoalumnoCollection();
            Collection<Pagoalumno> pagoalumnoCollectionNew = alumno.getPagoalumnoCollection();
            Collection<Pagotemporal> pagotemporalCollectionOld = persistentAlumno.getPagotemporalCollection();
            Collection<Pagotemporal> pagotemporalCollectionNew = alumno.getPagotemporalCollection();
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getIdInscripcion());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            alumno.setInscripcionCollection(inscripcionCollectionNew);
            Collection<Asistencia> attachedAsistenciaCollectionNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionNewAsistenciaToAttach : asistenciaCollectionNew) {
                asistenciaCollectionNewAsistenciaToAttach = em.getReference(asistenciaCollectionNewAsistenciaToAttach.getClass(), asistenciaCollectionNewAsistenciaToAttach.getIdAsistencia());
                attachedAsistenciaCollectionNew.add(asistenciaCollectionNewAsistenciaToAttach);
            }
            asistenciaCollectionNew = attachedAsistenciaCollectionNew;
            alumno.setAsistenciaCollection(asistenciaCollectionNew);
            Collection<Pagoalumno> attachedPagoalumnoCollectionNew = new ArrayList<Pagoalumno>();
            for (Pagoalumno pagoalumnoCollectionNewPagoalumnoToAttach : pagoalumnoCollectionNew) {
                pagoalumnoCollectionNewPagoalumnoToAttach = em.getReference(pagoalumnoCollectionNewPagoalumnoToAttach.getClass(), pagoalumnoCollectionNewPagoalumnoToAttach.getIdPago());
                attachedPagoalumnoCollectionNew.add(pagoalumnoCollectionNewPagoalumnoToAttach);
            }
            pagoalumnoCollectionNew = attachedPagoalumnoCollectionNew;
            alumno.setPagoalumnoCollection(pagoalumnoCollectionNew);
            Collection<Pagotemporal> attachedPagotemporalCollectionNew = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionNewPagotemporalToAttach : pagotemporalCollectionNew) {
                pagotemporalCollectionNewPagotemporalToAttach = em.getReference(pagotemporalCollectionNewPagotemporalToAttach.getClass(), pagotemporalCollectionNewPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollectionNew.add(pagotemporalCollectionNewPagotemporalToAttach);
            }
            pagotemporalCollectionNew = attachedPagotemporalCollectionNew;
            alumno.setPagotemporalCollection(pagotemporalCollectionNew);
            alumno = em.merge(alumno);
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    inscripcionCollectionOldInscripcion.setIdAlumno(null);
                    inscripcionCollectionOldInscripcion = em.merge(inscripcionCollectionOldInscripcion);
                }
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Alumno oldIdAlumnoOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getIdAlumno();
                    inscripcionCollectionNewInscripcion.setIdAlumno(alumno);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldIdAlumnoOfInscripcionCollectionNewInscripcion != null && !oldIdAlumnoOfInscripcionCollectionNewInscripcion.equals(alumno)) {
                        oldIdAlumnoOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldIdAlumnoOfInscripcionCollectionNewInscripcion = em.merge(oldIdAlumnoOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            for (Asistencia asistenciaCollectionOldAsistencia : asistenciaCollectionOld) {
                if (!asistenciaCollectionNew.contains(asistenciaCollectionOldAsistencia)) {
                    asistenciaCollectionOldAsistencia.setIdAlumno(null);
                    asistenciaCollectionOldAsistencia = em.merge(asistenciaCollectionOldAsistencia);
                }
            }
            for (Asistencia asistenciaCollectionNewAsistencia : asistenciaCollectionNew) {
                if (!asistenciaCollectionOld.contains(asistenciaCollectionNewAsistencia)) {
                    Alumno oldIdAlumnoOfAsistenciaCollectionNewAsistencia = asistenciaCollectionNewAsistencia.getIdAlumno();
                    asistenciaCollectionNewAsistencia.setIdAlumno(alumno);
                    asistenciaCollectionNewAsistencia = em.merge(asistenciaCollectionNewAsistencia);
                    if (oldIdAlumnoOfAsistenciaCollectionNewAsistencia != null && !oldIdAlumnoOfAsistenciaCollectionNewAsistencia.equals(alumno)) {
                        oldIdAlumnoOfAsistenciaCollectionNewAsistencia.getAsistenciaCollection().remove(asistenciaCollectionNewAsistencia);
                        oldIdAlumnoOfAsistenciaCollectionNewAsistencia = em.merge(oldIdAlumnoOfAsistenciaCollectionNewAsistencia);
                    }
                }
            }
            for (Pagoalumno pagoalumnoCollectionOldPagoalumno : pagoalumnoCollectionOld) {
                if (!pagoalumnoCollectionNew.contains(pagoalumnoCollectionOldPagoalumno)) {
                    pagoalumnoCollectionOldPagoalumno.setIdAlumno(null);
                    pagoalumnoCollectionOldPagoalumno = em.merge(pagoalumnoCollectionOldPagoalumno);
                }
            }
            for (Pagoalumno pagoalumnoCollectionNewPagoalumno : pagoalumnoCollectionNew) {
                if (!pagoalumnoCollectionOld.contains(pagoalumnoCollectionNewPagoalumno)) {
                    Alumno oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno = pagoalumnoCollectionNewPagoalumno.getIdAlumno();
                    pagoalumnoCollectionNewPagoalumno.setIdAlumno(alumno);
                    pagoalumnoCollectionNewPagoalumno = em.merge(pagoalumnoCollectionNewPagoalumno);
                    if (oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno != null && !oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno.equals(alumno)) {
                        oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno.getPagoalumnoCollection().remove(pagoalumnoCollectionNewPagoalumno);
                        oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno = em.merge(oldIdAlumnoOfPagoalumnoCollectionNewPagoalumno);
                    }
                }
            }
            for (Pagotemporal pagotemporalCollectionOldPagotemporal : pagotemporalCollectionOld) {
                if (!pagotemporalCollectionNew.contains(pagotemporalCollectionOldPagotemporal)) {
                    pagotemporalCollectionOldPagotemporal.setIdAlumno(null);
                    pagotemporalCollectionOldPagotemporal = em.merge(pagotemporalCollectionOldPagotemporal);
                }
            }
            for (Pagotemporal pagotemporalCollectionNewPagotemporal : pagotemporalCollectionNew) {
                if (!pagotemporalCollectionOld.contains(pagotemporalCollectionNewPagotemporal)) {
                    Alumno oldIdAlumnoOfPagotemporalCollectionNewPagotemporal = pagotemporalCollectionNewPagotemporal.getIdAlumno();
                    pagotemporalCollectionNewPagotemporal.setIdAlumno(alumno);
                    pagotemporalCollectionNewPagotemporal = em.merge(pagotemporalCollectionNewPagotemporal);
                    if (oldIdAlumnoOfPagotemporalCollectionNewPagotemporal != null && !oldIdAlumnoOfPagotemporalCollectionNewPagotemporal.equals(alumno)) {
                        oldIdAlumnoOfPagotemporalCollectionNewPagotemporal.getPagotemporalCollection().remove(pagotemporalCollectionNewPagotemporal);
                        oldIdAlumnoOfPagotemporalCollectionNewPagotemporal = em.merge(oldIdAlumnoOfPagotemporalCollectionNewPagotemporal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alumno.getIdAlumno();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
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
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getIdAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            Collection<Inscripcion> inscripcionCollection = alumno.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionInscripcion : inscripcionCollection) {
                inscripcionCollectionInscripcion.setIdAlumno(null);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
            }
            Collection<Asistencia> asistenciaCollection = alumno.getAsistenciaCollection();
            for (Asistencia asistenciaCollectionAsistencia : asistenciaCollection) {
                asistenciaCollectionAsistencia.setIdAlumno(null);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
            }
            Collection<Pagoalumno> pagoalumnoCollection = alumno.getPagoalumnoCollection();
            for (Pagoalumno pagoalumnoCollectionPagoalumno : pagoalumnoCollection) {
                pagoalumnoCollectionPagoalumno.setIdAlumno(null);
                pagoalumnoCollectionPagoalumno = em.merge(pagoalumnoCollectionPagoalumno);
            }
            Collection<Pagotemporal> pagotemporalCollection = alumno.getPagotemporalCollection();
            for (Pagotemporal pagotemporalCollectionPagotemporal : pagotemporalCollection) {
                pagotemporalCollectionPagotemporal.setIdAlumno(null);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
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

    public Alumno findAlumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
