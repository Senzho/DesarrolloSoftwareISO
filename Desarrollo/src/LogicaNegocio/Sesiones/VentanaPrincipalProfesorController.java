/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.PanelGruposProfesorController;
import LogicaNegocio.Lanzador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VentanaPrincipalProfesorController implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    private Lanzador lanzador;
    @FXML
    private MenuItem menuRegistrarPagos;
    
    @FXML
    private ImageView imagenCambiarCuenta;
    @FXML
    private BorderPane panelPrincipal;
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.imagenCambiarCuenta.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        System.out.println(usuario.getNombre());
        this.usuario = usuario;
        this.profesor = profesor;
        menuMisGrupos_onClick();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//1 activo 2 baja
        this.lanzador = new Lanzador(this.panelPrincipal);
        
    }  
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
    public void menuMisGrupos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
        PanelGruposProfesorController controller = this.lanzador.getCargador().getController();
        controller.setLanzador(lanzador);
        controller.setIdProfesor(this.profesor.getIdProfesor());
    }
}
