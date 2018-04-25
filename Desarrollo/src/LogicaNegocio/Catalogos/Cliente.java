package LogicaNegocio.Catalogos;

import Accesodatos.Catalogos.ClienteDAOSql;
import java.util.Date;
import java.util.List;

public class Cliente implements Comparable<Cliente>{
    private int idCliente;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private Date fecha;
    
    private ClienteDAOSql clienteDAO;

    public Cliente(){
        this.clienteDAO = new ClienteDAOSql();
    }
    public Cliente(int idCliente, String nombre, String correo, String telefono, String direccion, Date fecha) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha = fecha;
        this.clienteDAO = new ClienteDAOSql();
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public int compareTo(Cliente cliente) {
        return this.nombre.compareTo(cliente.getNombre());
    }
    
    public boolean registrarCliente(){
        return this.clienteDAO.registrarCliente(this);
    }
    public boolean editarCliente(){
        return this.clienteDAO.editarCliente(this);
    }
    public List<Cliente> obtenerClientes(){
        return this.clienteDAO.obtenerClientes();
    }
    public List<Cliente> obtenerClientes(String nombre){
        return this.clienteDAO.obtenerClientes(nombre);
    }
}
