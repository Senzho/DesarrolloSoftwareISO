package Accesodatos.Sesiones;

import LogicaNegocio.Sesiones.Profesor;
import LogicaNegocio.Sesiones.Usuario;

public interface UsuarioDAO {
    public Usuario buscarUsuarioSesion(String nombreUsuario, String contraseña);
    public boolean buscarUsuario(String nombreUsuario);
    public boolean crearUsuario(Usuario usuario);
    public boolean editarUsuario(Usuario usuario);
    public Profesor obtenerProfesor(int idProfesor);
}
