package com.ocb.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

class AppTest {

    ArrayList<JsonObject> array = new ArrayList<JsonObject>();
    HashMap<String, JsonObject> object = new HashMap<String, JsonObject>();
    
    boolean bool = true;
    String string = "Hello I am a string";
    double number_double = 3.14;
    int number_int = 42;

    JsonObject jsonArray,
        jsonObject,
        jsonBool,
        jsonString,
        jsonDouble,
        jsonInt;

    @BeforeEach
    void initializeTests() throws Exception {
        jsonArray = new JsonObject("array", array);
        jsonObject = new JsonObject("object", object);

        jsonBool = new JsonObject("bool", bool);
        jsonString = new JsonObject("string", string);
        jsonDouble = new JsonObject("number_double", number_double);
        jsonInt = new JsonObject("number_int", number_int);
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
    void tostrings() {
        assertEquals(jsonArray.toString(), "[]");
        assertEquals(jsonObject.toString(), "{}");

        assertEquals(jsonBool.toString(), Boolean.toString(bool));
        assertEquals(jsonString.toString(), string);
        assertEquals(jsonDouble.toString(), Double.toString(number_double));
        assertEquals(jsonInt.toString(), Integer.toString(number_int));
    }
}
