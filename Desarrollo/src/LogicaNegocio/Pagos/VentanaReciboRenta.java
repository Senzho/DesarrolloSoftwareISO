/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Desktop
 */
public class VentanaReciboRenta extends Application{
    private Stage stage;
    private Cliente cliente;
    private Renta renta;
    
    public VentanaReciboRenta(Cliente cliente, Renta renta){
        this.cliente = cliente;
        this.renta = renta;
        try {
            this.start(this.stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaReciboRenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaReciboRenta.fxml"));
        AnchorPane root = loader.load();
        VentanaReciboRentaController controller = loader.getController();
        controller.setStage(this.stage);
        controller.setCliente(cliente);
        controller.setRenta(renta);
        controller.generarDatosRecibo();
        Scene scene = new Scene(root, 332, 317);
        primaryStage.setTitle("Recibo de renta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
