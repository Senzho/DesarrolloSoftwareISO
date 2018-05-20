package Accesodatos.Grupos;

import Accesodatos.Controladores.DiaJpaController;
import LogicaNegocio.Grupos.Dia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DiaDAOSql implements DiaDAO{
    private Dia obtenerEntidad(Accesodatos.Entidades.Dia diaJpa){
        Dia dia = new Dia();
        dia.setDia(diaJpa.getDia());
        dia.setHoraFin(diaJpa.getHoraFin());
        dia.setHoraInicio(diaJpa.getHoraInicio());
        dia.setId(diaJpa.getIdDia());
        dia.setIdTipo(diaJpa.getIdTipo());
        dia.setSalon(diaJpa.getSalon());
        dia.setTipo((diaJpa.getTipo() != 0));
        return dia;
    }
    private Accesodatos.Entidades.Dia obtenerEntidad(Dia dia){
        Accesodatos.Entidades.Dia diaJpa = new Accesodatos.Entidades.Dia();
        diaJpa.setDia(dia.getDia());
        diaJpa.setHoraFin(dia.getHoraFin());
        diaJpa.setHoraInicio(dia.getHoraInicio());
        diaJpa.setIdDia(dia.getId());
        diaJpa.setIdTipo(dia.getIdTipo());
        diaJpa.setSalon(dia.getSalon());
        diaJpa.setTipo(dia.isTipo()?1:0);
        return diaJpa;
    }
    
    public DiaDAOSql(){
        
    }

    @Override
    public boolean agregarDia(Dia dia) {
        boolean agregado = true;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Dia diaJpa = this.obtenerEntidad(dia);
        try{
            controller.create(diaJpa);
            dia.setId(diaJpa.getIdDia());
        }catch(Exception excepcion){
            agregado = false;
        }
        return agregado;
    }
    @Override
    public boolean editarDia(Dia dia) {
        boolean editado;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try{
            controller.edit(this.obtenerEntidad(dia));
            editado = true;
        }catch(Exception excepcion){
            editado = false;
        }
        return editado;
    }
    @Override
    public List<Dia> obtenerDias(int idGrupo) {
        List<Dia> dias = new ArrayList();
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Dia.findByGrupo");
        query.setParameter("idGrupo", idGrupo);
        query.getResultList().forEach((diaJpa) -> {
            dias.add(this.obtenerEntidad((Accesodatos.Entidades.Dia) diaJpa));
        });
        return dias;
    }
    @Override
    public Dia obtenerDia(int idRenta) {
        Dia dia = null;
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Dia.findByRenta");
        query.setParameter("idRenta", idRenta);
        Object resultado = query.getSingleResult();
        if (resultado != null){
            dia = this.obtenerEntidad((Accesodatos.Entidades.Dia) resultado);
        }
        return dia;
    }
    @Override
    public boolean eliminarDia(int idDia){
        boolean eliminado = false;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try{
            controller.destroy(idDia);
            eliminado = true;
        }catch(Exception excepcion){
            eliminado = false;
        }
        return eliminado;
    }
}
