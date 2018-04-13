package LogicaNegocio.Grupos;

import Accesodatos.Grupos.GrupoDAOSql;
import java.util.List;

public class Grupo {
    private int id;
    private String nombre;
    private String danza;
    private Horario horario;
    private GrupoDAOSql grupoDAO;

    public Grupo(){
        this.grupoDAO = new GrupoDAOSql();
    }
    public Grupo(int id, String nombre, String danza) {
        this.id = id;
        this.nombre = nombre;
        this.danza = danza;
        this.grupoDAO = new GrupoDAOSql();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDanza() {
        return danza;
    }
    public void setDanza(String danza) {
        this.danza = danza;
    }
    public Horario getHorario() {
        return horario;
    }
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    public boolean registrarGrupo(){
        return this.grupoDAO.registrarGrupo(this);
    }
    public boolean editarGrupo(){
        return this.grupoDAO.editarGrupo(this);
    }
    public List<Grupo> obtenerGruposProfesor(int idProfesor){
        return this.grupoDAO.obtenerGruposProfesor(idProfesor);
    }
    public List<Grupo> obtenerGruposAlumno(int idAlumno){
        return this.grupoDAO.obtenerGruposAlumno(idAlumno);
    }
}
