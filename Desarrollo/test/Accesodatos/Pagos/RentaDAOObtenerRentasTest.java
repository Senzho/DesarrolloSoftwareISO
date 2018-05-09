/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Pagos.Renta;
import java.util.List;
import javax.persistence.NoResultException;
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
    
}
