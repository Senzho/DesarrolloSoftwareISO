package Accesodatos.Pagos;

import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.PagotemporalJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Entidades.Pagotemporal;
import LogicaNegocio.Pagos.PagoTemporal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class PagoTemporalDAOSql implements PagoTemporalDAO{
    @Override
    public boolean registrarPago(PagoTemporal pago, int idAlumno, int idProfesor) {
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
            Accesodatos.Entidades.Profesor profesorJpa = controladorProf.findProfesor(idProfesor);
            Accesodatos.Entidades.Alumno alumnoJpa = controladorAlumno.findAlumno(idAlumno);
            if (profesorJpa != null && alumnoJpa != null){
                controlador.create(pagoTemp);
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
        return pagos;
    }
    @Override
    public boolean eliminarPago(int idPago) {
        boolean eliminado = true;
        return eliminado;
    }
}
