package LogicaNegocio.Sesiones;

import Accesodatos.Sesiones.UsuarioDAOSql;
import LogicaNegocio.Catalogos.Profesor;

public class Usuario {
    private int idUsuario;
    private String contraseña;
    private int idTipoUsuario;
    private String nombre;
    private int tipoUsuario;
    private UsuarioDAOSql usuarioDAO;
    
    public Usuario(){
        this.usuarioDAO = new UsuarioDAOSql();
    }
    
    public Usuario(int idUsuario, String contraseña, int idTipoUsuario, String nombre, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.contraseña = contraseña;
        this.idTipoUsuario = idTipoUsuario;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.usuarioDAO = new UsuarioDAOSql();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioDAOSql getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAOSql usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario buscarUsuarioSesion(String nombreUsuario, String contraseña) {
        return this.usuarioDAO.buscarUsuarioSesion(nombreUsuario,contraseña);
    }
    public boolean buscarUsuario(String nombreUsuario) {
        return this.usuarioDAO.buscarUsuario(nombreUsuario);
    }
    public boolean crearUsuario() {
        return usuarioDAO.crearUsuario(this);
    }
    public boolean editarUsuario() {
        return usuarioDAO.editarUsuario(this);
    }
    public Profesor obtenerProfesor(int idProfesor) {
        return usuarioDAO.obtenerProfesor(idProfesor);
    }
    public int obtenerNumeroUsuario(){
        return this.usuarioDAO.obtenerNumeroUsuarios();
    }
}
