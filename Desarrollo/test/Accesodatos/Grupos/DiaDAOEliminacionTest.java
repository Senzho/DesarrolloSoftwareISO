package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Dia;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiaDAOEliminacionTest {
    private DiaDAOSql diaDAO;
    private Dia dia;
    
    public DiaDAOEliminacionTest() {
        this.diaDAO = new DiaDAOSql();
        this.dia = new Dia();
        this.dia.setDia("Lunes");
        this.dia.setHoraFin("20:00");
        this.dia.setHoraInicio("10:00");
        this.dia.setId(0);
        this.dia.setSalon("X");
        this.dia.setTipo(false);
        this.dia.setIdTipo(5000);
    }
    
    @Test
    public void eliminarDiaTrue(){
        this.diaDAO.agregarDia(this.dia);
        assertTrue(this.diaDAO.eliminarDia(this.dia.getId()));
    }
    @Test
    public void eliminarDiaFalse(){
        assertFalse(this.diaDAO.eliminarDia(0));
    }
}
