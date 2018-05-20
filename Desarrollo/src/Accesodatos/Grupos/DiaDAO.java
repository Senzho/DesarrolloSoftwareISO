package Accesodatos.Grupos;

import LogicaNegocio.Grupos.Dia;
import java.util.List;

public interface DiaDAO {
    public boolean agregarDia(Dia dia);
    public boolean editarDia(Dia dia);
    public boolean eliminarDia(int idDia);
    public List<Dia> obtenerDias(int idGrupo);
    public Dia obtenerDia(int idRenta);
}
