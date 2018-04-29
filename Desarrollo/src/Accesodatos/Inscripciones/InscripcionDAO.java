package Accesodatos.Inscripciones;

import LogicaNegocio.Inscripciones.Inscripcion;

public interface InscripcionDAO {
    public boolean registrar(Inscripcion inscripcion);
    public boolean borrarRegistro(int idAlumno, int idGrupo);
}
