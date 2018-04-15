package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Dia;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiaDAOObtencionCreacionTest {
    private DiaDAOSql diaDAO;
    private Dia dia;
    
    public DiaDAOObtencionCreacionTest() {
        this.diaDAO = new DiaDAOSql();
        this.dia = new Dia();
        this.dia.setDia("Lunes");
        this.dia.setHoraFin("20:00");
        this.dia.setHoraInicio("19:00");
        this.dia.setId(0);
        this.dia.setIdTipo(5);
        this.dia.setSalon("Y");
        this.dia.setTipo(true);
    }
    
    @Test
    public void obtenerDiasExcepcion(){
        try{
            this.diaDAO.obtenerDias(18);
        }catch(Exception excepcion){
            fail();
        }
    }
    @Test
    public void agregarDiaTrue(){
        assertTrue(this.diaDAO.agregarDia(this.dia));
    }
}
