package Accesodatos.Pagos;

import LogicaNegocio.Pagos.PagoTemporal;
import java.util.List;

public interface PagoTemporalDAO {
    public boolean registrarPago(PagoTemporal pago, int idAlumno, int idProfesor);
    public List<PagoTemporal> obtenerPagos();
    public boolean eliminarPago(int idPago);
}
