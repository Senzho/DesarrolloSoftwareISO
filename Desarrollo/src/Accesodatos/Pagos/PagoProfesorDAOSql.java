package Accesodatos.Pagos;

import Accesodatos.Controladores.PagoprofesorJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Entidades.Pagoprofesor;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Pagos.PagoProfesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class PagoProfesorDAOSql implements PagoProfesorDAO {

    private Accesodatos.Entidades.Pagoprofesor obtenerEntidad(PagoProfesor pago) {
        Accesodatos.Entidades.Pagoprofesor pagoJpa = new Accesodatos.Entidades.Pagoprofesor();
        pagoJpa.setFecha(pago.getFecha());
        pagoJpa.setIdPago(pago.getIdPago());
        pagoJpa.setMonto(pago.getMonto());
        int tipoPago;
        if (pago.isTipoPago()) {
            tipoPago = 1;
        } else {
            tipoPago = 0;
        }
        pagoJpa.setTipoPago(tipoPago);
        return pagoJpa;
    }

    private PagoProfesor obtenerEntidad(Accesodatos.Entidades.Pagoprofesor pagoJpa) {
        PagoProfesor pago = new PagoProfesor();
        pago.setFecha(pagoJpa.getFecha());
        pago.setIdPago(pagoJpa.getIdPago());
        pago.setMonto(pagoJpa.getMonto());
        pago.setIdProfesor(pagoJpa.getIdProfesor().getIdProfesor());
        pago.setTipoPago(pagoJpa.getTipoPago() == 1);
        return pago;
    }

    public PagoProfesorDAOSql() {

    }

    @Override
    public boolean registrarPago(PagoProfesor pago, int idProfesor) {
        boolean registrado = false;
        if (OperacionesString.montoValido(pago.getMonto())) {
            PagoprofesorJpaController controller = new PagoprofesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Pagoprofesor pagoJpa = this.obtenerEntidad(pago);
            ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Profesor profesorJpa = profesorController.findProfesor(idProfesor);
                if (profesorJpa != null) {
                    pagoJpa.setIdProfesor(profesorJpa);
                    controller.create(pagoJpa);
                    pago.setIdPago(pagoJpa.getIdPago());
                    registrado = true;
                }
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return registrado;
    }

    @Override
    public List<PagoProfesor> obtenerPagos(int idProfesor) {
        List<PagoProfesor> pagos = new ArrayList();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        for (Pagoprofesor pagoJpa : profesorController.findProfesor(idProfesor).getPagoprofesorCollection()) {
            pagos.add(this.obtenerEntidad(pagoJpa));
        }
        return pagos;
    }

    @Override
    public List<PagoProfesor> obtenerPagos() {
        List<PagoProfesor> listaPagos = new ArrayList();
        PagoprofesorJpaController controller = new PagoprofesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Pagoprofesor> pagoProfesorJpa = controller.findPagoprofesorEntities();
        for (Pagoprofesor pagoJpa : pagoProfesorJpa) {
            listaPagos.add(this.obtenerEntidad(pagoJpa));
        }
        return listaPagos;
    }
}
