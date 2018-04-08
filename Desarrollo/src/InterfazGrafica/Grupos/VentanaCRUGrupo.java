package InterfazGrafica.Grupos;

import LogicaNegocio.Grupos.VentanaCRUGrupoController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUGrupo extends Application{
    public VentanaCRUGrupo(){
        try {
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
        Scene scene = new Scene(root, 650, 400);
        primaryStage.setTitle("Grupo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
