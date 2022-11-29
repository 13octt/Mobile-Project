package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

public class AssetDescriptor {

    @SerializedName("descriptorType")
    private  String descriptorType;
    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("colour")
    private String colour;

    public String getDescriptorType() {
        return descriptorType;
    }

    public void setDescriptorType(String descriptorType) {
        this.descriptorType = descriptorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "AssetDescriptor{" +
                "descriptorType='" + descriptorType + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
