/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
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
public class UsuarioDAOBuscarUsuarioTest {
    private UsuarioDAOSql usuarioDao;
    private Usuario usuario;
    private String nombreUsuario = "jose";
    
    public UsuarioDAOBuscarUsuarioTest() {
        usuarioDao = new UsuarioDAOSql();
    }
    @Test
    public void buscarUsuarioNoExist(){
        boolean usuarioExistente = usuarioDao.buscarUsuario("mario");
        assertFalse(usuarioExistente);
    }
    @Test
    public void buscarUsuarioExist(){
        boolean usuarioExistente = usuarioDao.buscarUsuario(nombreUsuario);
        assertTrue(usuarioExistente);
    }
    
}
