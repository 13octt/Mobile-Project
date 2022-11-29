package com.example.sampleproject.Model;

public class Info {

    private String version;
    private String authServerUrl;

    public Info(String version, String authServerURL) {
        this.version = version;
        this.authServerUrl = authServerURL;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthServerURL() {
        return authServerUrl;
    }

    public void setAuthServerURL(String authServerURL) {
        this.authServerUrl = authServerURL;
    }

    @Override
    public String toString() {
        return "Info{" +
                "version='" + version + '\'' +
                ", authServerURL='" + authServerUrl + '\'' +
                '}';
    }
}
