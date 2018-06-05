package LogicaNegocio.Catalogos;

import LogicaNegocio.Lanzador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUProfesor extends Application{
    private Profesor profesor;
    private Lanzador lanzador;
    
    public VentanaCRUProfesor(Lanzador lanzador){
        try {
            this.lanzador = lanzador;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUProfesor(Profesor profesor, Lanzador lanzador){
        try {
            this.profesor = profesor;
            this.lanzador = lanzador;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/VentanaCRUProfesor.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUProfesorController controller = loader.getController();
        controller.iniciar(this.profesor, this.lanzador);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Profesor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
