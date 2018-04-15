package LogicaNegocio.Grupos;

public class Horas {
    public static int getSegundos(String hora){
        int segundos;
        try{
            String segmentos[] = hora.split(":");
            int horas = Integer.valueOf(segmentos[0]);
            int minutos = Integer.valueOf(segmentos[1]);
            segundos = (minutos * 60) + (horas * 60 * 60);
        }catch(NumberFormatException exception){
            segundos = -1;
        }
        return segundos;
    }
}
