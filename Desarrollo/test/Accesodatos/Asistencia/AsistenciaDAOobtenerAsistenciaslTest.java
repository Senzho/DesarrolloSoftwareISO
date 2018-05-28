/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Asistencia;

import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Grupo;
import java.util.Date;
import java.util.List;
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
public class AsistenciaDAOobtenerAsistenciaslTest {

    private AsistenciaDAOSql asistenciaDao;
    private Profesor profesor;
    private Grupo grupo;

    public AsistenciaDAOobtenerAsistenciaslTest() {
        asistenciaDao = new AsistenciaDAOSql();
        Profesor profesor = new Profesor().obtenerProfesores().get(0);
        grupo = new Grupo().obtenerGruposProfesor(profesor.getIdProfesor()).get(0);
    }

    @Test
    public void obtenerAsistenciasExceptionTest() {
        try {
            asistenciaDao.obtenerAsistencias(0);
        } catch (Exception excepcion) {
            fail();
        }
    }

}
