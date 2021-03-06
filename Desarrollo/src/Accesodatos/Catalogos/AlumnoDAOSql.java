package Accesodatos.Catalogos;

import Accesodatos.Controladores.AlumnoJpaController;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.OperacionesString;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AlumnoDAOSql implements AlumnoDAO {

    public AlumnoDAOSql() {

    }

    private Accesodatos.Entidades.Alumno obtenerEntidad(Alumno alumno) {
        Accesodatos.Entidades.Alumno alumnoJpa = new Accesodatos.Entidades.Alumno();
        alumnoJpa.setIdAlumno(alumno.getIdAlumno());
        alumnoJpa.setCorreo(alumno.getCorreo());
        alumnoJpa.setDireccion(alumno.getDireccion());
        int estado;
        if (alumno.isEstado()) {
            estado = 1;
        } else {
            estado = 0;
        }
        alumnoJpa.setEstado(estado);
        alumnoJpa.setFecha(alumno.getFecha());
        alumnoJpa.setNombre(alumno.getNombre());
        alumnoJpa.setTelefono(alumno.getTeléfono());
        return alumnoJpa;
    }

    public static Alumno obtenerEntidad(Accesodatos.Entidades.Alumno alumnoJpa) {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(alumnoJpa.getIdAlumno());
        alumno.setCorreo(alumnoJpa.getCorreo());
        alumno.setDireccion(alumnoJpa.getDireccion());
        boolean estado;
        estado = alumnoJpa.getEstado() != 0;
        alumno.setEstado(estado);
        alumno.setFecha(alumnoJpa.getFecha());
        alumno.setNombre(alumnoJpa.getNombre());
        alumno.setTeléfono(alumnoJpa.getTelefono());
        return alumno;
    }

    @Override
    public boolean registrarAlumno(Alumno alumno) {
        boolean registrado = false;
        if (OperacionesString.emailValido(alumno.getCorreo()) && OperacionesString.telefonoValido(alumno.getTeléfono())) {
            AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Alumno alumnoJpa = this.obtenerEntidad(alumno);
                controller.create(alumnoJpa);
                alumno.setIdAlumno(alumnoJpa.getIdAlumno());
                registrado = true;
            } catch (Exception excepcion) {
                Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, excepcion);
            }
        }
        return registrado;
    }

    @Override
    public boolean editarAlumno(Alumno alumno) {
        boolean editado = false;
        if (OperacionesString.emailValido(alumno.getCorreo()) && OperacionesString.telefonoValido(alumno.getTeléfono())) {
            AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Alumno alumnoJpa = controller.findAlumno(alumno.getIdAlumno());
                alumnoJpa.setCorreo(alumno.getCorreo());
                alumnoJpa.setDireccion(alumno.getDireccion());
                int estado;
                if (alumno.isEstado()) {
                    estado = 1;
                } else {
                    estado = 0;
                }
                alumnoJpa.setEstado(estado);
                alumnoJpa.setNombre(alumno.getNombre());
                alumnoJpa.setTelefono(alumno.getTeléfono());
                controller.edit(alumnoJpa);
                editado = true;
            } catch (Exception ex) {
                editado = false;
                Logger.getLogger(AlumnoDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return editado;
    }

    @Override
    public List<Alumno> obtenerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList();
        AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        controller.findAlumnoEntities().forEach((alumnoJpa) -> {
            alumnos.add(AlumnoDAOSql.obtenerEntidad(alumnoJpa));
        });
        return alumnos;
    }

    @Override
    public List<Alumno> obtenerAlumnos(String nombre) {
        List<Alumno> alumnos = new ArrayList();
        for (Alumno alumno : this.obtenerAlumnos()) {
            if (OperacionesString.coincide(nombre, alumno.getNombre())) {
                alumnos.add(alumno);
            }
        }
        return alumnos;
    }

    @Override
    public List<Alumno> obtenerAlumnos(int idGrupo) {
        List<Alumno> alumnos = new ArrayList();
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Alumno.findFromGrupo");
        query.setParameter("idGrupo", idGrupo);
        query.getResultList().forEach((alumnoJpa) -> {
            alumnos.add(AlumnoDAOSql.obtenerEntidad((Accesodatos.Entidades.Alumno) alumnoJpa));
        });
        return alumnos;
    }

    @Override
    public int[] obtenerGrupos() {
        int[] grupos = {0, 1};
        //A la espera de la entidad de Grupo
        return grupos;
    }

    @Override
    public Alumno obtenerAlumno(int idAlumno) {
        AlumnoJpaController controller = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Alumno alumno = AlumnoDAOSql.obtenerEntidad(controller.findAlumno(idAlumno));
        return alumno;
    }
}
