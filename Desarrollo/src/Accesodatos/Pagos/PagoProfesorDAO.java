package Accesodatos.Pagos;

import LogicaNegocio.Pagos.PagoProfesor;
import java.util.List;

public interface PagoProfesorDAO {
    public boolean registrarPago(PagoProfesor pago, int idProfesor);
    public List<PagoProfesor> obtenerPagos(int idProfesor);
    public List<PagoProfesor> obtenerPagos();
}
