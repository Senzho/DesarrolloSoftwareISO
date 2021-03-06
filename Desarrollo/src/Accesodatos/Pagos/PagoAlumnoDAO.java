/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Pagos.PagoAlumno;
import java.util.List;

/**
 *
 * @author Desktop
 */
public interface PagoAlumnoDAO {
    public List<PagoAlumno> obtenerPagos(int idAlumno, int idProfesor);
    public List<PagoAlumno> obtenerPagos(int idProfesor);
    public boolean registrarPago(PagoAlumno pagoAlumno, int idAlumno, int idPromocion);
    public PagoAlumno obtenerPrimeraInscripcion(int idAlumno, int idGrupo);
}
