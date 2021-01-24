package org.example.thinking.bean.factory;

import org.example.thinking.ioc.overview.domain.User;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/1/6 1:16 下午
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
