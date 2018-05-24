/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.Promocion;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Desktop
 */
public class PagoAlumnoDAOObtenerPagosTest {

    private PagoAlumnoDAOSql pagoAlumnoDao;
    private PagoAlumno pagoAlumno;
    private Profesor profesor;
    private Alumno alumno;
    private Promocion promocion;

    public PagoAlumnoDAOObtenerPagosTest() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
        profesor = new Profesor().obtenerProfesores().get(0);
        alumno = new Alumno().obtenerAlumnos().get(0);
        promocion = new Promocion().obtenerPromociones(profesor.getIdProfesor()).get(0);
        pagoAlumno = new PagoAlumno(new Date(), 0, "500", 1, profesor.getIdProfesor(), alumno.getIdAlumno());
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
        PagoAlumno pago = new PagoAlumno(new Date(), 0, "no es un pago", 1, profesor.getIdProfesor(), alumno.getIdAlumno());
        boolean registrado = pagoAlumnoDao.registrarPago(pago, alumno.getIdAlumno(), promocion.getIdPromocion());
        assertFalse(registrado);
    }
    @Test
    public void registrarPagoPromocionTest(){
        boolean registrado = pagoAlumnoDao.registrarPago(pagoAlumno, alumno.getIdAlumno(), promocion.getIdPromocion());
        assertTrue(registrado);
    }  
}
