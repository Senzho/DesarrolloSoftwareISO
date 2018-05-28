package LogicaNegocio.Asistencia;

import Accesodatos.Asistencia.AsistenciaDAOSql;
import LogicaNegocio.Catalogos.Alumno;
import java.util.Date;
import java.util.List;

public class Asistencia {
    private int idAsistencia;
    private int idGrupo;
    private Date fecha;
    private List<Alumno> alumnos;
    private AsistenciaDAOSql asistenciaDAO;
    
    public Asistencia(){
        this.asistenciaDAO = new AsistenciaDAOSql();
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }
    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }
    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    public boolean asistenciaRegistrada(){
        return this.asistenciaDAO.asistenciaRegistrada(this.idGrupo, this.fecha);
    }
    public List<Asistencia> obtenerAsistencias(int idGrupo){
        return this.asistenciaDAO.obtenerAsistencias(idGrupo);
    }
}
