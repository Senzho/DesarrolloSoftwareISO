/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Controladores.PromocionJpaController;
import LogicaNegocio.Pagos.Promocion;
import java.util.ArrayList;
import java.util.List;
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
        if (profesorJpa != null) {
            promocionJpa.setDescripcion(promocion.getDescripcion());
            promocionJpa.setIdProfesor(profesorJpa);
            promocionJpa.setIdPromocion(promocion.getIdPromocion());
            promocionJpa.setNombre(promocion.getNombre());
            promocionJpa.setPorcentaje(promocion.getPorcentaje());
            try {
                promocionController.edit(promocionJpa);
                editado = true;
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return editado;
    }

    @Override
    public boolean registrarPromocion(Promocion promocion) {
        boolean promocionRegistrada = false;
        PromocionJpaController promocionController = new PromocionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Promocion promocionJpa = new Accesodatos.Entidades.Promocion();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));

        try {
            Accesodatos.Entidades.Profesor profesorJpa = profesorController.findProfesor(promocion.getIdProfesor());
            if (profesorJpa != null) {
                promocionJpa.setDescripcion(promocion.getDescripcion());
                promocionJpa.setIdProfesor(profesorJpa);
                promocionJpa.setIdPromocion(promocion.getIdPromocion());
                promocionJpa.setNombre(promocion.getNombre());
                promocionJpa.setPorcentaje(promocion.getPorcentaje());
                promocionController.create(promocionJpa);
                promocion.setIdPromocion(promocionJpa.getIdPromocion());
                promocionRegistrada = true;
            }
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return promocionRegistrada;
    }

    @Override
    public List<Promocion> obtenerPromociones(int idProfesor) {
        List<Promocion> promociones = new ArrayList<>();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Profesor profesorJpa = profesorController.findProfesor(idProfesor);
        for (Accesodatos.Entidades.Promocion promocionJpa : profesorJpa.getPromocionCollection()) {
            promociones.add(this.obtenerEntidad(promocionJpa));
        }
        return promociones;
    }

    private Promocion obtenerEntidad(Accesodatos.Entidades.Promocion promocionJpa) {
        Promocion promocion = new Promocion();
        promocion.setDescripcion(promocionJpa.getDescripcion());
        promocion.setIdProfesor(promocionJpa.getIdProfesor().getIdProfesor());
        promocion.setIdPromocion(promocionJpa.getIdPromocion());
        promocion.setNombre(promocionJpa.getNombre());
        promocion.setPorcentaje(promocionJpa.getPorcentaje());
        return promocion;
    }
    @Override
    public Promocion obtenerPromocion(int idPromocion){
        PromocionJpaController controller = new PromocionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Promocion promocionJpa = controller.findPromocion(idPromocion);
        Promocion promocion = new Promocion();
        promocion.setDescripcion(promocionJpa.getDescripcion());
        promocion.setIdProfesor(promocionJpa.getIdProfesor().getIdProfesor());
        promocion.setIdPromocion(promocionJpa.getIdPromocion());
        promocion.setNombre(promocionJpa.getNombre());
        promocion.setPorcentaje(promocionJpa.getPorcentaje());
        return promocion;
    }
}
