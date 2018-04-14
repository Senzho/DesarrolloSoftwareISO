package Accesodatos.Grupos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Controladores.DiaJpaController;
import Accesodatos.Controladores.GrupoJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.Horario;
import LogicaNegocio.Grupos.HorarioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class GrupoDAOSql implements GrupoDAO{
    private Grupo obtenerEntidad(Accesodatos.Entidades.Grupo grupoJpa){
        Grupo grupo = new Grupo();
        grupo.setDanza(grupoJpa.getDanza());
        grupo.setId(grupoJpa.getIdGrupo());
        grupo.setNombre(grupoJpa.getNombre());
        return grupo;
    }
    private Accesodatos.Entidades.Grupo obtenerEntidad(Grupo grupo){
        Accesodatos.Entidades.Grupo grupoJpa = new Accesodatos.Entidades.Grupo();
        grupoJpa.setDanza(grupo.getDanza());
        grupoJpa.setIdGrupo(grupo.getId());
        grupoJpa.setNombre(grupo.getNombre());
        return grupoJpa;
    }
    private Dia horarioValido(List<Dia> dias){
        Dia diaError = null;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Dia> diasJpa = controller.findDiaEntities();
        fors:
        for (Dia dia : dias){
            for (Accesodatos.Entidades.Dia diaJpa : diasJpa){
                if (diaJpa.getDia().equals(dia.getDia())){
                    if (diaJpa.getSalon().equals(dia.getSalon())){
                        String horaInicioStringsJpa[] = diaJpa.getHoraInicio().split(":");
                        int horaInicioJpa = Integer.valueOf(horaInicioStringsJpa[0]);
                        int minutosInicioJpa = Integer.valueOf(horaInicioStringsJpa[1]);
                        String horaFinStringsJpa[] = diaJpa.getHoraFin().split(":");
                        int horaFinJpa = Integer.valueOf(horaFinStringsJpa[0]);
                        int minutosFinJpa = Integer.valueOf(horaFinStringsJpa[1]);
                        String horaInicioStrings[] = dia.getHoraInicio().split(":");
                        int horaInicio = Integer.valueOf(horaInicioStrings[0]);
                        int minutosInicio = Integer.valueOf(horaInicioStrings[1]);
                        String horaFinStrings[] = dia.getHoraFin().split(":");
                        int horaFin = Integer.valueOf(horaFinStrings[0]);
                        int minutosFin = Integer.valueOf(horaFinStrings[1]);
                        int miliIniJpa = (horaInicioJpa * 60 * 60) + (minutosInicioJpa * 60);
                        int miliIni = (horaInicio * 60 * 60) + (minutosInicio * 60);
                        int miliFinJpa = (horaFinJpa * 60 * 60) + (minutosFinJpa * 60);
                        int miliFin = (horaFin * 60 * 60) + (minutosFin * 60);
                        if ((miliIni >= miliIniJpa && miliIni <= miliFinJpa) || (miliFin >= miliIniJpa && miliFin <= miliFinJpa)){
                            diaError = dia;
                            break fors;
                        }
                    } 
                }
            }
        }
        return diaError;
    }
    
    public GrupoDAOSql(){
        
    }
    
    @Override
    public boolean registrarGrupo(Grupo grupo, int idProfesor) throws HorarioException{
        boolean registrado = true;
        GrupoJpaController controller = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Grupo grupoJpa = this.obtenerEntidad(grupo);
        HorarioException horarioException = null;
        try{
            DiaDAOSql diaDAO = new DiaDAOSql();
            grupoJpa.setIdProfesor(profesorController.findProfesor(idProfesor));
            Dia diaValidacion = this.horarioValido(grupo.getHorario().getDias());
            if (diaValidacion == null){
                controller.create(grupoJpa);
                grupo.setId(grupoJpa.getIdGrupo());
                for (Dia dia : grupo.getHorario().getDias()){
                    dia.setIdTipo(grupo.getId());
                    diaDAO.agregarDia(dia);
                } 
            }else{
                horarioException = new HorarioException(diaValidacion);
                throw horarioException;
            }
        }catch(Exception exception){
            registrado = false;
            if (exception instanceof HorarioException){
                throw horarioException;
            }
        } 
        return registrado;
    }
    @Override
    public boolean editarGrupo(Grupo grupo) {
        return false;
    }
    @Override
    public List<Grupo> obtenerGruposProfesor(int idProfesor) {
        List<Grupo> grupos = new ArrayList();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        for (Accesodatos.Entidades.Grupo grupoJpa : profesorController.findProfesor(idProfesor).getGrupoCollection()){
            Grupo grupo = this.obtenerEntidad(grupoJpa);
            grupo.setHorario(new Horario(grupo.getId()));
            grupos.add(grupo);
        }
        return grupos;
    }
    @Override
    public List<Grupo> obtenerGruposAlumno(int idAlumnos) {
        return new ArrayList();
    }
    @Override
    public List<Grupo> obtenerGrupos(){
        List<Grupo> grupos = new ArrayList();
        GrupoJpaController controller = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Grupo> gruposJpa = controller.findGrupoEntities();
        gruposJpa.forEach((grupoJpa) -> {
            Grupo grupo = this.obtenerEntidad(grupoJpa);
            grupo.setProfesor(ProfesorDAOSql.obtenerEntidad(grupoJpa.getIdProfesor()));
            grupo.setHorario(new Horario(grupo.getId()));
            grupos.add(grupo);
        });
        return grupos;
    }
}
