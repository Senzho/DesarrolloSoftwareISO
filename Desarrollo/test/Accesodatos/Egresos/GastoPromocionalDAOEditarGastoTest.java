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

public class GastoPromocionalDAOEditarGastoTest {
    private GastoPromocionalDAOSql gastoPromocionalDao;
    private GastoPromocional gastoPromocional;
    private Profesor profesor;

    public GastoPromocionalDAOEditarGastoTest() {
        this.profesor = new Profesor();
        gastoPromocionalDao = new GastoPromocionalDAOSql();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPrueba = "2012-12-12";
        try {
            Date fechaInicio = formatofecha.parse(fechaPrueba);
            Date fechaFin = formatofecha.parse(fechaPrueba);
            gastoPromocional = new GastoPromocional(1, "modulo de prueba", fechaInicio,fechaFin, "1200", "www.uv.mx", this.profesor.obtenerProfesores().get(0).getIdProfesor());
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
