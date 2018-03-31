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
public class GastoPromocionalDAOEditarGastoTest {

    private GastoPromocionalDAOSql gastoPromocionalDao;
    private GastoPromocional gastoPromocional;

    public GastoPromocionalDAOEditarGastoTest() {
        gastoPromocionalDao = new GastoPromocionalDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            Date fechaInicio = formatofecha.parse(fechaPrueba);
            Date fechaFin = formatofecha.parse(fechaPrueba);
            gastoPromocional = new GastoPromocional(1, "modulo de prueba", fechaInicio,fechaFin, "1200", "www.uv.mx");
        } catch (ParseException ex) {
            Logger.getLogger(GastoPromocionalDAOEditarGastoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarGastoTrue(){
        boolean editarGasto = gastoPromocionalDao.editarGasto(gastoPromocional);
        assertTrue(editarGasto);
    }
    @Test
    public void editarGastoFalse(){
        boolean editarGasto = gastoPromocionalDao.editarGasto(new GastoPromocional());
        assertFalse(editarGasto);
    }
}
