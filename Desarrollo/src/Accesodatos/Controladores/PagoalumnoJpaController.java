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
import Accesodatos.Entidades.Pagoalumno;
import Accesodatos.Entidades.Promocion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class PagoalumnoJpaController implements Serializable {

    public PagoalumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagoalumno pagoalumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno idAlumno = pagoalumno.getIdAlumno();
            if (idAlumno != null) {
                idAlumno = em.getReference(idAlumno.getClass(), idAlumno.getIdAlumno());
                pagoalumno.setIdAlumno(idAlumno);
            }
            Promocion idPromocion = pagoalumno.getIdPromocion();
            if (idPromocion != null) {
                idPromocion = em.getReference(idPromocion.getClass(), idPromocion.getIdPromocion());
                pagoalumno.setIdPromocion(idPromocion);
            }
            em.persist(pagoalumno);
            if (idAlumno != null) {
                idAlumno.getPagoalumnoCollection().add(pagoalumno);
                idAlumno = em.merge(idAlumno);
            }
            if (idPromocion != null) {
                idPromocion.getPagoalumnoCollection().add(pagoalumno);
                idPromocion = em.merge(idPromocion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagoalumno pagoalumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagoalumno persistentPagoalumno = em.find(Pagoalumno.class, pagoalumno.getIdPago());
            Alumno idAlumnoOld = persistentPagoalumno.getIdAlumno();
            Alumno idAlumnoNew = pagoalumno.getIdAlumno();
            Promocion idPromocionOld = persistentPagoalumno.getIdPromocion();
            Promocion idPromocionNew = pagoalumno.getIdPromocion();
            if (idAlumnoNew != null) {
                idAlumnoNew = em.getReference(idAlumnoNew.getClass(), idAlumnoNew.getIdAlumno());
                pagoalumno.setIdAlumno(idAlumnoNew);
            }
            if (idPromocionNew != null) {
                idPromocionNew = em.getReference(idPromocionNew.getClass(), idPromocionNew.getIdPromocion());
                pagoalumno.setIdPromocion(idPromocionNew);
            }
            pagoalumno = em.merge(pagoalumno);
            if (idAlumnoOld != null && !idAlumnoOld.equals(idAlumnoNew)) {
                idAlumnoOld.getPagoalumnoCollection().remove(pagoalumno);
                idAlumnoOld = em.merge(idAlumnoOld);
            }
            if (idAlumnoNew != null && !idAlumnoNew.equals(idAlumnoOld)) {
                idAlumnoNew.getPagoalumnoCollection().add(pagoalumno);
                idAlumnoNew = em.merge(idAlumnoNew);
            }
            if (idPromocionOld != null && !idPromocionOld.equals(idPromocionNew)) {
                idPromocionOld.getPagoalumnoCollection().remove(pagoalumno);
                idPromocionOld = em.merge(idPromocionOld);
            }
            if (idPromocionNew != null && !idPromocionNew.equals(idPromocionOld)) {
                idPromocionNew.getPagoalumnoCollection().add(pagoalumno);
                idPromocionNew = em.merge(idPromocionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagoalumno.getIdPago();
                if (findPagoalumno(id) == null) {
                    throw new NonexistentEntityException("The pagoalumno with id " + id + " no longer exists.");
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
            Pagoalumno pagoalumno;
            try {
                pagoalumno = em.getReference(Pagoalumno.class, id);
                pagoalumno.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoalumno with id " + id + " no longer exists.", enfe);
            }
            Alumno idAlumno = pagoalumno.getIdAlumno();
            if (idAlumno != null) {
                idAlumno.getPagoalumnoCollection().remove(pagoalumno);
                idAlumno = em.merge(idAlumno);
            }
            Promocion idPromocion = pagoalumno.getIdPromocion();
            if (idPromocion != null) {
                idPromocion.getPagoalumnoCollection().remove(pagoalumno);
                idPromocion = em.merge(idPromocion);
            }
            em.remove(pagoalumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagoalumno> findPagoalumnoEntities() {
        return findPagoalumnoEntities(true, -1, -1);
    }

    public List<Pagoalumno> findPagoalumnoEntities(int maxResults, int firstResult) {
        return findPagoalumnoEntities(false, maxResults, firstResult);
    }

    private List<Pagoalumno> findPagoalumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagoalumno.class));
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

    public Pagoalumno findPagoalumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagoalumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoalumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagoalumno> rt = cq.from(Pagoalumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
