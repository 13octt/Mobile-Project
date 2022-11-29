package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

public class MetaItemDescriptor {
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

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

    @Override
    public String toString() {
        return "MetaItemDescriptor{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}