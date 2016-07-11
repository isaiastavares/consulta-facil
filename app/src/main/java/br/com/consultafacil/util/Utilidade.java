package br.com.consultafacil.util;

import org.apache.commons.lang3.StringUtils;

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
}
