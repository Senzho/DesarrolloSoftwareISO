/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import LogicaNegocio.Egresos.Egreso;
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
public class EgresoDAOObtenerEgresosTest {
    private EgresoDAOSql  egresoDao;
    
    public EgresoDAOObtenerEgresosTest() {
        this.egresoDao = new EgresoDAOSql();
    }
    
    @Test
    public void obtenerGastosExcepcion(){
        try{
            this.egresoDao.obtenerEgresos();
        }catch(Exception excepcion){
            fail();
        }
    }
}