/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import LogicaNegocio.Egresos.Egreso;
import java.util.List;

/**
 *
 * @author Desktop
 */
public interface EgresoDAO {

    public boolean registrarEgreso(Egreso egreso);
    public boolean editarEgreso(Egreso egreso);
    public List<Egreso> obtenerEgresos();
}
