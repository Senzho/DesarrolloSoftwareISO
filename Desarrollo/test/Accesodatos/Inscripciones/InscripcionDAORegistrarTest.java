package Accesodatos.Inscripciones;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Inscripciones.Inscripcion;
import org.junit.Test;
import static org.junit.Assert.*;

public class InscripcionDAORegistrarTest {
    private InscripcionDAOSql inscripcionDAO;
    private Inscripcion inscripcion;
    private int idGrupo;
    private int idAlumno;
    
    public InscripcionDAORegistrarTest() {
        this.idAlumno = new AlumnoDAOSql().obtenerAlumnos().get(0).getIdAlumno();
        this.idGrupo = new GrupoDAOSql().obtenerGrupos().get(0).getId();
        this.inscripcionDAO = new InscripcionDAOSql();
        this.inscripcion = new Inscripcion();
        this.inscripcion.setIdInscripcion(0);
    }
    
    @Test
    public void registrarTrue(){
        this.inscripcion.setIdAlumno(this.idAlumno);
        this.inscripcion.setIdGrupo(this.idGrupo);
        assertTrue(this.inscripcionDAO.registrar(inscripcion));
    }
    @Test
    public void registrarFalseAlumno(){
        this.inscripcion.setIdAlumno(0);
        this.inscripcion.setIdGrupo(this.idGrupo);
        assertFalse(this.inscripcionDAO.registrar(inscripcion));
    }
    @Test
    public void registrarFalseGrupo(){
        this.inscripcion.setIdAlumno(this.idAlumno);
        this.inscripcion.setIdGrupo(0);
        assertFalse(this.inscripcionDAO.registrar(inscripcion));
    }
}
