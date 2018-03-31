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
public class EgresoDAOEditarEgresoTest {
    private EgresoDAOSql egresoDao;
    private Egreso egreso;
    private Date fechaInicio;
    
    public EgresoDAOEditarEgresoTest() {
    egresoDao = new EgresoDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            fechaInicio = formatofecha.parse(fechaPrueba);
            egreso = new Egreso(3, "1200", "una escoba ",fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EgresoDAOSRegistrarEgresoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarEgresoTrue(){
        boolean registrarEgreso = egresoDao.editarEgreso(egreso);
        assertTrue(registrarEgreso);
    }
    @Test
    public void editarEgresoFalse(){
        Egreso egreso1 = new Egreso(500, "costo", "una escoba ",fechaInicio);
        boolean registrarEgreso = egresoDao.editarEgreso(egreso1);
        assertFalse(registrarEgreso);
        
    }

}