package Accesodatos.Egresos;

import LogicaNegocio.Egresos.GastoPromocional;
import java.util.List;

public interface GastoPromocionalDAO {
    public boolean editarGasto(GastoPromocional gastoPromocional);
    public List<GastoPromocional> obtenerGastos();
    public boolean registrarGasto(GastoPromocional gastoPromocional);
}
