package au.com.auspost.microservice.utils;

/**
 * Created by terrence on 21/11/15.
 */
public final class StringFormatter {

    private StringFormatter() {
    }

    public static String padLeftWithZero(String str, int n) {

        if (str.length() >= n) {
            return str;
        } else {
            return String.format("%0" + (n - str.length()) + "d%s", 0, str);
        }
    }

}
