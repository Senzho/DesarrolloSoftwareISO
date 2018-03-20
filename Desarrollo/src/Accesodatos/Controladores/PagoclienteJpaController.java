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
import Accesodatos.Entidades.Cliente;
import Accesodatos.Entidades.Pagocliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class PagoclienteJpaController implements Serializable {

    public PagoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagocliente pagocliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = pagocliente.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                pagocliente.setIdCliente(idCliente);
            }
            em.persist(pagocliente);
            if (idCliente != null) {
                idCliente.getPagoclienteCollection().add(pagocliente);
                idCliente = em.merge(idCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagocliente pagocliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagocliente persistentPagocliente = em.find(Pagocliente.class, pagocliente.getIdPago());
            Cliente idClienteOld = persistentPagocliente.getIdCliente();
            Cliente idClienteNew = pagocliente.getIdCliente();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                pagocliente.setIdCliente(idClienteNew);
            }
            pagocliente = em.merge(pagocliente);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getPagoclienteCollection().remove(pagocliente);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getPagoclienteCollection().add(pagocliente);
                idClienteNew = em.merge(idClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagocliente.getIdPago();
                if (findPagocliente(id) == null) {
                    throw new NonexistentEntityException("The pagocliente with id " + id + " no longer exists.");
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
            Pagocliente pagocliente;
            try {
                pagocliente = em.getReference(Pagocliente.class, id);
                pagocliente.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagocliente with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = pagocliente.getIdCliente();
            if (idCliente != null) {
                idCliente.getPagoclienteCollection().remove(pagocliente);
                idCliente = em.merge(idCliente);
            }
            em.remove(pagocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagocliente> findPagoclienteEntities() {
        return findPagoclienteEntities(true, -1, -1);
    }

    public List<Pagocliente> findPagoclienteEntities(int maxResults, int firstResult) {
        return findPagoclienteEntities(false, maxResults, firstResult);
    }

    private List<Pagocliente> findPagoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagocliente.class));
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

    public Pagocliente findPagocliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagocliente> rt = cq.from(Pagocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
