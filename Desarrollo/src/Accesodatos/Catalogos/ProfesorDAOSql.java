package Accesodatos.Catalogos;

import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Catalogos.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

public class ProfesorDAOSql implements ProfesorDAO {

    private Accesodatos.Entidades.Profesor obtenerEntidad(Profesor profesor) {
        Accesodatos.Entidades.Profesor profesorJpa = new Accesodatos.Entidades.Profesor();
        profesorJpa.setCorreo(profesor.getCorreo());
        profesorJpa.setDireccion(profesor.getDireccion());
        int estado;
        if (profesor.isEstado()) {
            estado = 1;
        } else {
            estado = 0;
        }
        profesorJpa.setEstado(estado);
        profesorJpa.setFecha(profesor.getFecha());
        profesorJpa.setIdProfesor(profesor.getIdProfesor());
        profesorJpa.setNombre(profesor.getNombre());
        profesorJpa.setTelefono(profesor.getTelefono());
        int tipoPago;
        if (profesor.isTipoPago()) {
            tipoPago = 1;
        } else {
            tipoPago = 0;
        }
        profesorJpa.setTipoPago(tipoPago);
        profesorJpa.setMonto(profesor.getMonto());
        profesorJpa.setFechaInicio(profesor.getFechaInicio());
        return profesorJpa;
    }

    public static Profesor obtenerEntidad(Accesodatos.Entidades.Profesor profesorJpa) {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(profesorJpa.getIdProfesor());
        profesor.setCorreo(profesorJpa.getCorreo());
        profesor.setDireccion(profesorJpa.getDireccion());
        profesor.setEstado(profesorJpa.getEstado() != 0);
        profesor.setFecha(profesorJpa.getFecha());
        profesor.setMonto(profesorJpa.getMonto());
        profesor.setNombre(profesorJpa.getNombre());
        profesor.setTelefono(profesorJpa.getTelefono());
        profesor.setTipoPago(profesorJpa.getTipoPago() != 0);
        profesor.setFechaInicio(profesorJpa.getFechaInicio());
        return profesor;
    }

    public ProfesorDAOSql() {

    }

    @Override
    public boolean registrarProfesor(Profesor profesor) {
        boolean registrado = false;
        if (OperacionesString.emailValido(profesor.getCorreo()) && OperacionesString.telefonoValido(profesor.getTelefono()) && OperacionesString.montoValido(profesor.getMonto())) {
            ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Profesor profesorJpa = this.obtenerEntidad(profesor);
                controller.create(profesorJpa);
                profesor.setIdProfesor(profesorJpa.getIdProfesor());
                registrado = true;
            } catch (Exception excepcion) {
                registrado = false;
                Logger.getLogger(ProfesorDAOSql.class.getName()).log(Level.SEVERE, null, excepcion);
            }
        }
        return registrado;
    }

    @Override
    public boolean editarProfesor(Profesor profesor) {
        boolean editado = false;
        if (OperacionesString.emailValido(profesor.getCorreo()) && OperacionesString.telefonoValido(profesor.getTelefono()) && OperacionesString.montoValido(profesor.getMonto())) {
            ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Profesor profesorJpa = controller.findProfesor(profesor.getIdProfesor());
                profesorJpa.setCorreo(profesor.getCorreo());
                profesorJpa.setDireccion(profesor.getDireccion());
                int estado;
                if (profesor.isEstado()) {
                    estado = 1;
                } else {
                    estado = 0;
                }
                profesorJpa.setEstado(estado);
                profesorJpa.setMonto(profesor.getMonto());
                profesorJpa.setNombre(profesor.getNombre());
                profesorJpa.setTelefono(profesor.getTelefono());
                int tipoPago;
                if (profesor.isTipoPago()) {
                    tipoPago = 1;
                } else {
                    tipoPago = 0;
                }
                profesorJpa.setTipoPago(tipoPago);
                profesorJpa.setFechaInicio(profesor.getFechaInicio());
                controller.edit(profesorJpa);
                editado = true;
            } catch (Exception ex) {
                Logger.getLogger(ProfesorDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return editado;
    }

    @Override
    public List<Profesor> obtenerProfesores() {
        ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Profesor> profesores = new ArrayList();
        controller.findProfesorEntities().forEach((profesorJpa) -> {
            profesores.add(ProfesorDAOSql.obtenerEntidad(profesorJpa));
        });
        return profesores;
    }

    @Override
    public List<Profesor> obtenerProfesores(String nombre) {
        List<Profesor> profesores = new ArrayList();
        for (Profesor profesor : this.obtenerProfesores()) {
            if (OperacionesString.coincide(nombre, profesor.getNombre())) {
                profesores.add(profesor);
            }
        }
        return profesores;
    }

    @Override
    public Profesor obtenerProfesor(int idProfesor) {
        ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Profesor profesor = ProfesorDAOSql.obtenerEntidad(controller.findProfesor(idProfesor));
        return profesor;
    }
}
