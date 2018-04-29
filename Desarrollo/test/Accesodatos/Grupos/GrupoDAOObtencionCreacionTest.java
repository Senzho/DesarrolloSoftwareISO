package Accesodatos.Grupos;

import Accesodatos.Catalogos.AlumnoDAOSql;
import Accesodatos.Catalogos.ProfesorDAOSql;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.Horario;
import LogicaNegocio.Grupos.HorarioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrupoDAOObtencionCreacionTest {
    private GrupoDAOSql grupoDAO;
    private Grupo grupo;
    private Profesor profesor;
    private List<Dia> dias;
    private Dia dia;
    
    public GrupoDAOObtencionCreacionTest() {
        this.grupoDAO = new GrupoDAOSql();
        this.grupo = new Grupo();
        this.grupo.setDanza("Un tipo de danza");
        this.grupo.setNombre("Soy un grupo");
        this.grupo.setId(0);
        this.profesor = new ProfesorDAOSql().obtenerProfesores().get(0);
        this.grupo.setProfesor(this.profesor);
        Horario horario = new Horario();
        this.dias = new ArrayList();
        this.dia = new Dia(0, "Miércoles", "09:30", "11:00", "X", true, 0);
        this.dias.add(this.dia);
        horario.setDias(this.dias);
        this.grupo.setHorario(horario);
    }
    
    @Test
    public void obtenerGruposProfesorNoExcepcion(){
        try{
            this.grupoDAO.obtenerGruposProfesor(new ProfesorDAOSql().obtenerProfesores().get(0).getIdProfesor());
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test (expected = NullPointerException.class)
    public void obtenerGruposProfesorExcepcion(){
        this.grupoDAO.obtenerGruposProfesor(0);
    }
    @Test
    public void obtenerGruposAlumnoNoExcepcion(){
        try{
            this.grupoDAO.obtenerGruposAlumno(new AlumnoDAOSql().obtenerAlumnos().get(0).getIdAlumno());
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void obtenerGruposNoExcepcion(){
        try{
            this.grupoDAO.obtenerGrupos();
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void registrarGrupoTrue(){
        this.dias.clear();
        this.dias.add(this.dia);
        this.grupo.setProfesor(this.profesor);
        try {
            assertTrue(this.grupoDAO.registrarGrupo(this.grupo));
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOObtencionCreacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void registrarGrupoFalseProfesor(){
        this.dias.clear();
        this.dias.add(this.dia);
        this.grupo.setProfesor(null);
        try {
            assertFalse(this.grupoDAO.registrarGrupo(this.grupo));
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOObtencionCreacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test (expected = HorarioException.class)
    public void registrarGrupoExcepcion() throws HorarioException{
        this.dias.clear();
        Dia diaMalo = new Dia(0, "Lunes", "14:00", "15:00", "X", true, 0);
        this.dias.add(diaMalo);
        this.grupo.setProfesor(this.profesor);
        this.grupoDAO.registrarGrupo(this.grupo);
    }
}
