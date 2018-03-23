package Accesodatos.Catalogos;

import LogicaNegocio.Catalogos.Profesor;
import java.util.List;

public interface ProfesorDAO {
    public boolean registrarProfesor(Profesor profesor);
    public boolean editarProfesor(Profesor profesor);
    public List obtenerPromociones(int idProfesor);//Obtiene una lista de la entidad Promocion
    public List<Profesor> obtenerProfesores();
    public List<Profesor> obtenerProfesores(String nombre);
}
