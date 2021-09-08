package com.ocb.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonTest {
    
    @Test
    void parseJsonPrimitive() throws Exception {
        assertEquals(null, Json.parse("null").getValue());
        assertEquals(3.14, Json.parse("3.14").getValue());
        assertEquals(42, Json.parse("42").getValue());
        assertEquals("Testing", Json.parse("\"Testing\"").getValue());
        assertEquals(true, Json.parse("true").getValue());
    }

    @Test
    void parseJsonObject() throws Exception {
        String string = "{    id : \"  arr  \"  ,  \"value\"   :  5552407,  deleted: true  }";
        JsonObject jsonObject = Json.parse(string);

        assertEquals("  arr  ", jsonObject.getChild("id").getValue());
        assertEquals(5552407, jsonObject.getChild("value").getValue());
        assertEquals(true, jsonObject.getChild("deleted").getValue());
    }

    @Test
    void parseJsonArray() throws Exception {
        String string = "  [  null  ,  42  ,  3.14  ,  [  true  ,  false  ] ,  \n \"foo\"  ]  ";
        JsonObject jsonObject = Json.parse(string);

        assertEquals(null, jsonObject.getChild(0).getValue());
        assertEquals(42, jsonObject.getChild(1).getValue());
        assertEquals(3.14, jsonObject.getChild(2).getValue());
        assertEquals(true, jsonObject.getChild(3, 0).getValue());
        assertEquals("foo", jsonObject.getChild(4).getValue());
    }

    @Test
    void equals() throws Exception {
        assertEquals(new JsonObject(), new JsonObject());
        assertEquals(Json.parse("[true, false]"), Json.parse("[true,false]"));
        assertEquals(Json.parse("{key: \"val\"}"), Json.parse("{\"key\": \"val\"}"));
    }

    @Test
    void stringifyJson() throws Exception {
        assertEquals("null", Json.stringify(new JsonObject("_null", null)));
    }
}
