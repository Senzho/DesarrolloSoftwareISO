package Accesodatos.Catalogos;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlumnoDAOObtencionTest {
    private AlumnoDAOSql alumnoDAO;
    
    public AlumnoDAOObtencionTest() {
        this.alumnoDAO = new AlumnoDAOSql();
    }
    
    @Test
    public void obtenerAlumnosExcepcion(){
        try{
            this.alumnoDAO.obtenerAlumnos();
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void obtenerAlumnosNombreExcepcion(){
        try{
            this.alumnoDAO.obtenerAlumnos("victor");
        }catch(Exception excepcion){
            fail();
        }
    }
}
