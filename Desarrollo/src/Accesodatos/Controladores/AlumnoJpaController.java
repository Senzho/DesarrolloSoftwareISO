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
import Accesodatos.Entidades.Pagotemporal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
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
        if (alumno.getPagotemporalCollection() == null) {
            alumno.setPagotemporalCollection(new ArrayList<Pagotemporal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pagotemporal> attachedPagotemporalCollection = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionPagotemporalToAttach : alumno.getPagotemporalCollection()) {
                pagotemporalCollectionPagotemporalToAttach = em.getReference(pagotemporalCollectionPagotemporalToAttach.getClass(), pagotemporalCollectionPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollection.add(pagotemporalCollectionPagotemporalToAttach);
            }
            alumno.setPagotemporalCollection(attachedPagotemporalCollection);
            em.persist(alumno);
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
            Collection<Pagotemporal> pagotemporalCollectionOld = persistentAlumno.getPagotemporalCollection();
            Collection<Pagotemporal> pagotemporalCollectionNew = alumno.getPagotemporalCollection();
            Collection<Pagotemporal> attachedPagotemporalCollectionNew = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionNewPagotemporalToAttach : pagotemporalCollectionNew) {
                pagotemporalCollectionNewPagotemporalToAttach = em.getReference(pagotemporalCollectionNewPagotemporalToAttach.getClass(), pagotemporalCollectionNewPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollectionNew.add(pagotemporalCollectionNewPagotemporalToAttach);
            }
            pagotemporalCollectionNew = attachedPagotemporalCollectionNew;
            alumno.setPagotemporalCollection(pagotemporalCollectionNew);
            alumno = em.merge(alumno);
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
