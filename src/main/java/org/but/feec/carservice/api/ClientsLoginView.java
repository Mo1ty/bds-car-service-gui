package org.but.feec.carservice.api;

public class ClientsLoginView {
    private String email;
    private String passwordHash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "FullLoginData{" +
                "email='" + email + '\'' +
                ", password='" + passwordHash + '\'' +
                '}';
    }
}