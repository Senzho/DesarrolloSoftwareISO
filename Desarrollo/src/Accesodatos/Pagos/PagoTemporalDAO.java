package Accesodatos.Pagos;

import LogicaNegocio.Pagos.PagoTemporal;
import java.util.List;

public interface PagoTemporalDAO {
    public boolean registrarPago(PagoTemporal pago);
    public List<PagoTemporal> obtenerPagos();
    public boolean eliminarPago(int idPago);
}
