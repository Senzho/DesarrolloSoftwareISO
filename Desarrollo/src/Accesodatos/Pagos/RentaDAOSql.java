package Accesodatos.Pagos;

import Accesodatos.Catalogos.ClienteDAOSql;
import Accesodatos.Controladores.ClienteJpaController;
import Accesodatos.Controladores.DiaJpaController;
import Accesodatos.Controladores.RentaJpaController;
import Accesodatos.Grupos.DiaDAOSql;
import LogicaNegocio.Catalogos.OperacionesString;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.HorarioException;
import LogicaNegocio.Grupos.Horas;
import LogicaNegocio.Pagos.Renta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RentaDAOSql implements RentaDAO {

    private Dia diaValido(Dia dia) {
        Dia diaError = null;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Dia> diasJpa = controller.findDiaEntities();
        for (Accesodatos.Entidades.Dia diaJpa : diasJpa) {
            if (!(dia.isTipo() == (diaJpa.getTipo()==1) && dia.getIdTipo() == diaJpa.getIdTipo())){
                if (diaJpa.getDia().equals(dia.getDia())) {
                    if (diaJpa.getSalon().equals(dia.getSalon())) {
                        int miliIniJpa = Horas.getSegundos(diaJpa.getHoraInicio());
                        int miliIni = Horas.getSegundos(dia.getHoraInicio());
                        int miliFinJpa = Horas.getSegundos(diaJpa.getHoraFin());
                        int miliFin = Horas.getSegundos(dia.getHoraFin());
                        if ((miliIni >= miliIniJpa && miliIni < miliFinJpa) || (miliFin > miliIniJpa && miliFin <= miliFinJpa)) {
                            diaError = dia;
                            break;
                        }
                    }
                }
            }  
        }
        return diaError;
    }

    public RentaDAOSql() {

    }

    @Override
    public boolean registrarRenta(Renta renta) throws HorarioException {
        boolean registrada = false;
        if (OperacionesString.montoValido(renta.getMonto())){
            EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU");
            RentaJpaController rentaController = new RentaJpaController(entityManager);
            ClienteJpaController clienteController = new ClienteJpaController(entityManager);
            Accesodatos.Entidades.Renta rentaJpa = new Accesodatos.Entidades.Renta();
            rentaJpa.setIdRenta(renta.getIdRenta());
            rentaJpa.setFecha(renta.getFecha());
            rentaJpa.setMonto(renta.getMonto());
            if (this.diaValido(renta.getDia()) == null) {
                try {
                    rentaJpa.setIdCliente(clienteController.findCliente(renta.getCliente().getIdCliente()));
                    rentaController.create(rentaJpa);
                    renta.setIdRenta(rentaJpa.getIdRenta());
                    renta.getDia().setIdTipo(renta.getIdRenta());
                    DiaDAOSql diaDAO = new DiaDAOSql();
                    diaDAO.agregarDia(renta.getDia());
                    registrada = true;
                } catch (Exception excepcion) {
                    registrada = false;
                }
            } else {
                throw new HorarioException(renta.getDia());
            }
        }  
        return registrada;
    }

    @Override
    public boolean editarRenta(Renta renta) throws HorarioException {
        boolean editada = false;
        if (OperacionesString.montoValido(renta.getMonto())){
            if (this.diaValido(renta.getDia()) == null) {
                EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU");
                RentaJpaController rentaController = new RentaJpaController(entityManager);
                try {
                    Accesodatos.Entidades.Renta rentaJpa = rentaController.findRenta(renta.getIdRenta());
                    rentaJpa.setFecha(renta.getFecha());
                    rentaJpa.setMonto(renta.getMonto());
                    rentaController.edit(rentaJpa);
                    DiaDAOSql diaDAO = new DiaDAOSql();
                    diaDAO.editarDia(renta.getDia());
                    editada = true;
                } catch (Exception ex) {
                    editada = false;
                    Logger.getLogger(RentaDAOSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                throw new HorarioException(renta.getDia());
            }
        }
        return editada;
    }

    @Override
    public List<Renta> obtenerRentas() {
        List<Renta> rentas = new ArrayList();
        RentaJpaController controller = new RentaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Renta> rentasJpa = controller.findRentaEntities();
        rentasJpa.forEach((rentaJpa) -> {
            rentas.add(this.obtenerEntidad(rentaJpa));
        });
        return rentas;
    }

    private Renta obtenerEntidad(Accesodatos.Entidades.Renta rentaJpa) {
        Renta renta = new Renta();
        renta.setFecha(rentaJpa.getFecha());
        renta.setCliente(ClienteDAOSql.obtenerEntidad(rentaJpa.getIdCliente()));
        renta.setIdRenta(rentaJpa.getIdRenta());
        renta.setMonto(rentaJpa.getMonto());
        Dia dia = new DiaDAOSql().obtenerDia(rentaJpa.getIdRenta());
        renta.setDia(dia);
        return renta;
    }

    @Override
    public List<Renta> obtenerRentas(int idCliente) {
        List<Accesodatos.Entidades.Renta> rentasJpa;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Renta.findRentasByIdCliente");
        query.setParameter("idCliente", idCliente);
        rentasJpa = query.getResultList();
        List<Renta> rentas = null;
        if (!rentasJpa.isEmpty()) {
            rentas = new ArrayList<>();
            for (Accesodatos.Entidades.Renta rentaJpa : rentasJpa) {
                rentas.add(obtenerEntidad(rentaJpa));
            }
        }
        return rentas;
    }
}
