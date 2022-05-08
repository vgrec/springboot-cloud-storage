package com.udacity.jwdnd.course1.cloudstorage.data.forms;

import com.udacity.jwdnd.course1.cloudstorage.data.UserData;

public class CredentialForm implements UserData {
    private Integer credentialId;
    private String key;
    private String username;
    private String password;
    private String url;

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[")
                .append("credential_id: " + credentialId + ", ")
                .append("key: " + key + ", ")
                .append("url: " + url + ", ")
                .append("username: " + username + ", ")
                .append("password: " + password)
                .append("]")
                .toString();
    }
}
