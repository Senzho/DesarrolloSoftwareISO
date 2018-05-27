package Accesodatos.Pagos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import LogicaNegocio.Pagos.PagoProfesor;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagoProfesorDAOTest {
    private PagoProfesorDAOSql pagoProfesorDAO;
    private PagoProfesor pagoProfesor;
    private String monto;
    
    public PagoProfesorDAOTest() {
        this.pagoProfesorDAO = new PagoProfesorDAOSql();
        this.monto = "900.5";
        int id = new ProfesorDAOSql().obtenerProfesores().get(0).getIdProfesor();
        this.pagoProfesor = new PagoProfesor(0, true, this.monto, new Date(), id);
    }
    
    @Test
    public void obtenerPagosExcepcionTest(){
        try{
            this.pagoProfesorDAO.obtenerPagos(1);
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test (expected=NullPointerException.class)
    public void obtenerPagosExcepcionSiTest(){
        this.pagoProfesorDAO.obtenerPagos(-1);
    }
    @Test
    public void registrarPagoTrue(){
        this.pagoProfesor.setMonto(this.monto);
        assertTrue(this.pagoProfesorDAO.registrarPago(this.pagoProfesor, 1));
    }
    @Test
    public void registrarPagoFalseMonto(){
        this.pagoProfesor.setMonto("3t");
        assertFalse(this.pagoProfesorDAO.registrarPago(this.pagoProfesor, 1));
    }
    @Test
    public void registrarPagoFalseId(){
        this.pagoProfesor.setMonto(this.monto);
        assertFalse(this.pagoProfesorDAO.registrarPago(this.pagoProfesor, -1));
    }
}
