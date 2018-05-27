package LogicaNegocio.Grupos;

import javafx.scene.layout.AnchorPane;

public class Calendarizable implements Comparable<Calendarizable>{
    private int valor;
    private AnchorPane pane;
    
    public void setValor(int valor){
        this.valor = valor;
    }
    public int getValor(){
        return this.valor;
    }
    protected void setPane(AnchorPane pane){
        this.pane = pane;
    }
    protected AnchorPane getPane(){
        return this.pane;
    }
    
    @Override
    public int compareTo(Calendarizable cal) {
        return Integer.valueOf(cal.getValor()).compareTo(this.valor);
    }
}
