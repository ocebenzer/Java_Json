package com.ocb.json;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonObject {

    private JsonTypes type;

    private ArrayList<JsonObject> value_array;
    private HashMap<String, JsonObject> value_object;

    private boolean value_boolean;
    private String value_string;
    private int value_int;
    private double value_double;

    public JsonObject(JsonTypes type) {
        this.type = type;
    }

    public JsonObject(String type, Object value) throws Exception {
        this(JsonTypes.valueOf(type), value);
    }

    public JsonObject(JsonTypes type, Object value) throws Exception {
        this.type = type;
        this.setValue(type, value);
    }

    public Object getValue() {
        switch (this.type) {
            case array:
                return value_array;
            case object:
                return value_object;
            case bool:
                return this.value_boolean;
            case string:
                return value_string;
            case number_int:
                return this.value_int;
            case number_double:
                return this.value_double;
            default:
                return null;
        }
    }

    public void setValue(JsonTypes type, Object value) throws Exception {
        if (this.type != type) {
            this.setValue(type, null);
            this.type = type;
        }

        try{
            switch (this.type) {
                case array:
                    this.value_array = (ArrayList<JsonObject>)value;
                    break;
                case object:
                    this.value_object = (HashMap<String, JsonObject>)value;
                    break;
                case bool:
                    this.value_boolean = (boolean)value;
                    break;
                case string:
                    this.value_string = (String)value;
                    break;
                case number_int:
                    this.value_int = (int)value;
                    break;
                case number_double:
                    this.value_double = (double)value;
                    break;
                default:
                    throw new Exception("Json type is not defined");
            }
        } catch (Exception e) {
            throw new Exception("entered value is not valid for given type");
        }
    }

    public JsonObject getChild(int key) throws Exception {
        return this.getChild(Integer.toString(key));
    }

    public JsonObject getChild(String key) throws Exception {
        switch (this.type) {
            case object:
                return this.value_object.get(key);
            case array:
                int index = Integer.parseInt(key);
                return this.value_array.get(index);
            default:
                throw new Exception("Couldn't find a child with key: " + key + " in Json type " + this.type);
        }
    }

    @Override
    public String toString() {
        return this.type != null
            ? this.getValue().toString()
            : "";
    }
}
