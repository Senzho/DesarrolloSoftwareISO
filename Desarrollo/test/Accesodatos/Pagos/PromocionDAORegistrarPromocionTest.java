/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Pagos.Promocion;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Desktop
 */
public class PromocionDAORegistrarPromocionTest {
    private PromocionDAOSql promocionDao;
    private Promocion promocion;
    
    public PromocionDAORegistrarPromocionTest() {
        promocionDao = new PromocionDAOSql();
        promocion = new Promocion("promocion de prueba", 0, 1, "promocion de descuento", 20);
    }
    
    @Test 
    public void registrarPromocionTest(){
        boolean registrar = promocionDao.registrarPromocion(promocion);
        assertTrue(registrar);
    }
    @Test
    public void noRegistrarPromocionTest(){
        boolean registrar = promocionDao.registrarPromocion(new Promocion("promocion de prueba", 0, -1, "promocion de descuento 2", 20));
        assertFalse(registrar);
    }
     @Test
    public void obtenerPromocionesException() {
        try {
            this.promocionDao.obtenerPromociones(1);
        } catch (Exception excepcion) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void obtenerPromocionesTest() {
        this.promocionDao.obtenerPromociones(0);
    }
}
