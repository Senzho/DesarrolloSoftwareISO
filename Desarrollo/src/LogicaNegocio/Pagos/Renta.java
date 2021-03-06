package LogicaNegocio.Pagos;

import Accesodatos.Grupos.DiaDAOSql;
import Accesodatos.Pagos.RentaDAOSql;
import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.HorarioException;
import java.util.Date;
import java.util.List;

public class Renta {
    private int idRenta;
    private Date fecha;
    private Cliente cliente;
    private Dia dia;
    private String monto;
    private RentaDAOSql rentaDAO;
    
    public Renta(){
        this.rentaDAO = new RentaDAOSql();
    }
    public Renta(int idRenta, Date fecha, Cliente cliente, String monto){
        this.idRenta = idRenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.monto = monto;
        this.dia = new DiaDAOSql().obtenerDia(this.idRenta);
        this.rentaDAO = new RentaDAOSql();
    }

    public int getIdRenta() {
        return idRenta;
    }
    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Dia getDia() {
        return dia;
    }
    public void setDia(Dia dia) {
        this.dia = dia;
    }
    public String getMonto(){
        return this.monto;
    }
    public void setMonto(String monto){
        this.monto = monto;
    }
    
    public boolean rentar() throws HorarioException{
        return this.rentaDAO.registrarRenta(this);
    }
    public boolean editarRenta() throws HorarioException{
        return this.rentaDAO.editarRenta(this);
    }
    public List<Renta> obtenerRentas(){
        return this.rentaDAO.obtenerRentas();
    }
    public List<Renta> obtenerRentas(int idCliente){
        return this.rentaDAO.obtenerRentas(idCliente);
    }
    public boolean eliminarRenta(int idRenta){
        return this.rentaDAO.eliminarRenta(idRenta);
    }
}
