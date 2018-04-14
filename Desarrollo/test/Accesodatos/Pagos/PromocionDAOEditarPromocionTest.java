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
public class PromocionDAOEditarPromocionTest {
    private PromocionDAOSql promocionDao;
    private Promocion promocion;
    
    
    public PromocionDAOEditarPromocionTest() {
        promocionDao = new PromocionDAOSql();
        promocion = new Promocion("promocion de prueba", 1, 1, "promocion de descuento editada", 400);
    }
    @Test
    public void editarPromocionTrueTest(){
        boolean resultado = promocionDao.editarPromocion(promocion);
        assertTrue(resultado);
    }
    @Test
    public void editarPromocionFalseTest(){
        boolean resultado = promocionDao.editarPromocion(new Promocion("promocion de prueba", 14, 0, "promocion de descuento", 20));
        assertFalse(resultado);
    }
}
