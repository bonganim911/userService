package com.digicertbongani.userservice.util;

import com.digicertbongani.userservice.model.User;

public class UserBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private UserBuilder() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        return new User(id, firstName, lastName, email);
    }

}
