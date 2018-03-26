/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Sesiones.Hasher;
import LogicaNegocio.Sesiones.Usuario;
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
public class UsuarioDAOInicioSesionTest {

    private UsuarioDAOSql usuarioDao;
    private Usuario usuario;
    private String nombreUsuario = "jose";
    private String contraseña = Hasher.hash("snape");

    public UsuarioDAOInicioSesionTest() {
        this.usuarioDao = new UsuarioDAOSql();
        this.usuario = usuarioDao.buscarUsuarioSesion(nombreUsuario, contraseña);
    }

    @Test
    public void iniciarSesionUserExist() {
        Usuario usuarioDos = usuarioDao.buscarUsuarioSesion(nombreUsuario, contraseña);
        assertNotNull(usuarioDos);
    }

    @Test
    public void iniciarSesionUserNoExist() {
        Usuario usuarioDos  = usuarioDao.buscarUsuarioSesion("Mario", Hasher.hash("123"));
        assertNull(usuarioDos);
    }
    @Test
    public void iniciarSesionUserSameId(){
         Usuario usuarioDos  = usuarioDao.buscarUsuarioSesion(nombreUsuario, contraseña);
         assertEquals(usuarioDos.getIdUsuario(), usuario.getIdUsuario());
    }
    @Test
    public void iniciarSesionNotUserSameId(){
         Usuario usuarioDos  = usuarioDao.buscarUsuarioSesion("Gabriela", Hasher.hash("nemesis"));
         assertNotEquals(usuarioDos.getIdUsuario(), usuario.getIdUsuario());
    }
}
