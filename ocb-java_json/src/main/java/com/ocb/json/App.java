package com.ocb.json;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        JsonObject testObject = new JsonObject("string", "Hello my JSON library!");

        System.out.println(testObject);
    }
}
