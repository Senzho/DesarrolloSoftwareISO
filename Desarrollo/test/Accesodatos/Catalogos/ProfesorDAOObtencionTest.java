package Accesodatos.Catalogos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProfesorDAOObtencionTest {
    private ProfesorDAOSql profesorDAO;
    
    public ProfesorDAOObtencionTest() {
        this.profesorDAO = new ProfesorDAOSql();
    }
    
    @Test
    public void obtenerProfesoresExcepcion(){
        try{
            this.profesorDAO.obtenerProfesores();
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void obtenerProfesoresNombreExcepcion(){
        try{
            this.profesorDAO.obtenerProfesores("angel");
        }catch(Exception excepcion){
            fail();
        }
    }
}
