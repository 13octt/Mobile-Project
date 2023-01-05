package com.example.sampleproject.Model;

public class User {

    private String realm;
    private String realmId;
    private String id;
    private String enabled;
    private String createdOn;
    private String serviceAccount;
    private String username;

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "realm='" + realm + '\'' +
                ", realmId='" + realmId + '\'' +
                ", id='" + id + '\'' +
                ", enabled='" + enabled + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", serviceAccount='" + serviceAccount + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

