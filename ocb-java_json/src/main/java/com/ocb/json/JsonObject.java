package com.ocb.json;

import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class JsonObject {

    private JsonTypes type;
    private Object value;

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

    public JsonObject(Map<String, JsonObject> value) throws Exception {
        this.type = JsonTypes.object;
        this.setValue(this.type, value);
    }

    public JsonObject(List<JsonObject> value) throws Exception {
        this.type = JsonTypes.array;
        this.setValue(this.type, value);
    }
    public JsonObject(JsonObject[] value) throws Exception {
        this.type = JsonTypes.array;
        this.setValue(this.type, Arrays.asList(value));
    }

    public Object getValue() {
        return this.value;
    }

    @SuppressWarnings("unchecked")
    public void setValue(JsonTypes type, Object value) throws Exception {
        if (this.type != type) {
            this.setValue(type, null);
            this.type = type;
        }

        try{
            switch (this.type) {
                case array:
                    this.value = (List<JsonObject>)value;
                    break;
                case object:
                    this.value = (Map<String, JsonObject>)value;
                    break;
                case bool:
                    this.value = (boolean)value;
                    break;
                case string:
                    this.value = (String)value;
                    break;
                case number_int:
                    this.value = (int)value;
                    break;
                case number_double:
                    this.value = (double)value;
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

    @SuppressWarnings("unchecked")
    public JsonObject getChild(String key) throws Exception {
        switch (this.type) {
            case array:
                int index = Integer.parseInt(key);
                return ((List<JsonObject>)this.value).get(index);
            case object:
                return ((Map<String, JsonObject>)this.value).get(key);
            default:
                throw new Exception("Couldn't find a child with key: " + key + " in Json type " + this.type);
        }
    }

    public JsonObject getChild(String[] keys) throws Exception{
        JsonObject current = this;

        for (String key : keys) {
            current = this.getChild(key);
        }
        return current;
    }

    @Override
    public String toString() {
        return this.type != null
            ? this.getValue().toString()
            : "";
    }
}
