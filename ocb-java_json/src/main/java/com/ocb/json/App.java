package com.ocb.json;

import java.util.HashMap;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        JsonObject testObject = new JsonObject();
        testObject.addChild("key", new JsonObject("Hello my JSON library!"));

        HashMap<String, JsonObject> nestedDict = new HashMap<String, JsonObject>();
        nestedDict.put("name", new JsonObject("Aang"));
        nestedDict.put("surname", new JsonObject(""));
        nestedDict.put("age", new JsonObject(112));
        nestedDict.put("email", new JsonObject(""));
        nestedDict.put("isStudent", new JsonObject(true));
        nestedDict.put("misc", new JsonObject());

        JsonObject misc = nestedDict.get("misc");
        misc.addChild("relations", new JsonObject());

        String[] loves = { "Bisons", "Cats", "Dogs", "Momo", null };
        String[] hates = { "Pufferfishes" };

        JsonObject relations = misc.getChild("relations");
        relations.addChild("loves", new JsonObject(loves));
        relations.addChild("hates", new JsonObject(hates));

        Object[] value = { 0, true, 0.0, new JsonObject(nestedDict), new JsonObject("array") };
        testObject.addChild("value", new JsonObject(value));



        JsonObject arr = Json.parse("  [  null  ,  42  ,  3.14  ,  [  true  ,  false  ] ,  \n \"foo\"  ]  ");
        JsonObject obj = Json.parse("  {    key : \"  arr  \"  ,  \"value\"   :   ${arr}  }   ".replace("${arr}", arr.toString()));

        System.out.println();
        System.out.println(testObject);
        System.out.println();
        System.out.println(obj);
        System.out.println(arr);
        System.out.println();
    }
}
