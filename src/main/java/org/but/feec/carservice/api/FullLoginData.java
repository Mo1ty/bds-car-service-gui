package org.but.feec.carservice.api;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullLoginData {
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

    public static FullLoginData turnIntoLoginData(ResultSet resultSet) throws SQLException {
        FullLoginData person = new FullLoginData();
        person.setEmail(resultSet.getString("email"));
        person.setPasswordHash(resultSet.getString("password_hash"));
        return person;
    }
}