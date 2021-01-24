package org.example.thinking.bean.factory;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author：zzh
 * @Description: {@link User} Bean的{@link org.springframework.beans.factory.FactoryBean}实现
 * @Date: 2021/1/6 1:27 下午
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
