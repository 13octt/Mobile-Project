package com.example.sampleproject.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Asset {
    @SerializedName("id")
    public String id;
    @SerializedName("version")
    public String version;
    @SerializedName("createdOn")
    public String createdOn;
    @SerializedName("name")
    public String name;
    @SerializedName("accessPublicRead")
    public String accessPublicRead;
    @SerializedName("parentID")
    public String parentID;
    @SerializedName("realm")
    public String realm;
    @SerializedName("type")
    public String type;
    @SerializedName("path")
    public String path[];
    @SerializedName("attributes")
    public Attributes attributes;

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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
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

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", name='" + name + '\'' +
                ", accessPublicRead='" + accessPublicRead + '\'' +
                ", parentID='" + parentID + '\'' +
                ", realm='" + realm + '\'' +
                ", type='" + type + '\'' +
                ", path=" + Arrays.toString(path) +
                ", attributes=" + attributes +
                '}';
    }
}
