package Accesodatos.Catalogos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDAOObtencionTest {
    private ClienteDAOSql clienteDAO;
    
    public ClienteDAOObtencionTest() {
        this.clienteDAO = new ClienteDAOSql();
    }
    
    @Test
    public void obtenerClientesExcepcion(){
        try{
            this.clienteDAO.obtenerClientes();
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void obtenerClientesNombreExcepcion(){
        try{
            this.clienteDAO.obtenerClientes("cli");
        }catch(Exception excepcion){
            fail();
        }
    }
}
