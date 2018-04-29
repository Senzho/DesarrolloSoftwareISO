package Accesodatos.Inscripciones;

import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.GrupoJpaController;
import Accesodatos.Controladores.InscripcionJpaController;
import LogicaNegocio.Inscripciones.Inscripcion;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InscripcionDAOSql implements InscripcionDAO{
    @Override
    public boolean registrar(Inscripcion inscripcion) {
        boolean registrado = true;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("CentroDeControlAredPU");
        InscripcionJpaController inscripcionController = new InscripcionJpaController(managerFactory);
        Accesodatos.Entidades.Inscripcion inscripcionJpa = new Accesodatos.Entidades.Inscripcion();
        AlumnoJpaController alumnoController = new AlumnoJpaController(managerFactory);
        GrupoJpaController grupoController = new GrupoJpaController(managerFactory);
        try{
            Accesodatos.Entidades.Alumno alumnoJpa = alumnoController.findAlumno(inscripcion.getIdAlumno());
            Accesodatos.Entidades.Grupo grupoJpa = grupoController.findGrupo(inscripcion.getIdGrupo());
            if (alumnoJpa != null && grupoJpa != null){
                inscripcionJpa.setIdAlumno(alumnoJpa);
                inscripcionJpa.setIdGrupo(grupoJpa);
                inscripcionJpa.setIdInscripcion(0);
                inscripcionController.create(inscripcionJpa);
            }else{
                registrado = false;
            }
        }catch(Exception exception){
            registrado = false;
        }
        return registrado;
    }
    @Override
    public boolean borrarRegistro(int idAlumno, int idGrupo) {
        return false;
    }
}
