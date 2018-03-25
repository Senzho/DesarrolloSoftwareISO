package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Profesor;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfesorDAOEdicionTest {
    private ProfesorDAOSql profesorDAO;
    private Profesor profesor;
    private int id;
    private String correo;
    private String telefono;
    private String monto;
    
    public ProfesorDAOEdicionTest() {
        this.profesorDAO = new ProfesorDAOSql();
        this.profesor = this.profesorDAO.obtenerProfesores().get(0);
        this.id = this.profesor.getIdProfesor();
        this.telefono = this.profesor.getTelefono();
        this.correo = this.profesor.getCorreo();
        this.monto = this.profesor.getMonto();
    }
    
    @Test
    public void editarProfesorTrue(){
        this.profesor.setIdProfesor(this.id);
        this.profesor.setCorreo(this.correo);
        this.profesor.setTelefono(this.telefono);
        this.profesor.setMonto(this.monto);
        assertTrue(this.profesorDAO.editarProfesor(this.profesor));
    }
    @Test
    public void editarProfesorFalse(){
        this.profesor.setIdProfesor(0);
        this.profesor.setCorreo(this.correo);
        this.profesor.setTelefono(this.telefono);
        this.profesor.setMonto(this.monto);
        assertFalse(this.profesorDAO.editarProfesor(this.profesor));
    }
    @Test
    public void editarProfesorFalseCorreo(){
        this.profesor.setIdProfesor(this.id);
        this.profesor.setCorreo("s");
        this.profesor.setTelefono(this.telefono);
        this.profesor.setMonto(this.monto);
        assertFalse(this.profesorDAO.editarProfesor(this.profesor));
    }
    @Test
    public void editarProfesorFalseTelefono(){
        this.profesor.setIdProfesor(this.id);
        this.profesor.setCorreo(this.correo);
        this.profesor.setTelefono("876");
        this.profesor.setMonto(this.monto);
        assertFalse(this.profesorDAO.editarProfesor(this.profesor));
    }
    @Test
    public void editarProfesorFalseMonto(){
        this.profesor.setIdProfesor(this.id);
        this.profesor.setCorreo(this.correo);
        this.profesor.setTelefono(this.telefono);
        this.profesor.setMonto("1");
        assertFalse(this.profesorDAO.editarProfesor(this.profesor));
    }
}

