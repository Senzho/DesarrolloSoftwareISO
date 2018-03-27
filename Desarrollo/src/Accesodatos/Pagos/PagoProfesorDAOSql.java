package Accesodatos.Pagos;

import Accesodatos.Controladores.PagoprofesorJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Pagos.PagoProfesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class PagoProfesorDAOSql implements PagoProfesorDAO{
    private Accesodatos.Entidades.Pagoprofesor obtenerEntidad(PagoProfesor pago){
        Accesodatos.Entidades.Pagoprofesor pagoJpa = new Accesodatos.Entidades.Pagoprofesor();
        pagoJpa.setFecha(pago.getFecha());
        pagoJpa.setIdPago(pago.getIdPago());
        pagoJpa.setMonto(pago.getMonto());
        int tipoPago;
        if (pago.isTipoPago()){
            tipoPago = 1;
        }else{
            tipoPago = 0;
        }
        pagoJpa.setTipoPago(tipoPago);
        return pagoJpa;
    }
    private PagoProfesor obtenerEntidad(Accesodatos.Entidades.Pagoprofesor pagoJpa){
        PagoProfesor pago = new PagoProfesor();
        pago.setFecha(pagoJpa.getFecha());
        pago.setIdPago(pagoJpa.getIdPago());
        pago.setMonto(pagoJpa.getMonto());
        pago.setTipoPago(pagoJpa.getTipoPago() == 1);
        return pago;
    }
    
    public PagoProfesorDAOSql(){
        
    }
    
    @Override
    public boolean registrarPago(PagoProfesor pago, int idProfesor) {
        boolean registrado;
        PagoprofesorJpaController controller = new PagoprofesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Pagoprofesor pagoJpa = this.obtenerEntidad(pago);
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try{
            pagoJpa.setIdProfesor(profesorController.findProfesor(idProfesor));
            controller.create(pagoJpa);
            pago.setIdPago(pagoJpa.getIdPago());
            registrado = true;
        }catch(Exception excepcion){
            registrado = false;
        }
        return registrado;
    }
    @Override
    public List<PagoProfesor> obtenerPagos(int idProfesor) {
        List<PagoProfesor> pagos = new ArrayList();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        profesorController.findProfesor(idProfesor).getPagoprofesorCollection().forEach((pagoJpa) -> {
            pagos.add(this.obtenerEntidad(pagoJpa));
        });
        return pagos;
    }
}
