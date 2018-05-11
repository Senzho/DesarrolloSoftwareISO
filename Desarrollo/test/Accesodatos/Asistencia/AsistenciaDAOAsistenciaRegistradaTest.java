package Accesodatos.Asistencia;

import Accesodatos.Grupos.GrupoDAOSql;
import LogicaNegocio.Egresos.Dates;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsistenciaDAOAsistenciaRegistradaTest {
    private AsistenciaDAOSql asistenciaDAO;
    private int idGrupo;
    private Date fecha;
    
    public AsistenciaDAOAsistenciaRegistradaTest() {
        this.asistenciaDAO = new AsistenciaDAOSql();
        this.idGrupo = new GrupoDAOSql().obtenerGrupos().get(0).getId();
        this.fecha = Dates.toDate(Dates.toString(new Date()));
    }
    
    @Test
    public void asistenciaRegistradaTrue(){
        assertTrue(this.asistenciaDAO.asistenciaRegistrada(this.idGrupo, this.fecha));
    }
    @Test
    public void asistenciaRegistradaFalseGrupo(){
        assertFalse(this.asistenciaDAO.asistenciaRegistrada(0, this.fecha));
    }
    @Test
    public void asistenciaRegistradaFalseFecha(){
        assertFalse(this.asistenciaDAO.asistenciaRegistrada(this.idGrupo, Dates.toDate("2015-05-05")));
    }
}
