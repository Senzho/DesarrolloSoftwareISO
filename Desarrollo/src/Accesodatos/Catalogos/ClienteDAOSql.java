package Accesodatos.Catalogos;

import Accesodatos.Controladores.ClienteJpaController;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Catalogos.OperacionesString;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

public class ClienteDAOSql implements ClienteDAO{
    public static Cliente obtenerEntidad(Accesodatos.Entidades.Cliente clienteJpa){
        Cliente cliente = new Cliente();
        cliente.setCorreo(clienteJpa.getCorreo());
        cliente.setFecha(clienteJpa.getFecha());
        cliente.setIdCliente(clienteJpa.getIdCliente());
        cliente.setNombre(clienteJpa.getNombre());
        cliente.setTelefono(clienteJpa.getTelefono());
        cliente.setDireccion(clienteJpa.getDireccion());
        return cliente;
    }
    private Accesodatos.Entidades.Cliente obtenerEntidad(Cliente cliente){
        Accesodatos.Entidades.Cliente clienteJpa = new Accesodatos.Entidades.Cliente();
        clienteJpa.setCorreo(cliente.getCorreo());
        clienteJpa.setFecha(cliente.getFecha());
        clienteJpa.setIdCliente(cliente.getIdCliente());
        clienteJpa.setNombre(cliente.getNombre());
        clienteJpa.setTelefono(cliente.getTelefono());
        clienteJpa.setDireccion(cliente.getDireccion());
        return clienteJpa;
    }
    
    public ClienteDAOSql(){
        
    }
    
    @Override
    public boolean registrarCliente(Cliente cliente) {
        boolean registrado = false;
        if (OperacionesString.emailValido(cliente.getCorreo()) && OperacionesString.telefonoValido(cliente.getTelefono())){
            ClienteJpaController controller = new ClienteJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            Accesodatos.Entidades.Cliente clienteJpa = this.obtenerEntidad(cliente);
            try{
                controller.create(clienteJpa);
                cliente.setIdCliente(clienteJpa.getIdCliente());
                registrado = true;
            }catch(Exception excepcion){
                Logger.getLogger(ClienteDAOSql.class.getName()).log(Level.SEVERE, null, excepcion);
            }
        } 
        return registrado;
    }
    @Override
    public boolean editarCliente(Cliente cliente) {
        boolean editado = false;
        if (OperacionesString.emailValido(cliente.getCorreo()) && OperacionesString.telefonoValido(cliente.getTelefono())){
            ClienteJpaController controller = new ClienteJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
            try {
                Accesodatos.Entidades.Cliente clienteJpa = controller.findCliente(cliente.getIdCliente());
                clienteJpa.setCorreo(cliente.getCorreo());
                clienteJpa.setNombre(cliente.getNombre());
                clienteJpa.setTelefono(cliente.getTelefono());
                clienteJpa.setDireccion(cliente.getDireccion());
                controller.edit(clienteJpa);
                editado = true;
            } catch (Exception ex) {
                Logger.getLogger(ClienteDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return editado;
    }
    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList();
        ClienteJpaController controller = new ClienteJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        controller.findClienteEntities().forEach((clienteJpa) -> {
            clientes.add(ClienteDAOSql.obtenerEntidad(clienteJpa));
        });
        return clientes;
    }
    @Override
    public List<Cliente> obtenerClientes(String nombre) {
        List<Cliente> clientes = new ArrayList();
        for (Cliente cliente : this.obtenerClientes()){
            if (OperacionesString.coincide(nombre, cliente.getNombre())){
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
