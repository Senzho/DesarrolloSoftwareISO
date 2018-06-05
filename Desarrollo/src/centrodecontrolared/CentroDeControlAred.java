/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrodecontrolared;

import LogicaNegocio.Catalogos.VentanaCRUProfesor;
import LogicaNegocio.Sesiones.Usuario;
import LogicaNegocio.Sesiones.VentanaInicioSesion;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class CentroDeControlAred extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        if (new Usuario().obtenerNumeroUsuario() > 0){
            new VentanaInicioSesion();
        }else{
            new VentanaCRUProfesor(null);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
