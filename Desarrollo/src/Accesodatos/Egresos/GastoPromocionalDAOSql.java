/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import Accesodatos.Controladores.GastopromocionalJpaController;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Egresos.GastoPromocional;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Desktop
 */
public class GastoPromocionalDAOSql implements GastoPromocionalDAO {

    public GastoPromocionalDAOSql() {
    }

    @Override
    public boolean editarGasto(GastoPromocional gastoPromocional) {
        boolean gastoEditado = false;
        if (OperacionesString.URLValida(gastoPromocional.getURL()) && OperacionesString.montoValido(gastoPromocional.getMonto())) {
            GastopromocionalJpaController gastoController = new GastopromocionalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Gastopromocional gastoJpa = gastoController.findGastopromocional(gastoPromocional.getIdGastoPromocional());
            gastoJpa.setDescripcion(gastoPromocional.getDescripcion());
            gastoJpa.setFechaFin(gastoPromocional.getFechaFin());
            gastoJpa.setFechaInicio(gastoPromocional.getFechaInicio());
            gastoJpa.setMonto(gastoPromocional.getMonto());
            gastoJpa.setUrl(gastoPromocional.getURL());
            try {
                gastoController.edit(gastoJpa);
                gastoEditado = true;
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return gastoEditado;
    }

    @Override
    public List<GastoPromocional> obtenerGastos() {
        List<GastoPromocional> listaGastos = new ArrayList();
        GastopromocionalJpaController controller = new GastopromocionalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        controller.findGastopromocionalEntities().forEach((gastoJpa) -> {
            listaGastos.add(this.obtenerEntidad(gastoJpa));
        });
        return listaGastos;
    }

    private GastoPromocional obtenerEntidad(Accesodatos.Entidades.Gastopromocional egresoJpa) {
        GastoPromocional gastoPromocional = new GastoPromocional();
        gastoPromocional.setDescripcion(egresoJpa.getDescripcion());
        gastoPromocional.setFechaFin(egresoJpa.getFechaFin());
        gastoPromocional.setFechaInicio(egresoJpa.getFechaInicio());
        gastoPromocional.setIdGastoPromocional(egresoJpa.getIdGasto());
        gastoPromocional.setMonto(egresoJpa.getMonto());
        gastoPromocional.setURL(egresoJpa.getUrl());
        return gastoPromocional;
    }

    @Override
    public boolean registrarGasto(GastoPromocional gastoPromocional) {
        boolean gastoRegistrado = false;
        if (OperacionesString.URLValida(gastoPromocional.getURL()) && OperacionesString.montoValido(gastoPromocional.getMonto())) {
            GastopromocionalJpaController gastoController = new GastopromocionalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Gastopromocional gastoJpa = new Accesodatos.Entidades.Gastopromocional();
            gastoJpa.setDescripcion(gastoPromocional.getDescripcion());
            gastoJpa.setFechaFin(gastoPromocional.getFechaFin());
            gastoJpa.setFechaInicio(gastoPromocional.getFechaInicio());
            gastoJpa.setIdGasto(gastoPromocional.getIdGastoPromocional());
            gastoJpa.setMonto(gastoPromocional.getMonto());
            gastoJpa.setUrl(gastoPromocional.getURL());
            try {
                gastoController.create(gastoJpa);
                gastoPromocional.setIdGastoPromocional(gastoJpa.getIdGasto());
                gastoRegistrado = true;
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return gastoRegistrado;
    }

}
