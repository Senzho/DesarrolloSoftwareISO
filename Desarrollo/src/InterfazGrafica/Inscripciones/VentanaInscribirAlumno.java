package InterfazGrafica.Inscripciones;

import LogicaNegocio.Inscripciones.VentanaInscribirAlumnoController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaInscribirAlumno extends Application{
    private int idProfesor;
    
    public VentanaInscribirAlumno(int idProfesor){
        try {
            this.idProfesor = idProfesor;
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaInscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Inscripciones/VentanaInscribirAlumno.fxml"));
        AnchorPane root = loader.load();
        VentanaInscribirAlumnoController controller = loader.getController();
        controller.iniciar(this.idProfesor, primaryStage);
        Scene scene = new Scene(root, 450, 200);
        primaryStage.setTitle("Inscripción");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
