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
        assertEquals(jsonArray.getValue(), array);
        assertEquals(jsonObject.getValue(), object);

        assertEquals(jsonBool.getValue(), bool);
        assertEquals(jsonString.getValue(), string);
        assertEquals(jsonDouble.getValue(), number_double);
        assertEquals(jsonInt.getValue(), number_int);
    }

    @Test
    void tostringfunctions() {
        assertEquals(jsonArray.toString(), "[]");
        assertEquals(jsonObject.toString(), "{}");

        assertEquals(jsonBool.toString(), Boolean.toString(bool));
        assertEquals(jsonString.toString(), string);
        assertEquals(jsonDouble.toString(), Double.toString(number_double));
        assertEquals(jsonInt.toString(), Integer.toString(number_int));
    }

    @Test
    void getchildfunctions() throws Exception {
        System.out.println(bigJson);
        assertEquals(bigJson.getChild(0), jsonArray);
    }
}
