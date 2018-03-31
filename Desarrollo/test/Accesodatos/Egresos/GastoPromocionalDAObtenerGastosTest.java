/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import LogicaNegocio.Egresos.GastoPromocional;
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
public class GastoPromocionalDAObtenerGastosTest  {
    private GastoPromocionalDAOSql gastoDao;
    
    public GastoPromocionalDAObtenerGastosTest() {
        this.gastoDao = new GastoPromocionalDAOSql();
    }
    
    @Test
    public void obtenerGastosExcepcion(){
        try{
            this.gastoDao.obtenerGastos();
        }catch(Exception excepcion){
            fail();
        }
    }
}
