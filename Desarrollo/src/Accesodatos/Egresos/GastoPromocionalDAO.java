/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Egresos;

import LogicaNegocio.Egresos.GastoPromocional;
import java.util.List;

/**
 *
 * @author Desktop
 */
public interface GastoPromocionalDAO {
    public boolean editarGasto(GastoPromocional gastoPromocional);
    public List<GastoPromocional> obtenerGastos();
    public boolean registrarGasto(GastoPromocional gastoPromocional);
}
