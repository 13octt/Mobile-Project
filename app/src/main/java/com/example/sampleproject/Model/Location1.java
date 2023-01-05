
package com.example.sampleproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location1 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private Value__1 value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value__1 getValue() {
        return value;
    }

    public void setValue(Value__1 value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
