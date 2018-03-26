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
public class UsuarioDAOObtenerProfesorTest {

    private UsuarioDAOSql usuarioDao;
    private Usuario usuario;
    private String nombre = "Gabriela";
    private String contraseña = Hasher.hash("nemesis");

    public UsuarioDAOObtenerProfesorTest() {
        usuarioDao = new UsuarioDAOSql();
        usuario = usuarioDao.buscarUsuarioSesion(nombre, contraseña);
    }
    @Test
    public void obtenerProfesorExist(){
         Profesor profesor = usuario.obtenerProfesor(usuario.getIdTipoUsuario());
        assertNotNull(profesor);
    }
    @Test
    public void obtenerProfesorNoExist(){
         Profesor profesor = usuario.obtenerProfesor(-1);
        assertNull(profesor);
    }

}
