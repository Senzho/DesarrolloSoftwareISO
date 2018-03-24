package Accesodatos.Sesiones;

import Accesodatos.Controladores.UsuarioJpaController;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Sesiones.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioDAOSql implements UsuarioDAO {

    public UsuarioDAOSql() {
    }

    @Override
    public Usuario buscarUsuarioSesion(String nombreUsuario, String contraseña) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Usuario.FindUserLogin");
        query.setParameter("nombre", nombreUsuario);
        query.setParameter("contrasena", contraseña);
        Usuario usuario = null;
        try {
            Accesodatos.Entidades.Usuario usuarioJpa = (Accesodatos.Entidades.Usuario) query.getSingleResult();
            if (usuarioJpa != null) {
                usuario = new Usuario();
                usuario.setNombre(usuarioJpa.getNombre());
                usuario.setContraseña(usuarioJpa.getContrasena());
                usuario.setIdTipoUsuario(usuarioJpa.getIdTipoUsuario());
                usuario.setTipoUsuario(usuarioJpa.getTipoUsuario());
                usuario.setIdUsuario(usuarioJpa.getIdUsuario());
            }
        } catch (NoResultException excepcion) {
            excepcion.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean buscarUsuario(String nombreUsuario) {
        boolean usuarioEncontrado = false;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Usuario.findByNombre");
        query.setParameter("nombre", nombreUsuario);
        try {
            Accesodatos.Entidades.Usuario usuarioJpa = (Accesodatos.Entidades.Usuario) query.getSingleResult();
            usuarioEncontrado = true;
        } catch (NoResultException excepcion) {
            excepcion.printStackTrace();
        }
        return usuarioEncontrado;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        boolean registrado = false;
        UsuarioJpaController usuarioController = new UsuarioJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Usuario usuarioJpa = new Accesodatos.Entidades.Usuario();
        usuarioJpa.setContrasena(usuario.getContraseña());
        usuarioJpa.setIdTipoUsuario(usuario.getIdTipoUsuario());
        usuarioJpa.setIdUsuario(usuario.getIdUsuario());
        usuarioJpa.setNombre(usuario.getNombre());
        usuarioJpa.setTipoUsuario(usuario.getTipoUsuario());
        try {
            usuarioController.create(usuarioJpa);
            registrado = true;
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return registrado;
    }

    @Override
    public boolean editarUsuario(Usuario usuario) {
        boolean usuarioEditado = false;
        UsuarioJpaController usuarioController = new UsuarioJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Usuario usuarioJpa = new Accesodatos.Entidades.Usuario();
        usuarioJpa.setContrasena(usuario.getContraseña());
        usuarioJpa.setIdTipoUsuario(usuario.getIdTipoUsuario());
        usuarioJpa.setIdUsuario(usuario.getIdTipoUsuario());
        usuarioJpa.setNombre(usuario.getNombre());
        usuarioJpa.setTipoUsuario(usuario.getTipoUsuario());
        try {
            usuarioController.edit(usuarioJpa);
            usuarioEditado = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioEditado;
    }

    @Override
    public Profesor obtenerProfesor(int idProfesor) {
        Profesor profesor = null;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Profesor.findByIdProfesor");
        query.setParameter("idProfesor", idProfesor);
        Accesodatos.Entidades.Profesor profesorJpa = (Accesodatos.Entidades.Profesor) query.getSingleResult();
        if (profesorJpa != null) {
            profesor = new Profesor(profesorJpa.getIdProfesor(), profesorJpa.getNombre(), profesorJpa.getCorreo(),
                    profesorJpa.getTelefono(), profesorJpa.getDireccion(), profesorJpa.getMonto(), profesorJpa.getFecha());
        }
        return profesor;
    }

}
