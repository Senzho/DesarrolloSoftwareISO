package Accesodatos.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import LogicaNegocio.Grupos.HorarioException;
import LogicaNegocio.Pagos.Renta;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentaDOAEdicionTest {
    private RentaDAOSql rentaDAO;
    private Renta renta;
    private int idCliente;
    private String monto;
    private String horaInicio;
    private String horaFin;
    
    public RentaDOAEdicionTest() {
        this.rentaDAO = new RentaDAOSql();
        this.idCliente = new ClienteDAOSql().obtenerClientes().get(0).getIdCliente();
        this.renta = this.rentaDAO.obtenerRentas(this.idCliente).get(0);
        this.monto = "250";
        this.horaInicio = "11:30";
        this.horaFin = "12:00";
    }
    
    @Test
    public void editarRentaTrue(){
        this.renta.setMonto(this.monto);
        this.renta.getDia().setHoraInicio(this.horaInicio);
        this.renta.getDia().setHoraFin(this.horaFin);
        try {
            assertTrue(this.rentaDAO.editarRenta(this.renta));
        } catch (HorarioException ex) {
            Logger.getLogger(RentaDOAEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarRentaFalseId(){
        this.renta.setIdRenta(0);
        this.renta.setMonto(this.monto);
        this.renta.getDia().setHoraInicio(this.horaInicio);
        this.renta.getDia().setHoraFin(this.horaFin);
        try {
            assertFalse(this.rentaDAO.editarRenta(this.renta));
        } catch (HorarioException ex) {
            Logger.getLogger(RentaDOAEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void editarRentaFalseMonto(){
        this.renta.setMonto("qw");
        this.renta.getDia().setHoraInicio(this.horaInicio);
        this.renta.getDia().setHoraFin(this.horaFin);
        try {
            assertFalse(this.rentaDAO.editarRenta(this.renta));
        } catch (HorarioException ex) {
            Logger.getLogger(RentaDOAEdicionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test (expected = HorarioException.class)
    public void editarRentaHorarioException() throws HorarioException{
        this.renta.setMonto(this.monto);
        this.renta.getDia().setHoraInicio("10:00");
        this.renta.getDia().setHoraFin("12:00");
        this.rentaDAO.editarRenta(this.renta);
    }
}
