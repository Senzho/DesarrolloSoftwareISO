/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.PagoalumnoJpaController;
import Accesodatos.Entidades.Pagoalumno;
import Accesodatos.Entidades.Promocion;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Pagos.PagoAlumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Desktop
 */
public class PagoAlumnoDAOSql implements PagoAlumnoDAO {

    @Override
    public List<PagoAlumno> obtenerPagos(int idAlumno) {
        List<PagoAlumno> listaPagos = new ArrayList();
        AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Alumno alumnoJpa = controller.findAlumno(idAlumno);
        for (Pagoalumno pagoJpa : alumnoJpa.getPagoalumnoCollection()) {
            listaPagos.add(this.obtenerEntidad(pagoJpa));
        }
        return listaPagos;
    }

    private PagoAlumno obtenerEntidad(Accesodatos.Entidades.Pagoalumno pagoJpa) {
        PagoAlumno pago = new PagoAlumno();
        pago.setFecha(pagoJpa.getFecha());
        pago.setIdPagoAlumno(pagoJpa.getIdPago());
        pago.setMonto(pagoJpa.getMonto());
        pago.setTipoPago(pagoJpa.getTipoPago().toString());
        return pago;
    }

    @Override
    public boolean registrarPago(PagoAlumno pagoAlumno, int idAlumno, Promocion promocion) {
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
                    if (promocion != null) {
                        promocion.setIdPromocion(0);
                        pagoJpa.setIdPromocion(promocion);
                    } else {
                        pagoJpa.setIdPromocion(promocion);
                    }
                    pagoJpa.setMonto(pagoAlumno.getMonto());
                    pagoJpa.setTipoPago(Integer.parseInt(pagoAlumno.getTipoPago()));
                    controller.create(pagoJpa);
                    registrado = true;
                }
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
        }
        return registrado;
    }

}
