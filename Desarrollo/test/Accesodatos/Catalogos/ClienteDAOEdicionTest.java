package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Cliente;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ClienteDAOEdicionTest {
    private ClienteDAOSql clienteDAO;
    private Cliente cliente;
    private int id;
    private String correo;
    private String telefono;
    
    public ClienteDAOEdicionTest() {
        this.clienteDAO = new ClienteDAOSql();
        this.cliente = this.clienteDAO.obtenerClientes().get(0);
        this.id = this.cliente.getIdCliente();
        this.correo = this.cliente.getCorreo();
        this.telefono = this.cliente.getTelefono();
    }
    
    @Test
    public void editarClienteTrue(){
        this.cliente.setIdCliente(this.id);
        this.cliente.setCorreo(this.correo);
        this.cliente.setTelefono(this.telefono);
        assertTrue(this.clienteDAO.editarCliente(this.cliente));
    }
    @Test
    public void editarClienteFalse(){
        this.cliente.setIdCliente(0);
        this.cliente.setCorreo(this.correo);
        this.cliente.setTelefono(this.telefono);
        assertFalse(this.clienteDAO.editarCliente(this.cliente));
    }
    @Test
    public void editarClienteFalseCorreo(){
        this.cliente.setIdCliente(0);
        this.cliente.setCorreo("vija.com");
        this.cliente.setTelefono(this.telefono);
        assertFalse(this.clienteDAO.editarCliente(this.cliente));
    }
    @Test
    public void editarClienteFalseTelefono(){
        this.cliente.setIdCliente(0);
        this.cliente.setCorreo(this.correo);
        this.cliente.setTelefono("1");
        assertFalse(this.clienteDAO.editarCliente(this.cliente));
    }
}
