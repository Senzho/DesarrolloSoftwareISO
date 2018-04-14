/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrodecontrolared;

import Accesodatos.Pagos.PagoAlumnoDAOSql;
import LogicaNegocio.Pagos.PagoAlumno;
import LogicaNegocio.Sesiones.VentanaInicioSesion;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author marioolopez
 */
public class CentroDeControlAred extends Application {

    @Override
    public void start(Stage primaryStage) {
       // new VentanaInicioSesion();
    }

    public static void main(String[] args) {
        List<PagoAlumno> pagos  = new PagoAlumnoDAOSql().obtenerPagos(1);
        for(PagoAlumno pago:pagos){
            System.out.println(pago.getFecha());
            System.out.println(pago.getIdPagoAlumno());
            System.out.println(pago.getMonto());
            System.out.println(pago.getIdPromocion());
            System.out.println(pago.getTipoPago());
        }
    }

}
