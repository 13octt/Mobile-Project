package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

public class AssetUserCurrent {
    @SerializedName("id")
    private String id;
    @SerializedName("version")
    private String version;
    @SerializedName("createdOn")
    private String createdOn;
    @SerializedName("name")
    private String name;
    @SerializedName("accessPublicRead")
    private String accessPublicRead;
    @SerializedName("realm")
    private String realm;
    @SerializedName("type")
    private String type;
    @SerializedName("attribute")
    private Object attribute;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessPublicRead() {
        return accessPublicRead;
    }

    public void setAccessPublicRead(String accessPublicRead) {
        this.accessPublicRead = accessPublicRead;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getAttribute() {
        return attribute;
    }

    public void setAttribute(Object attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AssetUserCurrent{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", name='" + name + '\'' +
                ", accessPublicRead='" + accessPublicRead + '\'' +
                ", realm='" + realm + '\'' +
                ", type='" + type + '\'' +
                ", attribute=" + attribute +
                '}';
    }
}
