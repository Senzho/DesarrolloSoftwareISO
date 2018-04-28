/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Controladores;

import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import Accesodatos.Entidades.Gastopromocional;
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
 * @author Victor Javier
 */
public class GastopromocionalJpaController implements Serializable {

    public GastopromocionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gastopromocional gastopromocional) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor idProfesor = gastopromocional.getIdProfesor();
            if (idProfesor != null) {
                idProfesor = em.getReference(idProfesor.getClass(), idProfesor.getIdProfesor());
                gastopromocional.setIdProfesor(idProfesor);
            }
            em.persist(gastopromocional);
            if (idProfesor != null) {
                idProfesor.getGastopromocionalCollection().add(gastopromocional);
                idProfesor = em.merge(idProfesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gastopromocional gastopromocional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gastopromocional persistentGastopromocional = em.find(Gastopromocional.class, gastopromocional.getIdGasto());
            Profesor idProfesorOld = persistentGastopromocional.getIdProfesor();
            Profesor idProfesorNew = gastopromocional.getIdProfesor();
            if (idProfesorNew != null) {
                idProfesorNew = em.getReference(idProfesorNew.getClass(), idProfesorNew.getIdProfesor());
                gastopromocional.setIdProfesor(idProfesorNew);
            }
            gastopromocional = em.merge(gastopromocional);
            if (idProfesorOld != null && !idProfesorOld.equals(idProfesorNew)) {
                idProfesorOld.getGastopromocionalCollection().remove(gastopromocional);
                idProfesorOld = em.merge(idProfesorOld);
            }
            if (idProfesorNew != null && !idProfesorNew.equals(idProfesorOld)) {
                idProfesorNew.getGastopromocionalCollection().add(gastopromocional);
                idProfesorNew = em.merge(idProfesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gastopromocional.getIdGasto();
                if (findGastopromocional(id) == null) {
                    throw new NonexistentEntityException("The gastopromocional with id " + id + " no longer exists.");
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
            Gastopromocional gastopromocional;
            try {
                gastopromocional = em.getReference(Gastopromocional.class, id);
                gastopromocional.getIdGasto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gastopromocional with id " + id + " no longer exists.", enfe);
            }
            Profesor idProfesor = gastopromocional.getIdProfesor();
            if (idProfesor != null) {
                idProfesor.getGastopromocionalCollection().remove(gastopromocional);
                idProfesor = em.merge(idProfesor);
            }
            em.remove(gastopromocional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gastopromocional> findGastopromocionalEntities() {
        return findGastopromocionalEntities(true, -1, -1);
    }

    public List<Gastopromocional> findGastopromocionalEntities(int maxResults, int firstResult) {
        return findGastopromocionalEntities(false, maxResults, firstResult);
    }

    private List<Gastopromocional> findGastopromocionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gastopromocional.class));
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

    public Gastopromocional findGastopromocional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gastopromocional.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastopromocionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gastopromocional> rt = cq.from(Gastopromocional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
