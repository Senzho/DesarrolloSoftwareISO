/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Inscripciones;

import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Grupos.Grupo;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Desktop
 */
public class InscripcionDAOCambiarGrupolTest {
    private InscripcionDAOSql inscripcionDao;
    private GrupoDAOSql grupoDao;
    private Alumno alumno;
    private List<Grupo> grupos;
    
    public InscripcionDAOCambiarGrupolTest(){
        inscripcionDao = new InscripcionDAOSql();
        grupoDao = new GrupoDAOSql();
        List<Alumno> alumnos = new Alumno().obtenerAlumnos();
        alumno = alumnos.get(0);
        grupos = grupoDao.obtenerGruposAlumno(alumno.getIdAlumno());
        
    }
    @Test
    public void borrarRegistroFalse() {
        boolean borrado = inscripcionDao.borrarRegistro(0, 0);
        assertFalse(borrado);
    }
    @Test
    public void borrarRegistroTrue() {
        boolean borrado = inscripcionDao.borrarRegistro(alumno.getIdAlumno(), grupos.get(0).getId());
        assertTrue(borrado);
        
    }    
    
}
