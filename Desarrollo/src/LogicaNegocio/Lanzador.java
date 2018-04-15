package LogicaNegocio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Lanzador {
    private BorderPane panel;
    private FXMLLoader cargador;
    
    public Lanzador(BorderPane panel){
        this.panel = panel;
    }
    
    public boolean lanzar(String rutaFxml){
        boolean lanzado;
        this.cargador = new FXMLLoader(this.getClass().getResource(rutaFxml));
        try {
            AnchorPane pane = this.cargador.load();
            this.panel.setCenter(pane);
            lanzado = true;
        } catch (IOException ex) {
            lanzado = false;
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lanzado;
    }
    public FXMLLoader getCargador(){
        return this.cargador;
    }
}
