package LogicaNegocio.Inscripciones;

import Accesodatos.Inscripciones.InscripcionDAOSql;

public class Inscripcion {
    private int idInscripcion;
    private int idGrupo;
    private int idAlumno;
    private final InscripcionDAOSql inscripcionDAO;

    public Inscripcion(){
        this.inscripcionDAO = new InscripcionDAOSql();
    }
    public Inscripcion(int idInscripcion, int idGrupo, int idAlumno) {
        this.idInscripcion = idInscripcion;
        this.idGrupo = idGrupo;
        this.idAlumno = idAlumno;
        this.inscripcionDAO = new InscripcionDAOSql();
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public boolean registrar(){
        return this.inscripcionDAO.registrar(this);
    }
    public boolean borrarRegistro(int idAlumno, int idGrupo){
        return this.inscripcionDAO.borrarRegistro(idAlumno, idGrupo);
    }
}
