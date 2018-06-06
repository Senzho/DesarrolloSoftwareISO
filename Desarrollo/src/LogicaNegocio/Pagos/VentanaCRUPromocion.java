package LogicaNegocio.Pagos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaCRUPromocion extends Application{
    private Stage stage;
    private int idProfesor;
    private Promocion promocion;
    private PromocionListener listener;
    
    public VentanaCRUPromocion(int idProfesor, PromocionListener listener){
        this.idProfesor = idProfesor;
        this.listener = listener;
        try {
            this.start(stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public VentanaCRUPromocion(int idProfesor, Promocion promocion, PromocionListener listener){
        this.idProfesor = idProfesor;
        this.promocion = promocion;
        this.listener = listener;
        try {
            this.start(stage = new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaRegistrarPagoProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Pagos/VentanaCRUPromocion.fxml"));
        AnchorPane root = loader.load();
        VentanaCRUPromocionController controller = loader.getController();
        controller.setStage(this.stage);
        controller.setIdProfesor(this.idProfesor);
        controller.setPromocionListener(this.listener);
        if(promocion!= null){
            controller.setPromocion(this.promocion);
        }
        Scene scene = new Scene(root, 568, 331);
        primaryStage.setTitle("Consultar promociones");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}