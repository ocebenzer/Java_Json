package com.ocb.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    String string = "Hello I am a string";

    @Test
    void checkConstructor() throws Exception {
        JsonObject jsonString = new JsonObject(JsonTypes.string, string);
        assertEquals(jsonString.toString(), string);
    }
}
