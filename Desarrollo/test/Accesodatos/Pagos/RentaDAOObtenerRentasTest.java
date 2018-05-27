package Accesodatos.Pagos;

import LogicaNegocio.Catalogos.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentaDAOObtenerRentasTest {
    private Cliente cliente;
    private RentaDAOSql rentaDao;
    
    public RentaDAOObtenerRentasTest() {
        cliente = new Cliente().obtenerClientes().get(0);
        rentaDao = new RentaDAOSql();
    }
    
    @Test
    public void obtenerPagosTest(){
        try {
            this.rentaDao.obtenerRentas(cliente.getIdCliente());
        } catch (Exception excepcion) {
            fail();
        }
    }
     @Test
    public void obtenerPagosExceptionTest() {
        cliente = new Cliente().obtenerClientes().get(1);
         assertNull(rentaDao.obtenerRentas(cliente.getIdCliente()));
    }
    @Test
    public void obtenerRentasNoException(){
        try{
            this.rentaDao.obtenerRentas();
        }catch(Exception excepcion){
            fail();
        }
    }
    
}
