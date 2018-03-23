package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Cliente;
import java.util.List;

public interface ClienteDAO {
    public boolean registrarCliente(Cliente cliente);
    public boolean editarCliente(Cliente cliente);
    public List<Cliente> obtenerClientes();
    public List<Cliente> obtenerClientes(String nombre);
}
