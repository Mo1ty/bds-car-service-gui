package org.but.feec.carservice.api;

import java.util.Arrays;

public class ClientsLoginView {
    private String email;
    private char[] passwordHash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(char[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "FullLoginData{" +
                "email='" + email + '\'' +
                ", password='" + Arrays.toString(passwordHash) + '\'' +
                '}';
    }
}