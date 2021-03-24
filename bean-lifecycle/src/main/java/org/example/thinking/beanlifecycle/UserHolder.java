package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;

/**
 * User Holder类
 */
public class UserHolder {

    private final User user;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
