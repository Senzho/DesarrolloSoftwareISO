package LogicaNegocio.Catalogos;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacionesString {
    public static boolean coincide(String clave, String cadena){
        clave = OperacionesString.sinAcentosYMayusculas(clave);
        cadena = OperacionesString.sinAcentosYMayusculas(cadena);
        boolean coincide = false;
        int largoCadena = cadena.length();
        int largoClave = clave.length();
        for (int i = 0; i < largoCadena - largoClave + 1; i ++){
            if (cadena.substring(i, i + largoClave).equals(clave)){
                coincide = true;
                break;
            }
        }
        return coincide;
    }
    public static String sinAcentosYMayusculas(String texto){
        String clean = "";
        if (!texto.equals("")) {
            String value = texto.toUpperCase();
            clean = Normalizer.normalize(value, Normalizer.Form.NFD);
            clean = clean.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            clean = Normalizer.normalize(clean, Normalizer.Form.NFC);
        }
        return clean;
    }
    public static boolean emailValido(String email){
        boolean valido = false;
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher coincidencia = patron.matcher(email);
        if (coincidencia.find()){
            valido = true;
        }
        return valido;
    }
}
