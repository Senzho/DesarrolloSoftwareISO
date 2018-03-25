package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Alumno;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlumnoDAORegistroTest {
    private AlumnoDAOSql alumnoDAO;
    private Alumno alumno;
    private String correo;
    private String telefono;
    
    public AlumnoDAORegistroTest() {
        this.alumnoDAO = new AlumnoDAOSql();
        this.correo = "alumno@prueba.clase";
        this.telefono = "2281917762";
        this.alumno = new Alumno(0, "Alumno de prueba", this.correo, this.telefono, "Dirección de prueba", new Date(), false);
    }
    
    @Test
    public void registrarAlumnoTrue(){
        this.alumno.setCorreo(this.correo);
        this.alumno.setTeléfono(this.telefono);
        assertTrue(this.alumnoDAO.registrarAlumno(this.alumno));
    }
    @Test
    public void registrarAlumnoFalseCorreo(){
        this.alumno.setCorreo("@com.gmail");
        assertFalse(this.alumnoDAO.registrarAlumno(this.alumno));
    }
    @Test
    public void registrarAlumnoFalseTelefono(){
        this.alumno.setTeléfono("228262z221");
        assertFalse(this.alumnoDAO.registrarAlumno(this.alumno));
    }
}
