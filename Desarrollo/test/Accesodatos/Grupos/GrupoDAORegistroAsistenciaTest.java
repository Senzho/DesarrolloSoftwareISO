package Accesodatos.Grupos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Grupo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrupoDAORegistroAsistenciaTest {
    private GrupoDAOSql grupoDAO;
    private Grupo grupo;
    private Asistencia asistencia;
    private List<Alumno> alumnos;
    
    public GrupoDAORegistroAsistenciaTest() {
        this.grupoDAO = new GrupoDAOSql();
        this.grupo = this.grupoDAO.obtenerGrupos().get(0);
        this.alumnos = new AlumnoDAOSql().obtenerAlumnos(this.grupo.getId());
        this.asistencia = new Asistencia();
    }
    
    @Test
    public void registrarAsistenciaTrue(){
        this.asistencia.setFecha(Dates.toDate(Dates.toString(new Date())));
        this.asistencia.setIdAsistencia(0);
        this.asistencia.setIdGrupo(this.grupo.getId());
        this.asistencia.setAlumnos(this.alumnos);
        assertTrue(this.grupoDAO.registrarAsistencia(this.asistencia));
    }
    @Test
    public void registrarAsistenciaFalseGrupo(){
        this.asistencia.setFecha(Dates.toDate(Dates.toString(new Date())));
        this.asistencia.setIdAsistencia(0);
        this.asistencia.setIdGrupo(0);
        this.asistencia.setAlumnos(this.alumnos);
        assertFalse(this.grupoDAO.registrarAsistencia(this.asistencia));
    }
    @Test
    public void registrarAsistenciaFalseAlumnos(){
        this.asistencia.setFecha(Dates.toDate(Dates.toString(new Date())));
        this.asistencia.setIdAsistencia(0);
        this.asistencia.setIdGrupo(0);
        this.asistencia.setAlumnos(new ArrayList());
        assertFalse(this.grupoDAO.registrarAsistencia(this.asistencia));
    }
}
