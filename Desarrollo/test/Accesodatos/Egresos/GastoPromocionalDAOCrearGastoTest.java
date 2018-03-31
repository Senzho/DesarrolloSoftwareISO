/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import LogicaNegocio.Egresos.GastoPromocional;
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
public class GastoPromocionalDAOCrearGastoTest {
    
     private GastoPromocionalDAOSql gastoPromocionalDao;
    private GastoPromocional gastoPromocional;

    public GastoPromocionalDAOCrearGastoTest() {
        gastoPromocionalDao = new GastoPromocionalDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            Date fechaInicio = formatofecha.parse(fechaPrueba);
            Date fechaFin = formatofecha.parse(fechaPrueba);
            gastoPromocional = new GastoPromocional(0, "Creacion de gasto", fechaInicio,fechaFin, "4000", "www.eminus.mx");
        } catch (ParseException ex) {
            Logger.getLogger(GastoPromocionalDAOEditarGastoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void agregarGastoTrue(){
        boolean editarGasto = gastoPromocionalDao.registrarGasto(gastoPromocional);
        assertTrue(editarGasto);
    }
    @Test
    public void agregarGastoFalse(){
        boolean editarGasto = gastoPromocionalDao.registrarGasto(new GastoPromocional());
        assertFalse(editarGasto);
    }
    
}
