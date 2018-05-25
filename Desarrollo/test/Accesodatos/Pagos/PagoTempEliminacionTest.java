package Accesodatos.Pagos;

import org.junit.Test;
import static org.junit.Assert.*;

public class PagoTempEliminacionTest {
    private PagoTemporalDAOSql pagoDAO;
    private int idPago;
    
    public PagoTempEliminacionTest() {
        this.pagoDAO = new PagoTemporalDAOSql();
        this.idPago = this.pagoDAO.obtenerPagos().get(0).getIdPago();
    }
    
    @Test
    public void eliminarTrue(){
        assertTrue(this.pagoDAO.eliminarPago(this.idPago));
    }
    @Test
    public void eliminarFalse(){
        assertFalse(this.pagoDAO.eliminarPago(0));
    }
}
