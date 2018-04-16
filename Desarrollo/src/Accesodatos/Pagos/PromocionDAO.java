/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Pagos.Promocion;
import java.util.List;

/**
 *
 * @author Desktop
 */
public interface PromocionDAO {
    public boolean editarPromocion(Promocion promocion);
    public boolean registrarPromocion(Promocion promocion);
    public List<Promocion> obtenerPromociones(int idProfesor);
    Promocion obtenerPromocion(int idPromocion);
}
