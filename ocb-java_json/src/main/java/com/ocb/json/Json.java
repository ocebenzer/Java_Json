package com.ocb.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Json {

    public static String stringify(JsonObject json) {
        return json.toString();
    }

    public static JsonObject parse(String string) throws Exception {
        Scanner scanner = new Scanner(string);

        //pass whitespace
        scanner.skip("\\s*");

        // split scanner in chars ':', ',', '[', ']', '{', '}'
        String delimiters = "[:,\\[\\]\\{\\}]";
        scanner.useDelimiter("((?<=$)|(?=$))".replace("$", delimiters));
        return parse(scanner);
    }

    private static JsonObject parse(Scanner scanner) throws Exception {
        scanner.skip("\\s*");
        String token = scanner.next();
        token = token.strip();

        // if null
        if (token.matches("null")) return new JsonObject("_null", "null");
        // if double
        if (token.matches("\\d+\\.\\d+")) return new JsonObject("number_double", Double.parseDouble(token));
        // if int
        if (token.matches("\\d+")) return new JsonObject("number_int", Integer.parseInt(token));
        // if string (with quotes)
        if (token.matches("\".*\"")) return new JsonObject("string", token.substring(1, token.length()-1));
        // if boolean
        if (token.matches("(true)|(false)")) return new JsonObject("bool", Boolean.parseBoolean(token));

        // if array
        if (token.matches("\\[")) return parseArray(scanner);
        // if object
        if (token.matches("\\{"))return parseObject(scanner);

        throw new Exception("Couldn't parse token: \"$\"".replace("$", token));
    }

    private static JsonObject parseObject(Scanner scanner) throws Exception {
        HashMap<String,JsonObject> values = new HashMap<String,JsonObject>();

        String token = "";

        while (scanner.hasNext()) {
            scanner.skip("\\s*");
            String key = scanner.next().trim();
            if (JsonHelpers.hasQuotes(key)) key = JsonHelpers.removeQuotes(key);
            scanner.skip("\\s*");
            token = scanner.next();
            if (!token.equals(":")) break;
            values.put(key, parse(scanner));
            scanner.skip("\\s*");
            token = scanner.next();
            switch (token) {
                case ",":
                    continue;
                case "}":
                    return new JsonObject(values);
                default:
                    throw new Exception("Unknown token: \"$\"".replace("$", token));
            }
        }

        throw new Exception("Couldn't parse string, stuck at: \"$\"".replace("$", token));
    }

    private static JsonObject parseArray(Scanner scanner) throws Exception {
        ArrayList<JsonObject> values = new ArrayList<JsonObject>();

        String token = "";

        while (scanner.hasNext()) {
            scanner.skip("\\s*");
            values.add(parse(scanner));
            scanner.skip("\\s*");
            token = scanner.next();

            switch (token) {
                case ",":
                    continue;
                case "]":
                    return new JsonObject(values);
                default:
                    throw new Exception("Unknown token: \"$\"".replace("$", token));
            }
        }

        throw new Exception("Couldn't parse string, stuck at: \"$\"".replace("$", token));
    }
}
