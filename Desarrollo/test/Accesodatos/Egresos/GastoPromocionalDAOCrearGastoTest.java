package Accesodatos.Egresos;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Egresos.GastoPromocional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class GastoPromocionalDAOCrearGastoTest {
    private GastoPromocionalDAOSql gastoPromocionalDao;
    private GastoPromocional gastoPromocional;
    private Profesor profesor;

    public GastoPromocionalDAOCrearGastoTest() {
        this.profesor = new Profesor();
        gastoPromocionalDao = new GastoPromocionalDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            Date fechaInicio = formatofecha.parse(fechaPrueba);
            Date fechaFin = formatofecha.parse(fechaPrueba);
            gastoPromocional = new GastoPromocional(0, "Creacion de gasto", fechaInicio,fechaFin, "4000", "www.eminus.mx", this.profesor.obtenerProfesores().get(0).getIdProfesor());
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
