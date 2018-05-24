package Accesodatos.Pagos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.PagotemporalJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Controladores.exceptions.NonexistentEntityException;
import Accesodatos.Entidades.Pagotemporal;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Pagos.PagoTemporal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

public class PagoTemporalDAOSql implements PagoTemporalDAO{
    @Override
    public boolean registrarPago(PagoTemporal pago) {
        boolean registrado = true;
        PagotemporalJpaController controlador = new PagotemporalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Pagotemporal pagoTemp = new Pagotemporal();
        pagoTemp.setIdPago(pago.getIdPago());
        pagoTemp.setFecha(pago.getFecha());
        pagoTemp.setMonto(pago.getMonto());
        pagoTemp.setTipoPago(pago.getTipoPago());
        try{
            AlumnoJpaController controladorAlumno = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            ProfesorJpaController controladorProf = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Profesor profesorJpa = controladorProf.findProfesor(pago.getProfesor().getIdProfesor());
            Accesodatos.Entidades.Alumno alumnoJpa = controladorAlumno.findAlumno(pago.getAlumno().getIdAlumno());
            if (profesorJpa != null && alumnoJpa != null){
                pagoTemp.setIdProfesor(profesorJpa);
                pagoTemp.setIdAlumno(alumnoJpa);
                controlador.create(pagoTemp);
                pago.setIdPago(pagoTemp.getIdPago());
            }else{
                registrado = false;
            }
        }catch(Exception excepcion){
            registrado = false;
        }
        return registrado;
    }
    @Override
    public List<PagoTemporal> obtenerPagos() {
        List<PagoTemporal> pagos = new ArrayList();
        PagotemporalJpaController controller = new PagotemporalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Pagotemporal> pagosJpa = controller.findPagotemporalEntities();
        pagosJpa.forEach((pagoJpa) -> {
            PagoTemporal pagoTemp = new PagoTemporal();
            pagoTemp.setIdPago(pagoJpa.getIdPago());
            pagoTemp.setFecha(pagoJpa.getFecha());
            pagoTemp.setMonto(pagoJpa.getMonto());
            pagoTemp.setTipoPago(pagoJpa.getTipoPago());
            pagoTemp.setProfesor(ProfesorDAOSql.obtenerEntidad(pagoJpa.getIdProfesor()));
            pagoTemp.setAlumno(AlumnoDAOSql.obtenerEntidad(pagoJpa.getIdAlumno()));
            pagos.add(pagoTemp);
        });
        return pagos;
    }
    @Override
    public boolean eliminarPago(int idPago) {
        boolean eliminado = true;
        PagotemporalJpaController controller = new PagotemporalJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try {
            controller.destroy(idPago);
        } catch (NonexistentEntityException ex) {
            eliminado = false;
            Logger.getLogger(PagoTemporalDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminado;
    }
}
