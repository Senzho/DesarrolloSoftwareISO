package InterfazGrafica.Grupos;

import LogicaNegocio.Grupos.Grupo;
import LogicaNegocio.Grupos.VentanaCRUGrupoController;
import LogicaNegocio.Lanzador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUGrupo extends Application{
    private Grupo grupo;
    private Lanzador lanzador;
    
    public VentanaCRUGrupo(Lanzador lanzador){
        try {
            this.lanzador = lanzador;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUGrupo(Grupo grupo, Lanzador lanzador){
        try {
            this.lanzador = lanzador;
            this.grupo = grupo;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/VentanaCRUGrupo.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUGrupoController controller = loader.getController();
        controller.iniciar(this.lanzador);
        if (this.grupo != null){
            controller.setGrupo(this.grupo);
        }
        Scene scene = new Scene(root, 650, 400);
        primaryStage.setTitle("Grupo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
