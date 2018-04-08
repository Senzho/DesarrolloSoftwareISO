/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Pagos;

import LogicaNegocio.Pagos.Promocion;

/**
 *
 * @author Desktop
 */
public interface PromocionDAO {
    public boolean editarPromocion(Promocion promocion);
    public boolean registrarPromocion(Promocion promocion);
}
