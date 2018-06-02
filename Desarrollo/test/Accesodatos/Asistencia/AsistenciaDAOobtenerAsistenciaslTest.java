/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Asistencia;

import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Grupo;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Desktop
 */
public class AsistenciaDAOobtenerAsistenciaslTest {

    private AsistenciaDAOSql asistenciaDao;
    private Profesor profesor;
    private Grupo grupo;
    private Alumno alumno;
    
    public AsistenciaDAOobtenerAsistenciaslTest() {
        asistenciaDao = new AsistenciaDAOSql();
        Profesor profesor = new Profesor().obtenerProfesores().get(0);
        grupo = new Grupo().obtenerGruposProfesor(profesor.getIdProfesor()).get(0);
        alumno = new Alumno().obtenerAlumnos(grupo.getId()).get(0);
    }

    @Test
    public void obtenerAsistenciasExceptionTest() {
        try {
            asistenciaDao.obtenerAsistencias(grupo.getId(), alumno.getIdAlumno());
        } catch (Exception excepcion) {
            fail();
        }
    }
}
