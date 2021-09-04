package com.ocb.json;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

class JsonObjectTest {

    static ArrayList<JsonObject> array = new ArrayList<JsonObject>();
    static HashMap<String, JsonObject> object = new HashMap<String, JsonObject>();
    
    static boolean bool = true;
    static String string = "Hello I am a string";
    static double number_double = 3.14;
    static int number_int = 42;

    static JsonObject jsonArray,
        jsonObject,
        jsonBool,
        jsonString,
        jsonDouble,
        jsonInt,
        bigJson;

    static String addQuotes(String string) {
        return "\"" + string + "\"";
    }

    @BeforeAll
    static void initializeTests() throws Exception {
        jsonArray = new JsonObject("array", array);
        jsonObject = new JsonObject("object", object);

        jsonBool = new JsonObject("bool", bool);
        jsonString = new JsonObject("string", string);
        jsonDouble = new JsonObject("number_double", number_double);
        jsonInt = new JsonObject("number_int", number_int);

        JsonObject[] values = { jsonArray, jsonObject, jsonBool, jsonString, jsonDouble, jsonInt };
        bigJson = new JsonObject(values);
    }

    @Test
    void constructors() {
        assertEquals(array, jsonArray.getValue());
        assertEquals(object, jsonObject.getValue());

        assertEquals(bool, jsonBool.getValue());
        assertEquals(string, jsonString.getValue());
        assertEquals(number_double, jsonDouble.getValue());
        assertEquals(number_int, jsonInt.getValue());
    }

    @Test
    void tostringfunctions() {
        assertEquals("[]", jsonArray.toString());
        assertEquals("{}", jsonObject.toString());

        assertEquals(Boolean.toString(bool), jsonBool.toString());
        assertEquals(addQuotes(string),jsonString.toString());
        assertEquals(Double.toString(number_double), jsonDouble.toString());
        assertEquals(Integer.toString(number_int), jsonInt.toString());
    }

    @Test
    void getchildfunctions() throws Exception {
        System.out.println(bigJson);
        assertEquals(jsonArray, bigJson.getChild(0));
    }
}
