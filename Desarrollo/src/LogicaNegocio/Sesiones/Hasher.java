/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Sesiones;

/**
 *
 * @author Desktop
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hasher {

    private Hasher() {

    }

    public static String hash(String chain) {
        String hash = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(chain.getBytes());
            int size = bytes.length;
            StringBuffer stringBuffer = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int value = bytes[i] & 255;
                String hexadecimalValue = Integer.toHexString(value);
                if (value < 16) {
                    stringBuffer.append("0" + hexadecimalValue);
                } else {
                    stringBuffer.append(hexadecimalValue);
                }
            }
            hash = stringBuffer.toString();
        } catch (NoSuchAlgorithmException exception) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, exception);
        }
        return hash;
    }
}
