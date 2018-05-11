package Accesodatos.Asistencia;

import LogicaNegocio.Asistencia.Asistencia;
import java.util.Date;

public interface AsistenciaDAO {
    public Asistencia obtenerAsistencia(int idGrupo, Date fecha);
    public boolean asistenciaRegistrada(int idGrupo, Date fecha);
}
