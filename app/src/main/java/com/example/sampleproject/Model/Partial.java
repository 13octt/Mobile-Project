package com.example.sampleproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partial {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("createdOn")
    @Expose
    private Long createdOn;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("accessPublicRead")
    @Expose
    private Boolean accessPublicRead;
    @SerializedName("parentId")
    @Expose
    private String parentId;
    @SerializedName("realm")
    @Expose
    private String realm;
    @SerializedName("type")
    @Expose
    private String type;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAccessPublicRead() {
        return accessPublicRead;
    }

    public void setAccessPublicRead(Boolean accessPublicRead) {
        this.accessPublicRead = accessPublicRead;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    @Override
    public String toString() {
        return "Partial Asset: " +
                "id='" + id + '\'' +
                "\n version=" + version +
                "\n createdOn=" + createdOn +
                "\n name='" + name + '\'' +
                "\n accessPublicRead=" + accessPublicRead +
                "\n parentId='" + parentId + '\'' +
                "\n realm='" + realm + '\'' +
                "\n type='" + type + '\'';
    }
}
