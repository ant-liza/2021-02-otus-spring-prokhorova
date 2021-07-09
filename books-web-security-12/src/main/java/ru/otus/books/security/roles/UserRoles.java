package ru.otus.books.security.roles;

public enum UserRoles {
    ADMIN("ADMIN"),
    TEST("TEST"),
    USER("USER");
    public String roleName;

    UserRoles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
