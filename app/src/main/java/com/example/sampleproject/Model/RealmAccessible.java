package com.example.sampleproject.Model;

import androidx.annotation.NonNull;

public class RealmAccessible {
    private String name;
    private String displayName;


    public RealmAccessible(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RealmAccessible{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
