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
import Accesodatos.Entidades.Promocion;
import java.util.ArrayList;
import java.util.Collection;
import Accesodatos.Entidades.Gastopromocional;
import Accesodatos.Entidades.Grupo;
import Accesodatos.Entidades.Pagotemporal;
import Accesodatos.Entidades.Pagoalumno;
import Accesodatos.Entidades.Pagoprofesor;
import Accesodatos.Entidades.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
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
        if (profesor.getPromocionCollection() == null) {
            profesor.setPromocionCollection(new ArrayList<Promocion>());
        }
        if (profesor.getGastopromocionalCollection() == null) {
            profesor.setGastopromocionalCollection(new ArrayList<Gastopromocional>());
        }
        if (profesor.getGrupoCollection() == null) {
            profesor.setGrupoCollection(new ArrayList<Grupo>());
        }
        if (profesor.getPagotemporalCollection() == null) {
            profesor.setPagotemporalCollection(new ArrayList<Pagotemporal>());
        }
        if (profesor.getPagoalumnoCollection() == null) {
            profesor.setPagoalumnoCollection(new ArrayList<Pagoalumno>());
        }
        if (profesor.getPagoprofesorCollection() == null) {
            profesor.setPagoprofesorCollection(new ArrayList<Pagoprofesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Promocion> attachedPromocionCollection = new ArrayList<Promocion>();
            for (Promocion promocionCollectionPromocionToAttach : profesor.getPromocionCollection()) {
                promocionCollectionPromocionToAttach = em.getReference(promocionCollectionPromocionToAttach.getClass(), promocionCollectionPromocionToAttach.getIdPromocion());
                attachedPromocionCollection.add(promocionCollectionPromocionToAttach);
            }
            profesor.setPromocionCollection(attachedPromocionCollection);
            Collection<Gastopromocional> attachedGastopromocionalCollection = new ArrayList<Gastopromocional>();
            for (Gastopromocional gastopromocionalCollectionGastopromocionalToAttach : profesor.getGastopromocionalCollection()) {
                gastopromocionalCollectionGastopromocionalToAttach = em.getReference(gastopromocionalCollectionGastopromocionalToAttach.getClass(), gastopromocionalCollectionGastopromocionalToAttach.getIdGasto());
                attachedGastopromocionalCollection.add(gastopromocionalCollectionGastopromocionalToAttach);
            }
            profesor.setGastopromocionalCollection(attachedGastopromocionalCollection);
            Collection<Grupo> attachedGrupoCollection = new ArrayList<Grupo>();
            for (Grupo grupoCollectionGrupoToAttach : profesor.getGrupoCollection()) {
                grupoCollectionGrupoToAttach = em.getReference(grupoCollectionGrupoToAttach.getClass(), grupoCollectionGrupoToAttach.getIdGrupo());
                attachedGrupoCollection.add(grupoCollectionGrupoToAttach);
            }
            profesor.setGrupoCollection(attachedGrupoCollection);
            Collection<Pagotemporal> attachedPagotemporalCollection = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionPagotemporalToAttach : profesor.getPagotemporalCollection()) {
                pagotemporalCollectionPagotemporalToAttach = em.getReference(pagotemporalCollectionPagotemporalToAttach.getClass(), pagotemporalCollectionPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollection.add(pagotemporalCollectionPagotemporalToAttach);
            }
            profesor.setPagotemporalCollection(attachedPagotemporalCollection);
            Collection<Pagoalumno> attachedPagoalumnoCollection = new ArrayList<Pagoalumno>();
            for (Pagoalumno pagoalumnoCollectionPagoalumnoToAttach : profesor.getPagoalumnoCollection()) {
                pagoalumnoCollectionPagoalumnoToAttach = em.getReference(pagoalumnoCollectionPagoalumnoToAttach.getClass(), pagoalumnoCollectionPagoalumnoToAttach.getIdPago());
                attachedPagoalumnoCollection.add(pagoalumnoCollectionPagoalumnoToAttach);
            }
            profesor.setPagoalumnoCollection(attachedPagoalumnoCollection);
            Collection<Pagoprofesor> attachedPagoprofesorCollection = new ArrayList<Pagoprofesor>();
            for (Pagoprofesor pagoprofesorCollectionPagoprofesorToAttach : profesor.getPagoprofesorCollection()) {
                pagoprofesorCollectionPagoprofesorToAttach = em.getReference(pagoprofesorCollectionPagoprofesorToAttach.getClass(), pagoprofesorCollectionPagoprofesorToAttach.getIdPago());
                attachedPagoprofesorCollection.add(pagoprofesorCollectionPagoprofesorToAttach);
            }
            profesor.setPagoprofesorCollection(attachedPagoprofesorCollection);
            em.persist(profesor);
            for (Promocion promocionCollectionPromocion : profesor.getPromocionCollection()) {
                Profesor oldIdProfesorOfPromocionCollectionPromocion = promocionCollectionPromocion.getIdProfesor();
                promocionCollectionPromocion.setIdProfesor(profesor);
                promocionCollectionPromocion = em.merge(promocionCollectionPromocion);
                if (oldIdProfesorOfPromocionCollectionPromocion != null) {
                    oldIdProfesorOfPromocionCollectionPromocion.getPromocionCollection().remove(promocionCollectionPromocion);
                    oldIdProfesorOfPromocionCollectionPromocion = em.merge(oldIdProfesorOfPromocionCollectionPromocion);
                }
            }
            for (Gastopromocional gastopromocionalCollectionGastopromocional : profesor.getGastopromocionalCollection()) {
                Profesor oldIdProfesorOfGastopromocionalCollectionGastopromocional = gastopromocionalCollectionGastopromocional.getIdProfesor();
                gastopromocionalCollectionGastopromocional.setIdProfesor(profesor);
                gastopromocionalCollectionGastopromocional = em.merge(gastopromocionalCollectionGastopromocional);
                if (oldIdProfesorOfGastopromocionalCollectionGastopromocional != null) {
                    oldIdProfesorOfGastopromocionalCollectionGastopromocional.getGastopromocionalCollection().remove(gastopromocionalCollectionGastopromocional);
                    oldIdProfesorOfGastopromocionalCollectionGastopromocional = em.merge(oldIdProfesorOfGastopromocionalCollectionGastopromocional);
                }
            }
            for (Grupo grupoCollectionGrupo : profesor.getGrupoCollection()) {
                Profesor oldIdProfesorOfGrupoCollectionGrupo = grupoCollectionGrupo.getIdProfesor();
                grupoCollectionGrupo.setIdProfesor(profesor);
                grupoCollectionGrupo = em.merge(grupoCollectionGrupo);
                if (oldIdProfesorOfGrupoCollectionGrupo != null) {
                    oldIdProfesorOfGrupoCollectionGrupo.getGrupoCollection().remove(grupoCollectionGrupo);
                    oldIdProfesorOfGrupoCollectionGrupo = em.merge(oldIdProfesorOfGrupoCollectionGrupo);
                }
            }
            for (Pagotemporal pagotemporalCollectionPagotemporal : profesor.getPagotemporalCollection()) {
                Profesor oldIdProfesorOfPagotemporalCollectionPagotemporal = pagotemporalCollectionPagotemporal.getIdProfesor();
                pagotemporalCollectionPagotemporal.setIdProfesor(profesor);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
                if (oldIdProfesorOfPagotemporalCollectionPagotemporal != null) {
                    oldIdProfesorOfPagotemporalCollectionPagotemporal.getPagotemporalCollection().remove(pagotemporalCollectionPagotemporal);
                    oldIdProfesorOfPagotemporalCollectionPagotemporal = em.merge(oldIdProfesorOfPagotemporalCollectionPagotemporal);
                }
            }
            for (Pagoalumno pagoalumnoCollectionPagoalumno : profesor.getPagoalumnoCollection()) {
                Profesor oldIdProfesorOfPagoalumnoCollectionPagoalumno = pagoalumnoCollectionPagoalumno.getIdProfesor();
                pagoalumnoCollectionPagoalumno.setIdProfesor(profesor);
                pagoalumnoCollectionPagoalumno = em.merge(pagoalumnoCollectionPagoalumno);
                if (oldIdProfesorOfPagoalumnoCollectionPagoalumno != null) {
                    oldIdProfesorOfPagoalumnoCollectionPagoalumno.getPagoalumnoCollection().remove(pagoalumnoCollectionPagoalumno);
                    oldIdProfesorOfPagoalumnoCollectionPagoalumno = em.merge(oldIdProfesorOfPagoalumnoCollectionPagoalumno);
                }
            }
            for (Pagoprofesor pagoprofesorCollectionPagoprofesor : profesor.getPagoprofesorCollection()) {
                Profesor oldIdProfesorOfPagoprofesorCollectionPagoprofesor = pagoprofesorCollectionPagoprofesor.getIdProfesor();
                pagoprofesorCollectionPagoprofesor.setIdProfesor(profesor);
                pagoprofesorCollectionPagoprofesor = em.merge(pagoprofesorCollectionPagoprofesor);
                if (oldIdProfesorOfPagoprofesorCollectionPagoprofesor != null) {
                    oldIdProfesorOfPagoprofesorCollectionPagoprofesor.getPagoprofesorCollection().remove(pagoprofesorCollectionPagoprofesor);
                    oldIdProfesorOfPagoprofesorCollectionPagoprofesor = em.merge(oldIdProfesorOfPagoprofesorCollectionPagoprofesor);
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
            Collection<Promocion> promocionCollectionOld = persistentProfesor.getPromocionCollection();
            Collection<Promocion> promocionCollectionNew = profesor.getPromocionCollection();
            Collection<Gastopromocional> gastopromocionalCollectionOld = persistentProfesor.getGastopromocionalCollection();
            Collection<Gastopromocional> gastopromocionalCollectionNew = profesor.getGastopromocionalCollection();
            Collection<Grupo> grupoCollectionOld = persistentProfesor.getGrupoCollection();
            Collection<Grupo> grupoCollectionNew = profesor.getGrupoCollection();
            Collection<Pagotemporal> pagotemporalCollectionOld = persistentProfesor.getPagotemporalCollection();
            Collection<Pagotemporal> pagotemporalCollectionNew = profesor.getPagotemporalCollection();
            Collection<Pagoalumno> pagoalumnoCollectionOld = persistentProfesor.getPagoalumnoCollection();
            Collection<Pagoalumno> pagoalumnoCollectionNew = profesor.getPagoalumnoCollection();
            Collection<Pagoprofesor> pagoprofesorCollectionOld = persistentProfesor.getPagoprofesorCollection();
            Collection<Pagoprofesor> pagoprofesorCollectionNew = profesor.getPagoprofesorCollection();
            Collection<Promocion> attachedPromocionCollectionNew = new ArrayList<Promocion>();
            for (Promocion promocionCollectionNewPromocionToAttach : promocionCollectionNew) {
                promocionCollectionNewPromocionToAttach = em.getReference(promocionCollectionNewPromocionToAttach.getClass(), promocionCollectionNewPromocionToAttach.getIdPromocion());
                attachedPromocionCollectionNew.add(promocionCollectionNewPromocionToAttach);
            }
            promocionCollectionNew = attachedPromocionCollectionNew;
            profesor.setPromocionCollection(promocionCollectionNew);
            Collection<Gastopromocional> attachedGastopromocionalCollectionNew = new ArrayList<Gastopromocional>();
            for (Gastopromocional gastopromocionalCollectionNewGastopromocionalToAttach : gastopromocionalCollectionNew) {
                gastopromocionalCollectionNewGastopromocionalToAttach = em.getReference(gastopromocionalCollectionNewGastopromocionalToAttach.getClass(), gastopromocionalCollectionNewGastopromocionalToAttach.getIdGasto());
                attachedGastopromocionalCollectionNew.add(gastopromocionalCollectionNewGastopromocionalToAttach);
            }
            gastopromocionalCollectionNew = attachedGastopromocionalCollectionNew;
            profesor.setGastopromocionalCollection(gastopromocionalCollectionNew);
            Collection<Grupo> attachedGrupoCollectionNew = new ArrayList<Grupo>();
            for (Grupo grupoCollectionNewGrupoToAttach : grupoCollectionNew) {
                grupoCollectionNewGrupoToAttach = em.getReference(grupoCollectionNewGrupoToAttach.getClass(), grupoCollectionNewGrupoToAttach.getIdGrupo());
                attachedGrupoCollectionNew.add(grupoCollectionNewGrupoToAttach);
            }
            grupoCollectionNew = attachedGrupoCollectionNew;
            profesor.setGrupoCollection(grupoCollectionNew);
            Collection<Pagotemporal> attachedPagotemporalCollectionNew = new ArrayList<Pagotemporal>();
            for (Pagotemporal pagotemporalCollectionNewPagotemporalToAttach : pagotemporalCollectionNew) {
                pagotemporalCollectionNewPagotemporalToAttach = em.getReference(pagotemporalCollectionNewPagotemporalToAttach.getClass(), pagotemporalCollectionNewPagotemporalToAttach.getIdPago());
                attachedPagotemporalCollectionNew.add(pagotemporalCollectionNewPagotemporalToAttach);
            }
            pagotemporalCollectionNew = attachedPagotemporalCollectionNew;
            profesor.setPagotemporalCollection(pagotemporalCollectionNew);
            Collection<Pagoalumno> attachedPagoalumnoCollectionNew = new ArrayList<Pagoalumno>();
            for (Pagoalumno pagoalumnoCollectionNewPagoalumnoToAttach : pagoalumnoCollectionNew) {
                pagoalumnoCollectionNewPagoalumnoToAttach = em.getReference(pagoalumnoCollectionNewPagoalumnoToAttach.getClass(), pagoalumnoCollectionNewPagoalumnoToAttach.getIdPago());
                attachedPagoalumnoCollectionNew.add(pagoalumnoCollectionNewPagoalumnoToAttach);
            }
            pagoalumnoCollectionNew = attachedPagoalumnoCollectionNew;
            profesor.setPagoalumnoCollection(pagoalumnoCollectionNew);
            Collection<Pagoprofesor> attachedPagoprofesorCollectionNew = new ArrayList<Pagoprofesor>();
            for (Pagoprofesor pagoprofesorCollectionNewPagoprofesorToAttach : pagoprofesorCollectionNew) {
                pagoprofesorCollectionNewPagoprofesorToAttach = em.getReference(pagoprofesorCollectionNewPagoprofesorToAttach.getClass(), pagoprofesorCollectionNewPagoprofesorToAttach.getIdPago());
                attachedPagoprofesorCollectionNew.add(pagoprofesorCollectionNewPagoprofesorToAttach);
            }
            pagoprofesorCollectionNew = attachedPagoprofesorCollectionNew;
            profesor.setPagoprofesorCollection(pagoprofesorCollectionNew);
            profesor = em.merge(profesor);
            for (Promocion promocionCollectionOldPromocion : promocionCollectionOld) {
                if (!promocionCollectionNew.contains(promocionCollectionOldPromocion)) {
                    promocionCollectionOldPromocion.setIdProfesor(null);
                    promocionCollectionOldPromocion = em.merge(promocionCollectionOldPromocion);
                }
            }
            for (Promocion promocionCollectionNewPromocion : promocionCollectionNew) {
                if (!promocionCollectionOld.contains(promocionCollectionNewPromocion)) {
                    Profesor oldIdProfesorOfPromocionCollectionNewPromocion = promocionCollectionNewPromocion.getIdProfesor();
                    promocionCollectionNewPromocion.setIdProfesor(profesor);
                    promocionCollectionNewPromocion = em.merge(promocionCollectionNewPromocion);
                    if (oldIdProfesorOfPromocionCollectionNewPromocion != null && !oldIdProfesorOfPromocionCollectionNewPromocion.equals(profesor)) {
                        oldIdProfesorOfPromocionCollectionNewPromocion.getPromocionCollection().remove(promocionCollectionNewPromocion);
                        oldIdProfesorOfPromocionCollectionNewPromocion = em.merge(oldIdProfesorOfPromocionCollectionNewPromocion);
                    }
                }
            }
            for (Gastopromocional gastopromocionalCollectionOldGastopromocional : gastopromocionalCollectionOld) {
                if (!gastopromocionalCollectionNew.contains(gastopromocionalCollectionOldGastopromocional)) {
                    gastopromocionalCollectionOldGastopromocional.setIdProfesor(null);
                    gastopromocionalCollectionOldGastopromocional = em.merge(gastopromocionalCollectionOldGastopromocional);
                }
            }
            for (Gastopromocional gastopromocionalCollectionNewGastopromocional : gastopromocionalCollectionNew) {
                if (!gastopromocionalCollectionOld.contains(gastopromocionalCollectionNewGastopromocional)) {
                    Profesor oldIdProfesorOfGastopromocionalCollectionNewGastopromocional = gastopromocionalCollectionNewGastopromocional.getIdProfesor();
                    gastopromocionalCollectionNewGastopromocional.setIdProfesor(profesor);
                    gastopromocionalCollectionNewGastopromocional = em.merge(gastopromocionalCollectionNewGastopromocional);
                    if (oldIdProfesorOfGastopromocionalCollectionNewGastopromocional != null && !oldIdProfesorOfGastopromocionalCollectionNewGastopromocional.equals(profesor)) {
                        oldIdProfesorOfGastopromocionalCollectionNewGastopromocional.getGastopromocionalCollection().remove(gastopromocionalCollectionNewGastopromocional);
                        oldIdProfesorOfGastopromocionalCollectionNewGastopromocional = em.merge(oldIdProfesorOfGastopromocionalCollectionNewGastopromocional);
                    }
                }
            }
            for (Grupo grupoCollectionOldGrupo : grupoCollectionOld) {
                if (!grupoCollectionNew.contains(grupoCollectionOldGrupo)) {
                    grupoCollectionOldGrupo.setIdProfesor(null);
                    grupoCollectionOldGrupo = em.merge(grupoCollectionOldGrupo);
                }
            }
            for (Grupo grupoCollectionNewGrupo : grupoCollectionNew) {
                if (!grupoCollectionOld.contains(grupoCollectionNewGrupo)) {
                    Profesor oldIdProfesorOfGrupoCollectionNewGrupo = grupoCollectionNewGrupo.getIdProfesor();
                    grupoCollectionNewGrupo.setIdProfesor(profesor);
                    grupoCollectionNewGrupo = em.merge(grupoCollectionNewGrupo);
                    if (oldIdProfesorOfGrupoCollectionNewGrupo != null && !oldIdProfesorOfGrupoCollectionNewGrupo.equals(profesor)) {
                        oldIdProfesorOfGrupoCollectionNewGrupo.getGrupoCollection().remove(grupoCollectionNewGrupo);
                        oldIdProfesorOfGrupoCollectionNewGrupo = em.merge(oldIdProfesorOfGrupoCollectionNewGrupo);
                    }
                }
            }
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
            for (Pagoalumno pagoalumnoCollectionOldPagoalumno : pagoalumnoCollectionOld) {
                if (!pagoalumnoCollectionNew.contains(pagoalumnoCollectionOldPagoalumno)) {
                    pagoalumnoCollectionOldPagoalumno.setIdProfesor(null);
                    pagoalumnoCollectionOldPagoalumno = em.merge(pagoalumnoCollectionOldPagoalumno);
                }
            }
            for (Pagoalumno pagoalumnoCollectionNewPagoalumno : pagoalumnoCollectionNew) {
                if (!pagoalumnoCollectionOld.contains(pagoalumnoCollectionNewPagoalumno)) {
                    Profesor oldIdProfesorOfPagoalumnoCollectionNewPagoalumno = pagoalumnoCollectionNewPagoalumno.getIdProfesor();
                    pagoalumnoCollectionNewPagoalumno.setIdProfesor(profesor);
                    pagoalumnoCollectionNewPagoalumno = em.merge(pagoalumnoCollectionNewPagoalumno);
                    if (oldIdProfesorOfPagoalumnoCollectionNewPagoalumno != null && !oldIdProfesorOfPagoalumnoCollectionNewPagoalumno.equals(profesor)) {
                        oldIdProfesorOfPagoalumnoCollectionNewPagoalumno.getPagoalumnoCollection().remove(pagoalumnoCollectionNewPagoalumno);
                        oldIdProfesorOfPagoalumnoCollectionNewPagoalumno = em.merge(oldIdProfesorOfPagoalumnoCollectionNewPagoalumno);
                    }
                }
            }
            for (Pagoprofesor pagoprofesorCollectionOldPagoprofesor : pagoprofesorCollectionOld) {
                if (!pagoprofesorCollectionNew.contains(pagoprofesorCollectionOldPagoprofesor)) {
                    pagoprofesorCollectionOldPagoprofesor.setIdProfesor(null);
                    pagoprofesorCollectionOldPagoprofesor = em.merge(pagoprofesorCollectionOldPagoprofesor);
                }
            }
            for (Pagoprofesor pagoprofesorCollectionNewPagoprofesor : pagoprofesorCollectionNew) {
                if (!pagoprofesorCollectionOld.contains(pagoprofesorCollectionNewPagoprofesor)) {
                    Profesor oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor = pagoprofesorCollectionNewPagoprofesor.getIdProfesor();
                    pagoprofesorCollectionNewPagoprofesor.setIdProfesor(profesor);
                    pagoprofesorCollectionNewPagoprofesor = em.merge(pagoprofesorCollectionNewPagoprofesor);
                    if (oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor != null && !oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor.equals(profesor)) {
                        oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor.getPagoprofesorCollection().remove(pagoprofesorCollectionNewPagoprofesor);
                        oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor = em.merge(oldIdProfesorOfPagoprofesorCollectionNewPagoprofesor);
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
            Collection<Promocion> promocionCollection = profesor.getPromocionCollection();
            for (Promocion promocionCollectionPromocion : promocionCollection) {
                promocionCollectionPromocion.setIdProfesor(null);
                promocionCollectionPromocion = em.merge(promocionCollectionPromocion);
            }
            Collection<Gastopromocional> gastopromocionalCollection = profesor.getGastopromocionalCollection();
            for (Gastopromocional gastopromocionalCollectionGastopromocional : gastopromocionalCollection) {
                gastopromocionalCollectionGastopromocional.setIdProfesor(null);
                gastopromocionalCollectionGastopromocional = em.merge(gastopromocionalCollectionGastopromocional);
            }
            Collection<Grupo> grupoCollection = profesor.getGrupoCollection();
            for (Grupo grupoCollectionGrupo : grupoCollection) {
                grupoCollectionGrupo.setIdProfesor(null);
                grupoCollectionGrupo = em.merge(grupoCollectionGrupo);
            }
            Collection<Pagotemporal> pagotemporalCollection = profesor.getPagotemporalCollection();
            for (Pagotemporal pagotemporalCollectionPagotemporal : pagotemporalCollection) {
                pagotemporalCollectionPagotemporal.setIdProfesor(null);
                pagotemporalCollectionPagotemporal = em.merge(pagotemporalCollectionPagotemporal);
            }
            Collection<Pagoalumno> pagoalumnoCollection = profesor.getPagoalumnoCollection();
            for (Pagoalumno pagoalumnoCollectionPagoalumno : pagoalumnoCollection) {
                pagoalumnoCollectionPagoalumno.setIdProfesor(null);
                pagoalumnoCollectionPagoalumno = em.merge(pagoalumnoCollectionPagoalumno);
            }
            Collection<Pagoprofesor> pagoprofesorCollection = profesor.getPagoprofesorCollection();
            for (Pagoprofesor pagoprofesorCollectionPagoprofesor : pagoprofesorCollection) {
                pagoprofesorCollectionPagoprofesor.setIdProfesor(null);
                pagoprofesorCollectionPagoprofesor = em.merge(pagoprofesorCollectionPagoprofesor);
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
