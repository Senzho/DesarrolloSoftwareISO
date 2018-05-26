/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.PagoalumnoJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import Accesodatos.Controladores.PromocionJpaController;
import Accesodatos.Entidades.Pagoalumno;
import Accesodatos.Entidades.Profesor;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Pagos.PagoAlumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Desktop
 */
public class PagoAlumnoDAOSql implements PagoAlumnoDAO {

    @Override
    public List<PagoAlumno> obtenerPagos(int idAlumno, int idProfesor) {
        List<PagoAlumno> listaPagos = new ArrayList();
        AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Alumno alumnoJpa = controller.findAlumno(idAlumno);
        for (Pagoalumno pagoJpa : alumnoJpa.getPagoalumnoCollection()) {
            if (pagoJpa.getIdProfesor().getIdProfesor() == idProfesor) {
                listaPagos.add(this.obtenerEntidad(pagoJpa));
            }
        }
        return listaPagos;
    }
    
    private PagoAlumno obtenerEntidad(Accesodatos.Entidades.Pagoalumno pagoJpa) {
        PagoAlumno pago = new PagoAlumno();
        pago.setFecha(pagoJpa.getFecha());
        pago.setIdPagoAlumno(pagoJpa.getIdPago());
        pago.setMonto(pagoJpa.getMonto());
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Profesor profesorJpa = profesorController.findProfesor(pagoJpa.getIdProfesor().getIdProfesor());
        pago.setIdProfesor(profesorJpa.getIdProfesor());
        pago.setIdAlumno(pagoJpa.getIdAlumno().getIdAlumno());
        if (pagoJpa.getIdPromocion() != null) {
            pago.setIdPromocion(pagoJpa.getIdPromocion().getIdPromocion());
        } else {
            pago.setIdPromocion(0);
        }
        pago.setTipoPago(pagoJpa.getTipoPago());
        return pago;
    }

    @Override
    public boolean registrarPago(PagoAlumno pagoAlumno, int idAlumno, int idPromocion) {
        boolean registrado = false;
        if (OperacionesString.montoValido(pagoAlumno.getMonto())) {
            PagoalumnoJpaController controller = new PagoalumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Pagoalumno pagoJpa = new Accesodatos.Entidades.Pagoalumno();
            pagoJpa.setFecha(pagoAlumno.getFecha());
            AlumnoJpaController AlumnoController = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Alumno alumnoJpa = AlumnoController.findAlumno(idAlumno);
                if (alumnoJpa != null) {
                    pagoJpa.setIdAlumno(alumnoJpa);
                    pagoJpa.setFecha(pagoAlumno.getFecha());
                    pagoJpa.setIdPago(pagoAlumno.getIdPagoAlumno());
                    ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
                    Profesor profesorJpa = profesorController.findProfesor(pagoAlumno.getIdProfesor());
                    pagoJpa.setIdProfesor(profesorJpa);
                    PromocionJpaController promocionController = new PromocionJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
                    Accesodatos.Entidades.Promocion promocionJpa = promocionController.findPromocion(idPromocion);
                    if (promocionJpa != null) {
                        pagoJpa.setIdPromocion(promocionJpa);
                    }
                    pagoJpa.setMonto(pagoAlumno.getMonto());
                    pagoJpa.setTipoPago(pagoAlumno.getTipoPago());
                    controller.create(pagoJpa);
                    registrado = true;
                }
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return registrado;
    }

    @Override
    public List<PagoAlumno> obtenerPagos(int idProfesor) {
        List<PagoAlumno> listaPagos = new ArrayList();
        PagoalumnoJpaController controller = new PagoalumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Query query = controller.getEntityManager().createNamedQuery("Pagoalumno.findByIdProfesor");
        query.setParameter("idProfesor", idProfesor);
        List<Accesodatos.Entidades.Pagoalumno> pagoAlumnoJpa = query.getResultList();
        for (Pagoalumno pagoJpa : pagoAlumnoJpa) {
            listaPagos.add(this.obtenerEntidad(pagoJpa));
        }
        return listaPagos;
    }

}