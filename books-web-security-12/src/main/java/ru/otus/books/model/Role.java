package ru.otus.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Role.COLLECTION_NAME)
public class Role {
    public static final String COLLECTION_NAME = "roles";

    @Id
    private String roleId;

    @Field(name = "name")
    private String name;

    @Field(name = "description")
    private String description;

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
