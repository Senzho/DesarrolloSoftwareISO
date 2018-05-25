package Accesodatos.Pagos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Pagos.PagoTemporal;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagoTempObtencionCreacionTest {
    private PagoTemporalDAOSql pagoDAO;
    private PagoTemporal pago;
    private Alumno alumno;
    private Profesor profesor;
    private String monto;
    private int idAlumno;
    private int idProfesor;
    
    public PagoTempObtencionCreacionTest() {
        this.pagoDAO = new PagoTemporalDAOSql();
        this.pago = new PagoTemporal();
        this.pago.setIdPago(0);
        this.pago.setTipoPago(1);
        this.pago.setFecha(new Date());
        this.profesor = new ProfesorDAOSql().obtenerProfesores().get(0);
        Grupo grupo = new GrupoDAOSql().obtenerGruposProfesor(this.profesor.getIdProfesor()).get(0);
        this.alumno = new AlumnoDAOSql().obtenerAlumnos(grupo.getId()).get(0);
        this.idAlumno = this.alumno.getIdAlumno();
        this.idProfesor = this.profesor.getIdProfesor();
        this.pago.setAlumno(this.alumno);
        this.pago.setProfesor(this.profesor);
        this.monto = "2500";
    }
    
    @Test
    public void obtenerNoException(){
        try{
            this.pagoDAO.obtenerPagos();
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void guardarTrue(){
        this.pago.getAlumno().setIdAlumno(this.idAlumno);
        this.pago.getProfesor().setIdProfesor(this.idProfesor);
        this.pago.setMonto(this.monto);
        assertTrue(this.pago.registrarPago());
    }
    @Test
    public void guardarFalseMonto(){
        this.pago.getAlumno().setIdAlumno(this.idAlumno);
        this.pago.getProfesor().setIdProfesor(this.idProfesor);
        this.pago.setMonto("");
        assertFalse(this.pago.registrarPago());
    }
    @Test
    public void guardarFalseAlumno(){
        this.pago.getAlumno().setIdAlumno(0);
        this.pago.getProfesor().setIdProfesor(this.idProfesor);
        this.pago.setMonto(this.monto);
        assertFalse(this.pago.registrarPago());
    }
    @Test
    public void guardarFalseProfesor(){
        this.pago.getAlumno().setIdAlumno(this.idAlumno);
        this.pago.getProfesor().setIdProfesor(0);
        this.pago.setMonto(this.monto);
        assertFalse(this.pago.registrarPago());
    }
}
