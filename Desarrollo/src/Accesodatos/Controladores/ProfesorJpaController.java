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
import Accesodatos.Entidades.Pagotemporal;
import Accesodatos.Entidades.Profesor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class ProfesorJpaController implements Serializable {

    public ProfesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profesor profesor) {
        if (profesor.getPagotemporalCollection() == null) {
            profesor.setPagotemporalCollection(new ArrayList<Pagotemporal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pagotemporal> attachedPagotemporalCollection = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionPagotemporalToAttach : profesor.getPagotemporalCollection()) {
                pagotemporalCollectionPagotemporalToAttach = em.getReference(pagotemporalCollectionPagotemporalToAttach.getClass(), pagotemporalCollectionPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollection.add(pagotemporalCollectionPagotemporalToAttach);
            }
            profesor.setPagotemporalCollection(attachedPagotemporalCollection);
            em.persist(profesor);
            for (Pagotemporal pagotemporalCollectionPagotemporal : profesor.getPagotemporalCollection()) {
                Profesor oldIdProfesorOfPagotemporalCollectionPagotemporal = pagotemporalCollectionPagotemporal.getIdProfesor();
                pagotemporalCollectionPagotemporal.setIdProfesor(profesor);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
                if (oldIdProfesorOfPagotemporalCollectionPagotemporal != null) {
                    oldIdProfesorOfPagotemporalCollectionPagotemporal.getPagotemporalCollection().remove(pagotemporalCollectionPagotemporal);
                    oldIdProfesorOfPagotemporalCollectionPagotemporal = em.merge(oldIdProfesorOfPagotemporalCollectionPagotemporal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profesor profesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor persistentProfesor = em.find(Profesor.class, profesor.getIdProfesor());
            Collection<Pagotemporal> pagotemporalCollectionOld = persistentProfesor.getPagotemporalCollection();
            Collection<Pagotemporal> pagotemporalCollectionNew = profesor.getPagotemporalCollection();
            Collection<Pagotemporal> attachedPagotemporalCollectionNew = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionNewPagotemporalToAttach : pagotemporalCollectionNew) {
                pagotemporalCollectionNewPagotemporalToAttach = em.getReference(pagotemporalCollectionNewPagotemporalToAttach.getClass(), pagotemporalCollectionNewPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollectionNew.add(pagotemporalCollectionNewPagotemporalToAttach);
            }
            pagotemporalCollectionNew = attachedPagotemporalCollectionNew;
            profesor.setPagotemporalCollection(pagotemporalCollectionNew);
            profesor = em.merge(profesor);
            for (Pagotemporal pagotemporalCollectionOldPagotemporal : pagotemporalCollectionOld) {
                if (!pagotemporalCollectionNew.contains(pagotemporalCollectionOldPagotemporal)) {
                    pagotemporalCollectionOldPagotemporal.setIdProfesor(null);
                    pagotemporalCollectionOldPagotemporal = em.merge(pagotemporalCollectionOldPagotemporal);
                }
            }
            for (Pagotemporal pagotemporalCollectionNewPagotemporal : pagotemporalCollectionNew) {
                if (!pagotemporalCollectionOld.contains(pagotemporalCollectionNewPagotemporal)) {
                    Profesor oldIdProfesorOfPagotemporalCollectionNewPagotemporal = pagotemporalCollectionNewPagotemporal.getIdProfesor();
                    pagotemporalCollectionNewPagotemporal.setIdProfesor(profesor);
                    pagotemporalCollectionNewPagotemporal = em.merge(pagotemporalCollectionNewPagotemporal);
                    if (oldIdProfesorOfPagotemporalCollectionNewPagotemporal != null && !oldIdProfesorOfPagotemporalCollectionNewPagotemporal.equals(profesor)) {
                        oldIdProfesorOfPagotemporalCollectionNewPagotemporal.getPagotemporalCollection().remove(pagotemporalCollectionNewPagotemporal);
                        oldIdProfesorOfPagotemporalCollectionNewPagotemporal = em.merge(oldIdProfesorOfPagotemporalCollectionNewPagotemporal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profesor.getIdProfesor();
                if (findProfesor(id) == null) {
                    throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.");
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
            Profesor profesor;
            try {
                profesor = em.getReference(Profesor.class, id);
                profesor.getIdProfesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.", enfe);
            }
            Collection<Pagotemporal> pagotemporalCollection = profesor.getPagotemporalCollection();
            for (Pagotemporal pagotemporalCollectionPagotemporal : pagotemporalCollection) {
                pagotemporalCollectionPagotemporal.setIdProfesor(null);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
            }
            em.remove(profesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profesor> findProfesorEntities() {
        return findProfesorEntities(true, -1, -1);
    }

    public List<Profesor> findProfesorEntities(int maxResults, int firstResult) {
        return findProfesorEntities(false, maxResults, firstResult);
    }

    private List<Profesor> findProfesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profesor.class));
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

    public Profesor findProfesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profesor> rt = cq.from(Profesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
