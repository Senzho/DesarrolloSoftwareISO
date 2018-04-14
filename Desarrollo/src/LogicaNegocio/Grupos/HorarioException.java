package LogicaNegocio.Grupos;

public class HorarioException extends Exception{
    private String mensaje;
    
    public HorarioException(Dia dia){
        this.mensaje = "Las horas marcadas para el día " + dia.getDia() + " (" + dia.getHoraInicio() + " - " + dia.getHoraFin() + "), chocan con el horario de otro grupo";
    }
    @Override
    public String getMessage(){
        return this.mensaje;
    }
}
