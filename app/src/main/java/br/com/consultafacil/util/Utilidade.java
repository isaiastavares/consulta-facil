package br.com.consultafacil.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Isaias on 11/07/2016.
 */
public final class Utilidade {

    private Utilidade() {
        super();
    }

    public static String getDataString(int year, int monthOfYear, int dayOfMonth) {
        return StringUtils.leftPad(String.valueOf(dayOfMonth), 2, "0") + "/"
                + StringUtils.leftPad(String.valueOf(monthOfYear + 1), 2, "0") + "/"
                + String.valueOf(year);
    }

    public static String gerarHashMD5(String frase) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(frase.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
                int parteBaixa = bytes[i] & 0xf;
                if (parteAlta == 0) s.append('0');
                s.append(Integer.toHexString(parteAlta | parteBaixa));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
