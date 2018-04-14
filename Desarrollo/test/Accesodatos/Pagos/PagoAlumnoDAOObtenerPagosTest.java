/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import Accesodatos.Controladores.PromocionJpaController;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Pagos.Promocion;
import java.util.Date;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Desktop
 */
public class PagoAlumnoDAOObtenerPagosTest {

    private PagoAlumnoDAOSql pagoAlumnoDao;
    private PagoAlumno pagoAlumno;
    private Promocion promocion;

    public PagoAlumnoDAOObtenerPagosTest() {
        pagoAlumnoDao = new PagoAlumnoDAOSql();
        pagoAlumno = new PagoAlumno(new Date(), 0, "500", 1);
    }

    @Test
    public void obtenerPagosExcepcionTest() {
        try {
            this.pagoAlumnoDao.obtenerPagos(1);
        } catch (Exception excepcion) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void obtenerPagosTest() {
        this.pagoAlumnoDao.obtenerPagos(0);
    }
    @Test 
    public void registrarPagoTest(){
        boolean registrado = pagoAlumnoDao.registrarPago(pagoAlumno, 1, 1);
        assertTrue(registrado);
    }
    @Test 
    public void registrarPagoMontoInvalidoTest(){
        PagoAlumno pago = new PagoAlumno(new Date(), 0, "no es un pago", 1);
        boolean registrado = pagoAlumnoDao.registrarPago(pago, 1, 1);
        assertFalse(registrado);
    }
    @Test
    public void registrarPagoPromocionTest(){
        boolean registrado = pagoAlumnoDao.registrarPago(pagoAlumno, 1, 0);
        assertTrue(registrado);
    }
    
}
