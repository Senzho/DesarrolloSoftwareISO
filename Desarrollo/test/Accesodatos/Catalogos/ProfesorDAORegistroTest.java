package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Profesor;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfesorDAORegistroTest {
    private ProfesorDAOSql profesorDAO;
    private Profesor profesor;
    private String correo;
    private String telefono;
    private String monto;
    
    public ProfesorDAORegistroTest() {
        this.profesorDAO = new ProfesorDAOSql();
        this.correo = "profe@sor.test";
        this.telefono = "2281916654";
        this.monto = "1500";
        this.profesor = new Profesor(0, "Profesor de prueba", this.correo, this.telefono, "Dirección de prueba", this.monto, new Date());
        this.profesor.setTipoPago(true);
        this.profesor.setEstado(false);
    }
    @Test
    public void registrarProfesorTrue(){
        this.profesor.setCorreo(this.correo);
        this.profesor.setTelefono(this.telefono);
        this.profesor.setMonto(this.monto);
        assertTrue(this.profesorDAO.registrarProfesor(this.profesor));
    }
    @Test
    public void registrarProfesorFalseCorreo(){
        this.profesor.setCorreo("lkjhlkj");
        assertFalse(this.profesorDAO.registrarProfesor(this.profesor));
    }
    @Test
    public void registrarProfesorFalseTelefono(){
        this.profesor.setTelefono("lkjhlkj");
        assertFalse(this.profesorDAO.registrarProfesor(this.profesor));
    }
    @Test
    public void registrarProfesorFalseMonto(){
        this.profesor.setMonto("1iuy");
        assertFalse(this.profesorDAO.registrarProfesor(this.profesor));
    }
}
