package Accesodatos.Pagos;

import LogicaNegocio.Grupos.HorarioException;
import LogicaNegocio.Pagos.Renta;
import java.util.List;

public interface RentaDAO {
    public boolean registrarRenta(Renta renta) throws HorarioException;
    public boolean editarRenta(Renta renta) throws HorarioException;
    public List<Renta> obtenerRentas();
}
