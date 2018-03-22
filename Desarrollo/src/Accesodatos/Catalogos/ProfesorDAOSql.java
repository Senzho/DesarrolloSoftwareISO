package Accesodatos.Catalogos;

import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Catalogos.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

public class ProfesorDAOSql implements ProfesorDAO{
    private Accesodatos.Entidades.Profesor obtenerEntidad(Profesor profesor){
        Accesodatos.Entidades.Profesor profesorJpa = new Accesodatos.Entidades.Profesor();
        profesorJpa.setCorreo(profesor.getCorreo());
        profesorJpa.setDireccion(profesor.getDireccion());
        int estado;
        if (profesor.isEstado()){
            estado = 1;
        }else{
            estado = 0;
        }
        profesorJpa.setEstado(estado);
        profesorJpa.setFecha(profesor.getFecha());
        profesorJpa.setIdProfesor(profesor.getIdProfesor());
        profesorJpa.setNombre(profesor.getNombre());
        profesorJpa.setTelefono(profesor.getTelefono());
        int tipoPago;
        if (profesor.isTipoPago()){
            tipoPago = 1;
        }else{
            tipoPago = 0;
        }
        profesorJpa.setTipoPago(tipoPago);
        profesorJpa.setMonto(profesor.getMonto());
        return profesorJpa;
    }
    private Profesor obtenerEntidad(Accesodatos.Entidades.Profesor profesorJpa){
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
        return profesor;
    }
    
    public ProfesorDAOSql(){
        
    }
    
    @Override
    public boolean registrarProfesor(Profesor profesor) {
        boolean registrado = false;
        ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try{
            controller.create(this.obtenerEntidad(profesor));
        }catch(Exception excepcion){
            registrado = false;
            Logger.getLogger(ProfesorDAOSql.class.getName()).log(Level.SEVERE, null, excepcion);
        }
        return registrado;
    }
    @Override
    public boolean editarProfesor(Profesor profesor) {
        boolean editado = false;
        ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try {
            Accesodatos.Entidades.Profesor profesorJpa = controller.findProfesor(profesor.getIdProfesor());
            profesorJpa.setCorreo(profesor.getCorreo());
            profesorJpa.setDireccion(profesor.getDireccion());
            int estado;
            if (profesor.isEstado()){
                estado = 1;
            }else{
                estado = 0;
            }
            profesorJpa.setEstado(estado);
            profesorJpa.setMonto(profesor.getMonto());
            profesorJpa.setNombre(profesor.getNombre());
            profesorJpa.setTelefono(profesor.getTelefono());
            int tipoPago;
            if (profesor.isTipoPago()){
                tipoPago = 1;
            }else{
                tipoPago = 0;
            }
            profesorJpa.setTipoPago(tipoPago);
            controller.edit(profesorJpa);
            editado = true;
        } catch (Exception ex) {
            Logger.getLogger(ProfesorDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editado;
    }
    @Override
    public List obtenerPromociones(int idProfesor) {//A la espera de entidad Promocion
        List promociones = new ArrayList();
        return promociones;
    }
    @Override
    public List<Profesor> obtenerProfesores() {
        ProfesorJpaController controller = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Profesor> profesores = new ArrayList();
        controller.findProfesorEntities().forEach((profesorJpa) -> {
            profesores.add(this.obtenerEntidad(profesorJpa));
        });
        return profesores;
    }
}
