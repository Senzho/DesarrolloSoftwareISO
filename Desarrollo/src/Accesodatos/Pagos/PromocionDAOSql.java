/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Controladores.PromocionJpaController;
import LogicaNegocio.Pagos.Promocion;
import javax.persistence.Persistence;

/**
 *
 * @author Desktop
 */
public class PromocionDAOSql implements PromocionDAO {

    @Override
    public boolean editarPromocion(Promocion promocion) {
        boolean editado = false;
        PromocionJpaController promocionController = new PromocionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Promocion promocionJpa = promocionController.findPromocion(promocion.getIdPromocion());
        Accesodatos.Entidades.Profesor profesorJpa = profesorController.findProfesor(promocion.getIdProfesor());
        promocionJpa.setDescripcion(promocion.getDescripcion());
        promocionJpa.setIdProfesor(profesorJpa);
        promocionJpa.setIdPromocion(promocion.getIdPromocion());
        promocionJpa.setNombre(promocion.getNombre());
        promocionJpa.setPorcentaje(Integer.MIN_VALUE);
        try {
            promocionController.edit(promocionJpa);
            editado = true;
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return editado;
    }

    @Override
    public boolean registrarPromocion(Promocion promocion) {
        boolean promocionRegistrada = false;
        PromocionJpaController promocionController = new PromocionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Promocion promocionJpa = new Accesodatos.Entidades.Promocion();
        promocionJpa.setDescripcion(promocionJpa.getDescripcion());
        promocionJpa.setIdProfesor(promocionJpa.getIdProfesor());
        promocionJpa.setIdPromocion(promocionJpa.getIdPromocion());
        promocionJpa.setNombre(promocionJpa.getNombre());
        promocionJpa.setPorcentaje(promocionJpa.getPorcentaje());
        try {
            promocionController.create(promocionJpa);
            promocion.setIdPromocion(promocionJpa.getIdPromocion());
            promocionRegistrada = true;
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return promocionRegistrada;
    }

}
