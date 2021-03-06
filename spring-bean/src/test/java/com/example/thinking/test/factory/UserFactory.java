package com.example.thinking.test.factory;

import org.example.thinking.ioc.overview.domain.User;

public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }
}
