package Accesodatos.Grupos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Controladores.AlumnoJpaController;
import Accesodatos.Controladores.AsistenciaJpaController;
import Accesodatos.Controladores.DiaJpaController;
import Accesodatos.Controladores.GrupoJpaController;
import Accesodatos.Controladores.ProfesorJpaController;
import LogicaNegocio.Asistencia.Asistencia;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Grupos.Dia;
import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.Horario;
import LogicaNegocio.Grupos.HorarioException;
import LogicaNegocio.Grupos.Horas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GrupoDAOSql implements GrupoDAO{
    private Grupo obtenerEntidad(Accesodatos.Entidades.Grupo grupoJpa){
        Grupo grupo = new Grupo();
        grupo.setDanza(grupoJpa.getDanza());
        grupo.setId(grupoJpa.getIdGrupo());
        grupo.setNombre(grupoJpa.getNombre());
        grupo.setEstado(grupoJpa.getEstado());
        return grupo;
    }
    private Accesodatos.Entidades.Grupo obtenerEntidad(Grupo grupo){
        Accesodatos.Entidades.Grupo grupoJpa = new Accesodatos.Entidades.Grupo();
        grupoJpa.setDanza(grupo.getDanza());
        grupoJpa.setIdGrupo(grupo.getId());
        grupoJpa.setNombre(grupo.getNombre());
        grupoJpa.setEstado(grupo.getEstado());
        return grupoJpa;
    }
    private Dia horarioValido(List<Dia> dias){
        Dia diaError = null;
        DiaJpaController controller = new DiaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Dia> diasJpa = controller.findDiaEntities();
        fors:
        for (Dia dia : dias){
            for (Accesodatos.Entidades.Dia diaJpa : diasJpa){
                if (!(dia.isTipo() == (diaJpa.getTipo()==1) && dia.getIdTipo() == diaJpa.getIdTipo())){
                    if (diaJpa.getDia().equals(dia.getDia())){
                        if (diaJpa.getSalon().equals(dia.getSalon())){
                            int miliIniJpa = Horas.getSegundos(diaJpa.getHoraInicio());
                            int miliIni = Horas.getSegundos(dia.getHoraInicio());
                            int miliFinJpa = Horas.getSegundos(diaJpa.getHoraFin());
                            int miliFin = Horas.getSegundos(dia.getHoraFin());
                            if ((miliIni >= miliIniJpa && miliIni < miliFinJpa) || (miliFin > miliIniJpa && miliFin <= miliFinJpa)){
                                diaError = dia;
                                break fors;
                            }
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
    public boolean registrarGrupo(Grupo grupo) throws HorarioException{
        boolean registrado = true;
        GrupoJpaController controller = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Accesodatos.Entidades.Grupo grupoJpa = this.obtenerEntidad(grupo);
        HorarioException horarioException = null;
        try{
            DiaDAOSql diaDAO = new DiaDAOSql();
            grupoJpa.setIdProfesor(profesorController.findProfesor(grupo.getProfesor().getIdProfesor()));
            Dia diaValidacion = this.horarioValido(grupo.getHorario().getDias());
            if (diaValidacion == null){
                controller.create(grupoJpa);
                grupo.setId(grupoJpa.getIdGrupo());
                for (Dia dia : grupo.getHorario().getDias()){
                    dia.setTipo(true);
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
    public boolean editarGrupo(Grupo grupo, List<Dia> listaOriginal) throws HorarioException{
        boolean editado = true;
        GrupoJpaController grupoController = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        Dia diaValidacion = this.horarioValido(grupo.getHorario().getDias());
        if (diaValidacion == null){
            try{
                Accesodatos.Entidades.Grupo grupoJpa = grupoController.findGrupo(grupo.getId());
                grupoJpa.setDanza(grupo.getDanza());
                Accesodatos.Entidades.Profesor profesorJpa = profesorController.findProfesor(grupo.getProfesor().getIdProfesor());
                if (profesorJpa != null){
                    grupoJpa.setIdProfesor(profesorJpa);
                    grupoJpa.setNombre(grupo.getNombre());
                    grupoJpa.setEstado(grupo.getEstado());
                    grupoController.edit(grupoJpa);
                    DiaDAOSql diaDAO = new DiaDAOSql();
                    listaOriginal.forEach((diaOriginal) -> {
                        boolean esta = false;
                        for (Dia diaNuevo : grupo.getHorario().getDias()) {
                            if (diaOriginal.getId() == diaNuevo.getId()){
                                esta = true;
                                diaDAO.editarDia(diaNuevo);
                            }else if (diaNuevo.getId() == 0){
                                diaNuevo.setTipo(true);
                                diaNuevo.setIdTipo(grupo.getId());
                                diaDAO.agregarDia(diaNuevo);
                            }
                        }
                        if (!esta){
                            diaDAO.eliminarDia(diaOriginal.getId());
                        }
                    });
                }else{
                    editado = false;
                }
            }catch(Exception excepcion){
                editado = false;
            }
        }else{
            throw new HorarioException(diaValidacion);
        }  
        return editado;
    }
    @Override
    public List<Grupo> obtenerGruposProfesor(int idProfesor) {
        List<Grupo> grupos = new ArrayList();
        ProfesorJpaController profesorController = new ProfesorJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        for (Accesodatos.Entidades.Grupo grupoJpa : profesorController.findProfesor(idProfesor).getGrupoCollection()){
            Grupo grupo = this.obtenerEntidad(grupoJpa);
            if (grupo.getEstado() == 1){
                grupo.setProfesor(ProfesorDAOSql.obtenerEntidad(grupoJpa.getIdProfesor()));
                grupo.setHorario(new Horario(grupo.getId()));
                grupos.add(grupo);
            }  
        }
        return grupos;
    }
    @Override
    public List<Grupo> obtenerGruposAlumno(int idAlumnos) {
        List<Grupo> grupos = new ArrayList();
        EntityManager entityManager = Persistence.createEntityManagerFactory("CentroDeControlAredPU").createEntityManager();
        Query query = entityManager.createNamedQuery("Grupo.findByAlumno");
        query.setParameter("idAlumno", idAlumnos);
        query.getResultList().forEach((grupoJpa) -> {
            Accesodatos.Entidades.Grupo grupoJpaCont = (Accesodatos.Entidades.Grupo) grupoJpa;
            Grupo grupo = this.obtenerEntidad(grupoJpaCont);
            grupo.setProfesor(ProfesorDAOSql.obtenerEntidad(grupoJpaCont.getIdProfesor()));
            grupos.add(grupo);
        });
        return grupos;
    }
    @Override
    public List<Grupo> obtenerGrupos(){
        List<Grupo> grupos = new ArrayList();
        GrupoJpaController controller = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        List<Accesodatos.Entidades.Grupo> gruposJpa = controller.findGrupoEntities();
        gruposJpa.forEach((grupoJpa) -> {
            if (grupoJpa.getEstado() == 1){
                Grupo grupo = this.obtenerEntidad(grupoJpa);
                grupo.setProfesor(ProfesorDAOSql.obtenerEntidad(grupoJpa.getIdProfesor()));
                grupo.setHorario(new Horario(grupo.getId()));
                grupos.add(grupo);
            } 
        });
        return grupos;
    }
    @Override
    public boolean registrarAsistencia(Asistencia asistencia){
        boolean registrada;
        AsistenciaJpaController controller = new AsistenciaJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        AlumnoJpaController alumnoController = new AlumnoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        GrupoJpaController grupoController = new GrupoJpaController(Persistence.createEntityManagerFactory("CentroDeControlAredPU"));
        try{
            Accesodatos.Entidades.Grupo grupoJpa = grupoController.findGrupo(asistencia.getIdGrupo());
            if (grupoJpa != null){
                List<Accesodatos.Entidades.Asistencia> asistenciasJpa = new ArrayList();
                for(Alumno alumno : asistencia.getAlumnos()){
                    Accesodatos.Entidades.Asistencia asistenciaJpa = new Accesodatos.Entidades.Asistencia();
                    asistenciaJpa.setFecha(asistencia.getFecha());
                    asistenciaJpa.setIdAlumno(alumnoController.findAlumno(alumno.getIdAlumno()));
                    asistenciaJpa.setIdAsistencia(0);
                    asistenciaJpa.setIdGrupo(grupoJpa);
                    asistenciasJpa.add(asistenciaJpa);
                }
                if (asistenciasJpa.isEmpty()){
                    registrada = false;
                }else{
                    controller.create(asistenciasJpa);
                    registrada = true;
                } 
            }else{
                registrada = false;
            }  
        }catch(Exception excepcion){
            registrada = false;
        }
        return registrada;
    }
}
