package Accesodatos.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Egresos.Dates;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.HorarioException;
import LogicaNegocio.Pagos.Renta;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentaDAOCreacionTest {
    private RentaDAOSql rentaDAO;
    private Renta renta;
    private int idCliente;
    private String monto;
    private String horaFin;
    private String horaInicio;
    
    public RentaDAOCreacionTest() {
        this.rentaDAO = new RentaDAOSql();
        Cliente cliente = new ClienteDAOSql().obtenerClientes().get(0);
        this.idCliente = cliente.getIdCliente();
        this.monto = "250";
        this.horaInicio = "11:30";
        this.horaFin = "12:00";
        this.renta = new Renta();
        Dia dia = new Dia();
        dia.setDia("Lunes");
        dia.setHoraFin(this.horaFin);
        dia.setHoraInicio(this.horaInicio);
        dia.setId(0);
        dia.setTipo(false);
        dia.setSalon("X");
        dia.setIdTipo(this.idCliente);
        this.renta.setDia(dia);
        this.renta.setCliente(cliente);
    }
    
    @Test
    public void registrarRentaTrue(){
        this.renta.setFecha(Dates.toDate("2018-05-12"));
        this.renta.getCliente().setIdCliente(this.idCliente);
        this.renta.setIdRenta(0);
        this.renta.setMonto(this.monto);
        this.renta.getDia().setHoraInicio(this.horaInicio);
        this.renta.getDia().setHoraFin(this.horaFin);
        try {
            assertTrue(this.rentaDAO.registrarRenta(this.renta));
        } catch (HorarioException ex) {
            Logger.getLogger(RentaDAOCreacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void registrarRentaFalseMonto(){
        this.renta.setFecha(Dates.toDate("2018-05-12"));
        this.renta.getCliente().setIdCliente(this.idCliente);
        this.renta.setIdRenta(0);
        this.renta.setMonto("w");
        this.renta.getDia().setHoraInicio(this.horaInicio);
        this.renta.getDia().setHoraFin(this.horaFin);
        try {
            assertFalse(this.rentaDAO.registrarRenta(this.renta));
        } catch (HorarioException ex) {
            Logger.getLogger(RentaDAOCreacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test (expected = HorarioException.class)
    public void registrarRentaHorarioException() throws HorarioException{
        this.renta.setFecha(Dates.toDate("2018-05-12"));
        this.renta.getCliente().setIdCliente(this.idCliente);
        this.renta.setIdRenta(0);
        this.renta.setMonto(this.monto);
        this.renta.getDia().setHoraInicio("17:30");
        this.renta.getDia().setHoraFin("18:00");
        this.rentaDAO.registrarRenta(this.renta);
    }
}
