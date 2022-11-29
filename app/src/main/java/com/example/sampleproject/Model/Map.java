package com.example.sampleproject.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Map {
    @SerializedName("options")
    @Expose
    public Object options;
    @SerializedName("version")
    @Expose
    public Integer version;
    @SerializedName("sources")
    @Expose
    public Object sources;
    @SerializedName("sprite")
    @Expose
    public String sprite;
    @SerializedName("glyphs")
    @Expose
    public String glyphs;
    @SerializedName("layers")
    @Expose
    public Object layers[];

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Object getSources() {
        return sources;
    }

    public void setSources(Object sources) {
        this.sources = sources;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getGlyphs() {
        return glyphs;
    }

    public void setGlyphs(String glyphs) {
        this.glyphs = glyphs;
    }

    public Object[] getLayers() {
        return layers;
    }

    public void setLayers(Object[] layers) {
        this.layers = layers;
    }

    @Override
    public String toString() {
        return "Map{" +
                "options=" + options +
                ", version=" + version +
                ", sources=" + sources +
                ", sprite='" + sprite + '\'' +
                ", glyphs='" + glyphs + '\'' +
                ", layers=" + Arrays.toString(layers) +
                '}';
    }
}
