package com.ocb.json;

public class Json {
    
    public static JsonObject parse(String string) {
        return new JsonObject(JsonTypes.object);
    }

    public static String stringify(JsonObject json) {
        return json.toString();
    }
}