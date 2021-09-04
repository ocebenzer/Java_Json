package com.ocb.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonTest {
    
    @Test
    void parseJson() throws Exception {
        assertEquals("null", Json.parse("null").getValue());
        assertEquals(3.14, Json.parse("3.14").getValue());
        assertEquals(42, Json.parse("42").getValue());
        assertEquals("Testing", Json.parse("\"Testing\"").getValue());
        assertEquals(true, Json.parse("true").getValue());
        // array check
        // object check
    }

    @Test
    void stringifyJson() throws Exception {
        assertEquals("null", Json.stringify(new JsonObject("_null", null)));
    }
}
