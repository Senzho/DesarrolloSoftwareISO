/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Sesiones.Hasher;
import LogicaNegocio.Sesiones.Usuario;
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
public class UsuarioDAOEditarUsuarioTest {

    private UsuarioDAOSql usuarioDao;
    private Usuario usuario;
    private String nombreUsuario = "jose";
    private String contraseña = Hasher.hash("snape");

    public UsuarioDAOEditarUsuarioTest() {
        usuarioDao = new UsuarioDAOSql();
        this.usuario = usuarioDao.buscarUsuarioSesion(nombreUsuario, contraseña);
    }

    @Test
    public void editarUsuarioIsTrue() {
        this.usuario.setNombre("soy una prueba");
        this.usuario.setContraseña(Hasher.hash("123456"));
        boolean editado = usuarioDao.editarUsuario(usuario);
        assertTrue(editado);
    }

}
