/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import Accesodatos.Controladores.EgresoJpaController;
import LogicaNegocio.Egresos.Egreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Desktop
 */
public class EgresoDAOSql implements EgresoDAO {

    public EgresoDAOSql() {
    }

    @Override
    public boolean registrarEgreso(Egreso egreso) {
        boolean egresoRegistrado = false;
        EgresoJpaController egresoController = new EgresoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Egreso egresoJpa = new Accesodatos.Entidades.Egreso();
        egresoJpa.setIdEgreso(egreso.getIdEgreso());
        egresoJpa.setDescripcion(egreso.getDescripcion());
        egresoJpa.setFecha(egreso.getFecha());
        egresoJpa.setMonto(egreso.getMonto());
        try {
             egresoController.create(egresoJpa);
             egreso.setIdEgreso(egresoJpa.getIdEgreso());
            egresoRegistrado = true;
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
       
        return egresoRegistrado;
    }

    @Override
    public boolean editarEgreso(Egreso egreso) {
        boolean egresoRegistrado = false;
        EgresoJpaController egresoController = new EgresoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Egreso egresoJpa = egresoController.findEgreso(egreso.getIdEgreso());
        egresoJpa.setDescripcion(egreso.getDescripcion());
        egresoJpa.setFecha(egreso.getFecha());
        egresoJpa.setMonto(egreso.getMonto());
        try {
             egresoController.edit(egresoJpa);
            egresoRegistrado = true;
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
       
        return egresoRegistrado;
    }
    @Override
    public List<Egreso> obtenerEgresos() {
        List<Egreso> listaEgresos = new ArrayList();
        EgresoJpaController controller = new EgresoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        controller.findEgresoEntities().forEach((egresoJpa) -> {
            listaEgresos.add(this.obtenerEntidad(egresoJpa));
        });
        return listaEgresos;
    }
    private Egreso obtenerEntidad(Accesodatos.Entidades.Egreso egresoJpa){
        Egreso egreso = new Egreso();
        egreso.setDescripcion(egresoJpa.getDescripcion());
        egreso.setFecha(egresoJpa.getFecha());
        egreso.setIdEgreso(egresoJpa.getIdEgreso());
        egreso.setMonto(egresoJpa.getMonto());
        return egreso;
    }
}
