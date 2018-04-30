/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Inscripciones;

import LogicaNegocio.Catalogos.Alumno;
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
public class VentanaCambiarAlumnoGrupo extends Application{
    private Alumno alumno;
    private int idProfesor;
    
    public VentanaCambiarAlumnoGrupo(Alumno alumno, int idProfesor){
        this.idProfesor = idProfesor;
        this.alumno = alumno;        
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaCambiarAlumnoGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Inscripciones/VentanaCambiarAlumnoGrupo.fxml"));
        AnchorPane root = loader.load();
        VentanaCambiarAlumnoGrupoController controller = loader.getController();
        controller.setAlumno(this.alumno);
        controller.setIdProfesor(idProfesor);
        Scene scene = new Scene(root, 433, 271);
        primaryStage.setTitle("Alumno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}