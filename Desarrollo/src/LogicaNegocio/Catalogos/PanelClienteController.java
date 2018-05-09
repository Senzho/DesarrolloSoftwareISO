package LogicaNegocio.Catalogos;

import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelHistorialPagosClienteController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelClienteController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private ImageView editar;
    @FXML
    private ImageView imagen;
    private Cliente cliente;
    private Lanzador lanzador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.editar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        this.cargarImagen();
    }
    
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.cargarCliente();
    }
    public void cargarCliente(){
        this.nombre.setText(this.cliente.getNombre());
        this.correo.setText(this.cliente.getCorreo());
        this.telefono.setText(this.cliente.getTelefono());
        Image imagenCliente = CopiarArchivo.obtenerFotoUsuario("cliente", cliente.getIdCliente());
        if(imagenCliente != null){
            this.imagen.setImage(imagenCliente);
        }
    }
    
    public void editar_OnClick(){
        new VentanaCRUCliente(this.cliente);
    }
    public void cargarImagen() {
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    public void btnRentas_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelHistorialPagosCliente.fxml");
        PanelHistorialPagosClienteController controller =  lanzador.getCargador().getController();
        controller.setLanzador(lanzador);
        controller.setCliente(this.cliente);
    }
    
}
