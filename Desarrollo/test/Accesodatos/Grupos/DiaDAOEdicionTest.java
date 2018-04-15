package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Dia;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiaDAOEdicionTest {
    private Dia dia;
    private DiaDAOSql diaDAO;
    private int id;
    
    public DiaDAOEdicionTest() {
        this.diaDAO = new DiaDAOSql();
        this.dia = this.diaDAO.obtenerDias(18).get(0);
        this.id = this.dia.getId();
        //35 | X     |    1 |     18 | Viernes | 09:00      | 09:30
    }
    
    @Test
    public void editarDiaTrue(){
        this.dia.setId(this.id);
        assertTrue(this.diaDAO.editarDia(this.dia));
    }
    @Test
    public void editarDiaFalseId(){
        this.dia.setId(0);
        assertFalse(this.diaDAO.editarDia(this.dia));
    }
}
