package LogicaNegocio.Catalogos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUAlumno extends Application{
    private Alumno alumno;
    
    public VentanaCRUAlumno(){
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUAlumno(Alumno alumno){
        try {
            this.alumno = alumno;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCRUAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Catalogos/VentanaCRUAlumno.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUAlumnoController controller = loader.getController();
        controller.setAlumno(this.alumno);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Alumno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
