package com.ocb.json;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class JsonObject {
    private JsonTypes type;
    private Object value;

    private static final HashMap<String,String> TYPE_MAP = new HashMap<>();
    static {
        TYPE_MAP.put("java.lang.Boolean", "bool");
        TYPE_MAP.put("java.lang.String", "string");
        TYPE_MAP.put("java.lang.Integer", "number_int");
        TYPE_MAP.put("java.lang.Double", "number_double");

        TYPE_MAP.put("null", "_null");
    }

    /**
     * Creates an empty JsonObject with default type "object"
     * @throws Exception
     */
    public JsonObject() throws Exception {
        this(JsonTypes.object);
    }

    /**
     * Creates a JsonObject with default value based on given type
     * @param type
     * @throws Exception
     */
    public JsonObject(JsonTypes type) throws Exception {
        this.type = type;
        Object value;
        switch (this.type) {
            case object:
                value = new HashMap<String, JsonObject>();
                break;
            case array:
                value = new ArrayList<JsonObject>();
                break;
            case bool:
                value = false;
                break;
            case string:
                value = "";
                break;
            case number_int:
                value = 0;
                break;
            case number_double:
                value = 0.0;
                break;
            default:
                value = null;
                break;
        }
        this.setValue(type, value);
    }

    /**
     * Creates a JsonObject with given type and value
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(String type, Object value) throws Exception {
        this(JsonTypes.valueOf(type), value);
    }

    /**
     * Creates a JsonObject with given type and value
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(JsonTypes type, Object value) throws Exception {
        this.type = type;
        this.setValue(type, value);
    }

    /**
     * Creates a JsonObject with type "object"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(Map<String, JsonObject> value) throws Exception {
        this.type = JsonTypes.object;
        this.setValue(this.type, value);
    }

    /**
     * Creates a JsonObject with type "array"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(List<JsonObject> value) throws Exception {
        this.type = JsonTypes.array;
        this.setValue(this.type, value);
    }

    /**
     * Creates a JsonObject with type "array"
     * @param type
     * @param values array of values
     * @throws Exception
     * @implNote all elements of values must be one of: [boolean, string, int, double, JsonObject]
     */
    public JsonObject(Object[] values) throws Exception {
        this.type = JsonTypes.array;
        ArrayList<JsonObject> jsonValues = new ArrayList<JsonObject>();
        for (Object value : values) {
            String type = (value == null)
                ? "null"
                : value.getClass().getName();
            try {
                JsonObject valueObject = type.equals("com.ocb.json.JsonObject")
                    ? (JsonObject) value
                    : new JsonObject(TYPE_MAP.get(type), value);
                jsonValues.add(valueObject);
            }
            catch (NullPointerException e) {
                throw new Exception("Could not set " + value + " into JsonObject");
            }
        }
        this.setValue(this.type, jsonValues);
    }

    /**
     * Creates a JsonObject with type "bool"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(Boolean value) throws Exception {
        this(JsonTypes.bool, value);
    }

    /**
     * Creates a JsonObject with type "string"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(String value) throws Exception {
        this(JsonTypes.string, value);
    }

    /**
     * Creates a JsonObject with type "number_int"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(int value) throws Exception {
        this(JsonTypes.number_int, value);
    }

    /**
     * Creates a JsonObject with type "number_double"
     * @param type
     * @param value
     * @throws Exception
     */
    public JsonObject(double value) throws Exception {
        this(JsonTypes.number_double, value);
    }

    /**
     * @return Current value
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Set a new "object" value to the JsonObject
     * @param value
     * @throws Exception
     */
    public void setValue(Map<String, JsonObject> value) throws Exception {
        this.setValue(JsonTypes.object, value);
    }

    /**
     * Set a new "array" value to the JsonObject
     * @param value
     * @throws Exception
     */
    public void setValue(List<JsonObject> value) throws Exception {
        this.setValue(JsonTypes.array, value);
    }

    /**
     * Set a new "array" value to the JsonObject
     * @param value
     * @throws Exception
     */
    public void setValue(JsonObject[] value) throws Exception {
        this.setValue(JsonTypes.array, value);
    }

    /**
     * Set a new type and value to the JsonObject
     * @param type
     * @param value
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setValue(JsonTypes type, Object value) throws Exception {
        if (this.type != type) {
            this.setValue(type, null);
            this.type = type;
        }
        try{
            switch (this.type) {
                case array:
                    this.value = (List<JsonObject>) value;
                    break;
                case object:
                    this.value = (Map<String, JsonObject>) value;
                    break;
                case bool:
                    this.value = (boolean) value;
                    break;
                case string:
                    this.value = (String) value;
                    break;
                case number_int:
                    this.value = (int) value;
                    break;
                case number_double:
                    this.value = (double) value;
                    break;
                default:
                    this.value = null;
            }
        }
        catch (Exception e) {
            throw new Exception("entered value is not valid for given type");
        }
    }

    /**
     * Add a JsonObject to current value
     * @param value
     * @throws Exception
     * @implNote assumes current type is "array"
     */
    @SuppressWarnings("unchecked")
    public void addChild(JsonObject value) throws Exception {
        if (this.type == JsonTypes.object) {
            ((List<JsonObject>) this.value).add(value);
        }
        else {
            throw new Exception("Couldn't add child " + value + " to JsonType " + this.type);
        }
    }

    /**
     * Add a key-JsonObject pair to current value
     * @param value
     * @throws Exception
     * @implNote assumes current type is "object"
     */
    @SuppressWarnings("unchecked")
    public void addChild(String key, JsonObject value) throws Exception {
        if (this.type == JsonTypes.object) {
            ((Map<String, JsonObject>) this.value).put(key, value);
        }
        else {
            throw new Exception("Couldn't add child " + value + " to JsonType " + this.type);
        }
    }

    /**
     * @param key
     * @return related JsonObject of given key 
     * @throws Exception
     * @implNote assumes current type is "array"
     */
    public JsonObject getChild(int key) throws Exception {
        return this.getChild(Integer.toString(key));
    }

    /**
     * @param key
     * @return related JsonObject of given key 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public JsonObject getChild(String key) throws Exception {
        switch (this.type) {
            case array:
                int index = Integer.parseInt(key);
                return ((List<JsonObject>) this.value).get(index);
            case object:
                return ((Map<String, JsonObject>) this.value).get(key);
            default:
                throw new Exception("Couldn't find a child with key: " + key + " in Json type " + this.type);
        }
    }

    /**
     * @param key
     * @return related JsonObject of given keys
     * @throws Exception
     * @apiNote keys array is called recursively
     */
    public JsonObject getChild(String[] keys) throws Exception{
        JsonObject current = this;
        for (String key : keys) {
            current = this.getChild(key);
        }
        return current;
    }

    /**
     * Get String representation of the JsonObject
     * @implNote Strings come with quotation marks, to return a string without quotation marks use getValue()
     */
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        switch (this.type) {
            case object:
                HashMap<String, JsonObject> values = ((HashMap<String,JsonObject>) this.getValue());
                ArrayList<String> semi = new ArrayList<String>(values.size());
                values.forEach((key, value) -> semi.add("\"" + key + "\":" + value));
                return "{" + String.join(", ", semi) + "}";
            case string:
                return "\"" + this.getValue() + "\"";
            case array:
            case bool:
            case number_int:
            case number_double:
                return this.getValue().toString();
            default:
                return "null";
        }
    }
}
