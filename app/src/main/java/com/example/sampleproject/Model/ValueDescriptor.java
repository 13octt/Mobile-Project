package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

public class ValueDescriptor {
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("jsonType")
    private String jsonType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsonType() {
        return jsonType;
    }

    public void setJsonType(String jsonType) {
        this.jsonType = jsonType;
    }

    @Override
    public String toString() {
        return "ValueDescriptor{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", jsonType='" + jsonType + '\'' +
                '}';
    }
}
