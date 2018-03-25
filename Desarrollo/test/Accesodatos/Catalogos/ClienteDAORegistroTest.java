package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Cliente;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDAORegistroTest {
    private ClienteDAOSql clienteDAO;
    private Cliente cliente;
    private String correo;
    private String telefono;
    
    public ClienteDAORegistroTest() {
        this.clienteDAO = new ClienteDAOSql();
        this.correo = "cliente@prueba.clase";
        this.telefono = "2289365356";
        this.cliente = new Cliente(0, "Cliente de prueba", this.correo, "22893653", "Direccion de prueba", new Date());
    }
    
    @Test
    public void registrarClienteTrue(){
        this.cliente.setCorreo(this.correo);
        this.cliente.setTelefono(this.telefono);
        assertTrue(this.clienteDAO.registrarCliente(this.cliente));
    }
    @Test
    public void registrarClienteFalseCorreo(){
        this.cliente.setCorreo("@com.gmail");
        assertFalse(this.clienteDAO.registrarCliente(this.cliente));
    }
    @Test
    public void registrarClienteFalseTelefono(){
        this.cliente.setTelefono("228262z221");
        assertFalse(this.clienteDAO.registrarCliente(this.cliente));
    }
}
