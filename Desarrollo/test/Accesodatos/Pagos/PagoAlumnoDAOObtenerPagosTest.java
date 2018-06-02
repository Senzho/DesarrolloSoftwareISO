package Accesodatos.Pagos;

import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.Promocion;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagoAlumnoDAOObtenerPagosTest {
    private PagoAlumnoDAOSql pagoAlumnoDao;
    private PagoAlumno pagoAlumno;
    private Profesor profesor;
    private Alumno alumno;
    private Promocion promocion;
    private int idGrupo;

    public PagoAlumnoDAOObtenerPagosTest() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
        profesor = new Profesor().obtenerProfesores().get(0);
        alumno = new Alumno().obtenerAlumnos().get(0);
        promocion = new Promocion().obtenerPromociones(profesor.getIdProfesor()).get(0);
        this.idGrupo = new Grupo().obtenerGrupos().get(0).getId();
        pagoAlumno = new PagoAlumno(new Date(), 0, "500", 1, profesor.getIdProfesor(), alumno.getIdAlumno(), this.idGrupo);
    }

    @Test
    public void obtenerPagosExcepcionTest() {
        try {
            this.pagoAlumnoDao.obtenerPagos(alumno.getIdAlumno(),profesor.getIdProfesor());
        } catch (Exception excepcion) {
            fail();
        }
    }
    @Test(expected = NullPointerException.class)
    public void obtenerPagosTest() {
        this.pagoAlumnoDao.obtenerPagos(0,profesor.getIdProfesor());
    }
    @Test 
    public void registrarPagoTest(){
        boolean registrado = pagoAlumnoDao.registrarPago(pagoAlumno, alumno.getIdAlumno(), promocion.getIdPromocion());
        assertTrue(registrado);
    }
    @Test 
    public void registrarPagoMontoInvalidoTest(){
        PagoAlumno pago = new PagoAlumno(new Date(), 0, "no es un pago", 1, profesor.getIdProfesor(), alumno.getIdAlumno(), this.idGrupo);
        boolean registrado = pagoAlumnoDao.registrarPago(pago, alumno.getIdAlumno(), promocion.getIdPromocion());
        assertFalse(registrado);
    }
    @Test
    public void registrarPagoPromocionTest(){
        boolean registrado = pagoAlumnoDao.registrarPago(pagoAlumno, alumno.getIdAlumno(), promocion.getIdPromocion());
        assertTrue(registrado);
    }  
    @Test
    public void obtenerProfesoresExceptionTest(){
        try {
            pagoAlumnoDao.obtenerPagos(profesor.getIdProfesor());
        } catch (Exception excepcion) {
            fail();
        }
    }
    @Test
    public void obtenerPrimeraInscripcionTestNotNull(){
        int idGrupo = new GrupoDAOSql().obtenerGruposAlumno(this.alumno.getIdAlumno()).get(0).getId();
        assertNotNull(this.pagoAlumnoDao.obtenerPrimeraInscripcion(this.alumno.getIdAlumno(), idGrupo));
    }
    @Test
    public void obtenerPrimeraInscripcionTestNull(){
        assertNull(this.pagoAlumnoDao.obtenerPrimeraInscripcion(this.alumno.getIdAlumno(), 0));
    }
}
