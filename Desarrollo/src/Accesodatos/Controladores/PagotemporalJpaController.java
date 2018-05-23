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
import Accesodatos.Entidades.Alumno;
import Accesodatos.Entidades.Pagotemporal;
import Accesodatos.Entidades.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
 */
public class PagotemporalJpaController implements Serializable {

    public PagotemporalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagotemporal pagotemporal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno idAlumno = pagotemporal.getIdAlumno();
            if (idAlumno != null) {
                idAlumno = em.getReference(idAlumno.getClass(), idAlumno.getIdAlumno());
                pagotemporal.setIdAlumno(idAlumno);
            }
            Profesor idProfesor = pagotemporal.getIdProfesor();
            if (idProfesor != null) {
                idProfesor = em.getReference(idProfesor.getClass(), idProfesor.getIdProfesor());
                pagotemporal.setIdProfesor(idProfesor);
            }
            em.persist(pagotemporal);
            if (idAlumno != null) {
                idAlumno.getPagotemporalCollection().add(pagotemporal);
                idAlumno = em.merge(idAlumno);
            }
            if (idProfesor != null) {
                idProfesor.getPagotemporalCollection().add(pagotemporal);
                idProfesor = em.merge(idProfesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagotemporal pagotemporal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagotemporal persistentPagotemporal = em.find(Pagotemporal.class, pagotemporal.getIdPago());
            Alumno idAlumnoOld = persistentPagotemporal.getIdAlumno();
            Alumno idAlumnoNew = pagotemporal.getIdAlumno();
            Profesor idProfesorOld = persistentPagotemporal.getIdProfesor();
            Profesor idProfesorNew = pagotemporal.getIdProfesor();
            if (idAlumnoNew != null) {
                idAlumnoNew = em.getReference(idAlumnoNew.getClass(), idAlumnoNew.getIdAlumno());
                pagotemporal.setIdAlumno(idAlumnoNew);
            }
            if (idProfesorNew != null) {
                idProfesorNew = em.getReference(idProfesorNew.getClass(), idProfesorNew.getIdProfesor());
                pagotemporal.setIdProfesor(idProfesorNew);
            }
            pagotemporal = em.merge(pagotemporal);
            if (idAlumnoOld != null && !idAlumnoOld.equals(idAlumnoNew)) {
                idAlumnoOld.getPagotemporalCollection().remove(pagotemporal);
                idAlumnoOld = em.merge(idAlumnoOld);
            }
            if (idAlumnoNew != null && !idAlumnoNew.equals(idAlumnoOld)) {
                idAlumnoNew.getPagotemporalCollection().add(pagotemporal);
                idAlumnoNew = em.merge(idAlumnoNew);
            }
            if (idProfesorOld != null && !idProfesorOld.equals(idProfesorNew)) {
                idProfesorOld.getPagotemporalCollection().remove(pagotemporal);
                idProfesorOld = em.merge(idProfesorOld);
            }
            if (idProfesorNew != null && !idProfesorNew.equals(idProfesorOld)) {
                idProfesorNew.getPagotemporalCollection().add(pagotemporal);
                idProfesorNew = em.merge(idProfesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagotemporal.getIdPago();
                if (findPagotemporal(id) == null) {
                    throw new NonexistentEntityException("The pagotemporal with id " + id + " no longer exists.");
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
            Pagotemporal pagotemporal;
            try {
                pagotemporal = em.getReference(Pagotemporal.class, id);
                pagotemporal.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagotemporal with id " + id + " no longer exists.", enfe);
            }
            Alumno idAlumno = pagotemporal.getIdAlumno();
            if (idAlumno != null) {
                idAlumno.getPagotemporalCollection().remove(pagotemporal);
                idAlumno = em.merge(idAlumno);
            }
            Profesor idProfesor = pagotemporal.getIdProfesor();
            if (idProfesor != null) {
                idProfesor.getPagotemporalCollection().remove(pagotemporal);
                idProfesor = em.merge(idProfesor);
            }
            em.remove(pagotemporal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagotemporal> findPagotemporalEntities() {
        return findPagotemporalEntities(true, -1, -1);
    }

    public List<Pagotemporal> findPagotemporalEntities(int maxResults, int firstResult) {
        return findPagotemporalEntities(false, maxResults, firstResult);
    }

    private List<Pagotemporal> findPagotemporalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagotemporal.class));
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

    public Pagotemporal findPagotemporal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagotemporal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagotemporalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagotemporal> rt = cq.from(Pagotemporal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
