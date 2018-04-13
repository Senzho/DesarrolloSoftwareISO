package LogicaNegocio.Grupos;

import Accesodatos.Grupos.DiaDAOSql;
import java.util.List;

public class Horario {
    private int idGrupo;
    private List<Dia> dias;
    private DiaDAOSql diaDAO;
    
    public Horario(int idGrupo){
        this.idGrupo = idGrupo;
        this.diaDAO = new DiaDAOSql();
        this.setDias(this.diaDAO.obtenerDias(idGrupo));
    }

    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public List<Dia> getDias() {
        return dias;
    }
    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }
}
