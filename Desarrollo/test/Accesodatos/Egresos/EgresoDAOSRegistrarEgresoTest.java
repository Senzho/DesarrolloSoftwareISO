/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import LogicaNegocio.Egresos.Egreso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EgresoDAOSRegistrarEgresoTest {
    private EgresoDAOSql egresoDao;
    private Egreso egreso;
    private Date fechaInicio;
    
    public EgresoDAOSRegistrarEgresoTest() {
    egresoDao = new EgresoDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            fechaInicio = formatofecha.parse(fechaPrueba);
            egreso = new Egreso(0, "1200", "una escoba ",fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EgresoDAOSRegistrarEgresoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void registrarEgresoTrue(){
        boolean registrarEgreso = egresoDao.registrarEgreso(egreso);
        assertTrue(registrarEgreso);
    }
    @Test
    public void registrarEgresoFalse(){
        Egreso egreso1 = new Egreso(0, "hola", "una escoba ",fechaInicio);
        boolean registrarEgreso = egresoDao.registrarEgreso(egreso1);
        assertFalse(registrarEgreso);
        
    }

}