package com.ocb.json;

import java.util.regex.Pattern;

public class Json {
    
    public static JsonObject parse(String string) throws Exception {
        String arrayPattern = "\\[\\s*(.*?)(,\\s*.*)*\\s*\\]";
        String objectPattern = "";
        string = string.strip();
        // if null
        if (Pattern.matches("null", string)) return new JsonObject("null");
        // if double
        if (Pattern.matches("\\d+\\.\\d+", string)) return new JsonObject("number_double", Double.parseDouble(string));
        // if int
        if (Pattern.matches("\\d+", string)) return new JsonObject("number_int", Integer.parseInt(string));
        // if string (with quotes)
        if (Pattern.matches("\".*\"", string)) return new JsonObject("string", string.substring(1, string.length()-1));
        // if boolean
        if (Pattern.matches("(true)|(false)", string)) return new JsonObject("bool", Boolean.parseBoolean(string));
        // if array
        if (Pattern.matches(arrayPattern, string)) {
            return new JsonObject();
        }
        // if object
        if (Pattern.matches(objectPattern, string)){
            return new JsonObject();
        }

        throw new Exception("Couldn't parse string:\n" + string);
    }

    public static String stringify(JsonObject json) {
        return json.toString();
    }
}
