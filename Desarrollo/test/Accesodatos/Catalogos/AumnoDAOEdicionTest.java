package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Alumno;
import org.junit.Test;
import static org.junit.Assert.*;

public class AumnoDAOEdicionTest {
    private AlumnoDAOSql alumnoDAO;
    private Alumno alumno;
    private int id;
    private String correo;
    private String telefono;
    
    public AumnoDAOEdicionTest() {
        this.alumnoDAO = new AlumnoDAOSql();
        this.alumno = this.alumnoDAO.obtenerAlumnos().get(0);
        this.id = this.alumno.getIdAlumno();
        this.telefono = this.alumno.getTeléfono();
        this.correo = this.alumno.getCorreo();
    }
    
    @Test
    public void editarAlumnoTrue(){
        this.alumno.setIdAlumno(this.id);
        this.alumno.setCorreo(this.correo);
        this.alumno.setTeléfono(this.telefono);
        assertTrue(this.alumnoDAO.editarAlumno(this.alumno));
    }
    @Test
    public void editarAlumnoFalse(){
        this.alumno.setIdAlumno(0);
        this.alumno.setCorreo(this.correo);
        this.alumno.setTeléfono(this.telefono);
        assertFalse(this.alumnoDAO.editarAlumno(this.alumno));
    }
    @Test
    public void editarAlumnoFalseCorreo(){
        this.alumno.setIdAlumno(this.id);
        this.alumno.setCorreo("kjh");
        this.alumno.setTeléfono(this.telefono);
        assertFalse(this.alumnoDAO.editarAlumno(this.alumno));
    }
    @Test
    public void editarAlumnoFalseTelefono(){
        this.alumno.setIdAlumno(this.id);
        this.alumno.setCorreo(this.correo);
        this.alumno.setTeléfono("2281716s");
        assertFalse(this.alumnoDAO.editarAlumno(this.alumno));
    }
}
