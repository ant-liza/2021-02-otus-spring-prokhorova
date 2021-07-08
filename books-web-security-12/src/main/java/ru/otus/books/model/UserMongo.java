package ru.otus.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = UserMongo.COLLECTION_NAME)
public class UserMongo {
    public static final String COLLECTION_NAME = "users";
    @Id
    private String userId;
    @Field(name = "password")
    private String password;
    @Field(name = "username")
    private String userName;

    public UserMongo() {
    }

    public UserMongo(String username, String password) {
        this.password = password;
        this.userName = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

