package LogicaNegocio.Pagos;

import LogicaNegocio.Lanzador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaRegistrarPagoAlumno extends Application {
    private int idProfesor;
    private String nombreAlumno;
    private Lanzador lanzador;
   
    public VentanaRegistrarPagoAlumno(int idProfesor, Lanzador lanzador){
        this.idProfesor = idProfesor;
        this.lanzador = lanzador;
        try {
            start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaRegistrarPagoAlumno(int idProfesor, String nombreAlumno, Lanzador lanzador){
        this.idProfesor = idProfesor;
        this.nombreAlumno = nombreAlumno;
        this.lanzador = lanzador;
        try {
            start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaRegistrarPagoAlumno.fxml"));
        AnchorPane root = loader.load();//ancho largo
        VentanaRegistrarPagoAlumnoController controller = loader.getController();
        controller.setIdProfesor(this.idProfesor);
        controller.iniciar(this.lanzador);
        if (this.nombreAlumno != null){
            controller.setAlumnoSeleccionado(this.nombreAlumno);
        }
        Scene scene = new Scene(root, 471, 231);
        primaryStage.setTitle("Registrar pago alumno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
