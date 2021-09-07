package com.ocb.json;
public class JsonHelpers {

    public static boolean hasQuotes(String string) {
        return string.charAt(0) == '"' && string.charAt(string.length()-1) == '"';
    }

    public static String addQuotes(String string) {
        return "\"" + string + "\"";
    }

    public static String removeQuotes(String string) throws Exception {
        if (!hasQuotes(string)){
            throw new Exception(string + " has no quotes");
        }
        return string.substring(1, string.length()-1);
    }
}
