package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.HorarioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrupoDAOEdicionTest {
    private GrupoDAOSql grupoDAO;
    private Grupo grupo;
    private List<Dia> listaOriginal;
    private String dia;
    private String horaInicio;
    private String horaFin;
    private int id;
    private int idProfesor;
    
    public GrupoDAOEdicionTest() {
        this.grupoDAO = new GrupoDAOSql();
        this.grupo = this.grupoDAO.obtenerGrupos().get(0);
        this.listaOriginal = this.grupo.getHorario().getDias();
        this.dia = this.grupo.getHorario().getDias().get(0).getDia();
        this.horaInicio = this.grupo.getHorario().getDias().get(0).getHoraInicio();
        this.horaFin = this.grupo.getHorario().getDias().get(0).getHoraFin();
        this.id = this.grupo.getId();
        this.idProfesor = this.grupo.getProfesor().getIdProfesor();
    }
    
    @Test
    public void editarGrupoTrue(){
        this.grupo.setId(this.id);
        this.grupo.getHorario().getDias().get(0).setDia(this.dia);
        this.grupo.getHorario().getDias().get(0).setHoraInicio(this.horaInicio);
        this.grupo.getHorario().getDias().get(0).setHoraFin(this.horaFin);
        this.grupo.getProfesor().setIdProfesor(this.idProfesor);
        try {
            assertTrue(this.grupoDAO.editarGrupo(grupo, this.listaOriginal));
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test (expected = HorarioException.class)
    public void editarGrupoHorarioExceeption(){
        this.grupo.setId(this.id);
        this.grupo.getHorario().getDias().get(0).setDia("Lunes");
        this.grupo.getHorario().getDias().get(0).setHoraInicio("11:30");
        this.grupo.getHorario().getDias().get(0).setHoraFin("12:00");
        this.grupo.getProfesor().setIdProfesor(this.idProfesor);
        try {
            this.grupoDAO.editarGrupo(grupo, this.listaOriginal);
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarGrupoFalseId(){
        this.grupo.setId(0);
        this.grupo.getHorario().getDias().get(0).setDia(this.dia);
        this.grupo.getHorario().getDias().get(0).setHoraInicio(this.horaInicio);
        this.grupo.getHorario().getDias().get(0).setHoraFin(this.horaFin);
        this.grupo.getProfesor().setIdProfesor(this.idProfesor);
        try {
            assertFalse(this.grupoDAO.editarGrupo(grupo, this.listaOriginal));
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarGrupoFalseProfesor(){
        this.grupo.setId(this.id);
        this.grupo.getHorario().getDias().get(0).setDia(this.dia);
        this.grupo.getHorario().getDias().get(0).setHoraInicio(this.horaInicio);
        this.grupo.getHorario().getDias().get(0).setHoraFin(this.horaFin);
        this.grupo.getProfesor().setIdProfesor(0);
        try {
            assertFalse(this.grupoDAO.editarGrupo(grupo, this.listaOriginal));
        } catch (HorarioException ex) {
            Logger.getLogger(GrupoDAOEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
