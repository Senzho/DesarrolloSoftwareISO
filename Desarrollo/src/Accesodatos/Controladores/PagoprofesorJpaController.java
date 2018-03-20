/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Controladores;

import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import Accesodatos.Entidades.Pagoprofesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Accesodatos.Entidades.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class PagoprofesorJpaController implements Serializable {

    public PagoprofesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagoprofesor pagoprofesor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor idProfesor = pagoprofesor.getIdProfesor();
            if (idProfesor != null) {
                idProfesor = em.getReference(idProfesor.getClass(), idProfesor.getIdProfesor());
                pagoprofesor.setIdProfesor(idProfesor);
            }
            em.persist(pagoprofesor);
            if (idProfesor != null) {
                idProfesor.getPagoprofesorCollection().add(pagoprofesor);
                idProfesor = em.merge(idProfesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagoprofesor pagoprofesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagoprofesor persistentPagoprofesor = em.find(Pagoprofesor.class, pagoprofesor.getIdPago());
            Profesor idProfesorOld = persistentPagoprofesor.getIdProfesor();
            Profesor idProfesorNew = pagoprofesor.getIdProfesor();
            if (idProfesorNew != null) {
                idProfesorNew = em.getReference(idProfesorNew.getClass(), idProfesorNew.getIdProfesor());
                pagoprofesor.setIdProfesor(idProfesorNew);
            }
            pagoprofesor = em.merge(pagoprofesor);
            if (idProfesorOld != null && !idProfesorOld.equals(idProfesorNew)) {
                idProfesorOld.getPagoprofesorCollection().remove(pagoprofesor);
                idProfesorOld = em.merge(idProfesorOld);
            }
            if (idProfesorNew != null && !idProfesorNew.equals(idProfesorOld)) {
                idProfesorNew.getPagoprofesorCollection().add(pagoprofesor);
                idProfesorNew = em.merge(idProfesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagoprofesor.getIdPago();
                if (findPagoprofesor(id) == null) {
                    throw new NonexistentEntityException("The pagoprofesor with id " + id + " no longer exists.");
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
            Pagoprofesor pagoprofesor;
            try {
                pagoprofesor = em.getReference(Pagoprofesor.class, id);
                pagoprofesor.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoprofesor with id " + id + " no longer exists.", enfe);
            }
            Profesor idProfesor = pagoprofesor.getIdProfesor();
            if (idProfesor != null) {
                idProfesor.getPagoprofesorCollection().remove(pagoprofesor);
                idProfesor = em.merge(idProfesor);
            }
            em.remove(pagoprofesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagoprofesor> findPagoprofesorEntities() {
        return findPagoprofesorEntities(true, -1, -1);
    }

    public List<Pagoprofesor> findPagoprofesorEntities(int maxResults, int firstResult) {
        return findPagoprofesorEntities(false, maxResults, firstResult);
    }

    private List<Pagoprofesor> findPagoprofesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagoprofesor.class));
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

    public Pagoprofesor findPagoprofesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagoprofesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoprofesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagoprofesor> rt = cq.from(Pagoprofesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
