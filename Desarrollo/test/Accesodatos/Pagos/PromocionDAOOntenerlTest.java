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
public class PromocionDAOOntenerlTest {
    private PromocionDAOSql promocionDao;
    private int idPromocion;
    private Promocion promocion;
    public PromocionDAOOntenerlTest() {
        idPromocion = 1;
        promocionDao = new PromocionDAOSql();
    }
    @Test
    public void promocionValidaTest(){
        promocion = promocionDao.obtenerPromocion(idPromocion);
        assertNotNull(promocion);
    }    
    @Test (expected = NullPointerException.class)
    public void promocionInvalidaTest(){
        promocion = promocionDao.obtenerPromocion(0);
        assertNull(promocion);
    }

}
