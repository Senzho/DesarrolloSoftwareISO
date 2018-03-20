/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Controladores;

import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import Accesodatos.Entidades.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Accesodatos.Entidades.Pagocliente;
import java.util.ArrayList;
import java.util.Collection;
import Accesodatos.Entidades.Renta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Desktop
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPagoclienteCollection() == null) {
            cliente.setPagoclienteCollection(new ArrayList<Pagocliente>());
        }
        if (cliente.getRentaCollection() == null) {
            cliente.setRentaCollection(new ArrayList<Renta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pagocliente> attachedPagoclienteCollection = new ArrayList<Pagocliente>();
            for (Pagocliente pagoclienteCollectionPagoclienteToAttach : cliente.getPagoclienteCollection()) {
                pagoclienteCollectionPagoclienteToAttach = em.getReference(pagoclienteCollectionPagoclienteToAttach.getClass(), pagoclienteCollectionPagoclienteToAttach.getIdPago());
                attachedPagoclienteCollection.add(pagoclienteCollectionPagoclienteToAttach);
            }
            cliente.setPagoclienteCollection(attachedPagoclienteCollection);
            Collection<Renta> attachedRentaCollection = new ArrayList<Renta>();
            for (Renta rentaCollectionRentaToAttach : cliente.getRentaCollection()) {
                rentaCollectionRentaToAttach = em.getReference(rentaCollectionRentaToAttach.getClass(), rentaCollectionRentaToAttach.getIdRenta());
                attachedRentaCollection.add(rentaCollectionRentaToAttach);
            }
            cliente.setRentaCollection(attachedRentaCollection);
            em.persist(cliente);
            for (Pagocliente pagoclienteCollectionPagocliente : cliente.getPagoclienteCollection()) {
                Cliente oldIdClienteOfPagoclienteCollectionPagocliente = pagoclienteCollectionPagocliente.getIdCliente();
                pagoclienteCollectionPagocliente.setIdCliente(cliente);
                pagoclienteCollectionPagocliente = em.merge(pagoclienteCollectionPagocliente);
                if (oldIdClienteOfPagoclienteCollectionPagocliente != null) {
                    oldIdClienteOfPagoclienteCollectionPagocliente.getPagoclienteCollection().remove(pagoclienteCollectionPagocliente);
                    oldIdClienteOfPagoclienteCollectionPagocliente = em.merge(oldIdClienteOfPagoclienteCollectionPagocliente);
                }
            }
            for (Renta rentaCollectionRenta : cliente.getRentaCollection()) {
                Cliente oldIdClienteOfRentaCollectionRenta = rentaCollectionRenta.getIdCliente();
                rentaCollectionRenta.setIdCliente(cliente);
                rentaCollectionRenta = em.merge(rentaCollectionRenta);
                if (oldIdClienteOfRentaCollectionRenta != null) {
                    oldIdClienteOfRentaCollectionRenta.getRentaCollection().remove(rentaCollectionRenta);
                    oldIdClienteOfRentaCollectionRenta = em.merge(oldIdClienteOfRentaCollectionRenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Collection<Pagocliente> pagoclienteCollectionOld = persistentCliente.getPagoclienteCollection();
            Collection<Pagocliente> pagoclienteCollectionNew = cliente.getPagoclienteCollection();
            Collection<Renta> rentaCollectionOld = persistentCliente.getRentaCollection();
            Collection<Renta> rentaCollectionNew = cliente.getRentaCollection();
            Collection<Pagocliente> attachedPagoclienteCollectionNew = new ArrayList<Pagocliente>();
            for (Pagocliente pagoclienteCollectionNewPagoclienteToAttach : pagoclienteCollectionNew) {
                pagoclienteCollectionNewPagoclienteToAttach = em.getReference(pagoclienteCollectionNewPagoclienteToAttach.getClass(), pagoclienteCollectionNewPagoclienteToAttach.getIdPago());
                attachedPagoclienteCollectionNew.add(pagoclienteCollectionNewPagoclienteToAttach);
            }
            pagoclienteCollectionNew = attachedPagoclienteCollectionNew;
            cliente.setPagoclienteCollection(pagoclienteCollectionNew);
            Collection<Renta> attachedRentaCollectionNew = new ArrayList<Renta>();
            for (Renta rentaCollectionNewRentaToAttach : rentaCollectionNew) {
                rentaCollectionNewRentaToAttach = em.getReference(rentaCollectionNewRentaToAttach.getClass(), rentaCollectionNewRentaToAttach.getIdRenta());
                attachedRentaCollectionNew.add(rentaCollectionNewRentaToAttach);
            }
            rentaCollectionNew = attachedRentaCollectionNew;
            cliente.setRentaCollection(rentaCollectionNew);
            cliente = em.merge(cliente);
            for (Pagocliente pagoclienteCollectionOldPagocliente : pagoclienteCollectionOld) {
                if (!pagoclienteCollectionNew.contains(pagoclienteCollectionOldPagocliente)) {
                    pagoclienteCollectionOldPagocliente.setIdCliente(null);
                    pagoclienteCollectionOldPagocliente = em.merge(pagoclienteCollectionOldPagocliente);
                }
            }
            for (Pagocliente pagoclienteCollectionNewPagocliente : pagoclienteCollectionNew) {
                if (!pagoclienteCollectionOld.contains(pagoclienteCollectionNewPagocliente)) {
                    Cliente oldIdClienteOfPagoclienteCollectionNewPagocliente = pagoclienteCollectionNewPagocliente.getIdCliente();
                    pagoclienteCollectionNewPagocliente.setIdCliente(cliente);
                    pagoclienteCollectionNewPagocliente = em.merge(pagoclienteCollectionNewPagocliente);
                    if (oldIdClienteOfPagoclienteCollectionNewPagocliente != null && !oldIdClienteOfPagoclienteCollectionNewPagocliente.equals(cliente)) {
                        oldIdClienteOfPagoclienteCollectionNewPagocliente.getPagoclienteCollection().remove(pagoclienteCollectionNewPagocliente);
                        oldIdClienteOfPagoclienteCollectionNewPagocliente = em.merge(oldIdClienteOfPagoclienteCollectionNewPagocliente);
                    }
                }
            }
            for (Renta rentaCollectionOldRenta : rentaCollectionOld) {
                if (!rentaCollectionNew.contains(rentaCollectionOldRenta)) {
                    rentaCollectionOldRenta.setIdCliente(null);
                    rentaCollectionOldRenta = em.merge(rentaCollectionOldRenta);
                }
            }
            for (Renta rentaCollectionNewRenta : rentaCollectionNew) {
                if (!rentaCollectionOld.contains(rentaCollectionNewRenta)) {
                    Cliente oldIdClienteOfRentaCollectionNewRenta = rentaCollectionNewRenta.getIdCliente();
                    rentaCollectionNewRenta.setIdCliente(cliente);
                    rentaCollectionNewRenta = em.merge(rentaCollectionNewRenta);
                    if (oldIdClienteOfRentaCollectionNewRenta != null && !oldIdClienteOfRentaCollectionNewRenta.equals(cliente)) {
                        oldIdClienteOfRentaCollectionNewRenta.getRentaCollection().remove(rentaCollectionNewRenta);
                        oldIdClienteOfRentaCollectionNewRenta = em.merge(oldIdClienteOfRentaCollectionNewRenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Collection<Pagocliente> pagoclienteCollection = cliente.getPagoclienteCollection();
            for (Pagocliente pagoclienteCollectionPagocliente : pagoclienteCollection) {
                pagoclienteCollectionPagocliente.setIdCliente(null);
                pagoclienteCollectionPagocliente = em.merge(pagoclienteCollectionPagocliente);
            }
            Collection<Renta> rentaCollection = cliente.getRentaCollection();
            for (Renta rentaCollectionRenta : rentaCollection) {
                rentaCollectionRenta.setIdCliente(null);
                rentaCollectionRenta = em.merge(rentaCollectionRenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
