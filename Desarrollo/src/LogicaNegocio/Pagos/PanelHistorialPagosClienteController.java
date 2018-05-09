/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Cliente;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Lanzador;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class PanelHistorialPagosClienteController implements Initializable {

    @FXML
    private ImageView imagenCliente;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCorreo;
    @FXML
    private VBox panelPagos;
    private Cliente cliente;
    private List<Renta> pagosCliente;
    private Lanzador lanzador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        lblNombre.setText(cliente.getNombre());
        lblCorreo.setText(cliente.getCorreo());
        Image imagenCliente = CopiarArchivo.obtenerFotoUsuario("cliente", cliente.getIdCliente());
        if (imagenCliente != null) {
            this.imagenCliente.setImage(imagenCliente);
        } else {
            this.imagenCliente.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
        inicializarPanelPagos();
    }

    public void inicializarPanelPagos() {
        this.lblNombre.setText(this.cliente.getNombre());
        this.lblCorreo.setText(this.cliente.getCorreo());
        pagosCliente = new Renta().obtenerRentas(this.cliente.getIdCliente());
        if (pagosCliente != null) {
            for (int i = pagosCliente.size() - 1; i > - 1; i--) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/PanelPagoCliente.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panel.setStyle("-fx-background-color: #DAD9D5;");
                    PanelPagoClienteController controller = loader.getController();
                    controller.setPago(pagosCliente.get(i));
                    controller.setCliente(this.cliente);
                    this.panelPagos.getChildren().add(panel);
                } catch (IOException ex) {
                    Logger.getLogger(PanelPagoClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
