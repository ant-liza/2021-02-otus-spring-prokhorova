package ru.otus.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = UserRoles.COLLECTION_NAME)
public class UserRoles {
    public static final String COLLECTION_NAME = "user_roles";

    @Id
    private String userRolesId;

    @Field(name = "username")
    private String userName;

    @Field(name = "role")
    private String role;

    public UserRoles() {
    }

    public UserRoles(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(String userRolesId) {
        this.userRolesId = userRolesId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
