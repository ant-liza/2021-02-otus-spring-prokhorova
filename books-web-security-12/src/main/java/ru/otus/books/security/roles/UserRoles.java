package ru.otus.books.security.roles;

public enum UserRoles {
    ADMIN("admin"),
    TEST("test"),
    USER("user");
    public String roleName;

    UserRoles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
