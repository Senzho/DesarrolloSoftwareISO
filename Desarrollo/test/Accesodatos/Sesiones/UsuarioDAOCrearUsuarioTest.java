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
public class UsuarioDAOCrearUsuarioTest {

    private String contraseña = Hasher.hash("1234");
    private String nombre = "Antonio";
    private int idTipoUsuario = 1;
    private int idUsuario = 1;
    private int tipoUsuario = 1;
    private Usuario usuario;
    private UsuarioDAOSql usuarioDao;

    public UsuarioDAOCrearUsuarioTest() {
        usuarioDao = new UsuarioDAOSql();
        usuario = new Usuario(idUsuario, contraseña, idTipoUsuario, nombre, tipoUsuario);
    }

    @Test
    public void crearUsuarioTrue(){
        boolean usuarioGuardado = usuarioDao.crearUsuario(this.usuario);
        assertTrue(usuarioGuardado);
    }
}
