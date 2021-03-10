package org.example.thinking.ioc.overview.domain;

import org.example.thinking.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;


/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/1/6 9:24 上午
 */
public class User implements BeanNameAware {

    private Long id;

    private String name;

    private City city;

    private City[] workCitys;

    private List<City> lifeCitys;

    private Resource configFileLocation;
    /**
     * 当前bean的名称
     */
    // 为了让它序列化或者反序列化，不要存储，可以加上一个transient这种方式来描述
    private transient String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCitys() {
        return workCitys;
    }

    public void setWorkCitys(City[] workCitys) {
        this.workCitys = workCitys;
    }

    public List<City> getLifeCitys() {
        return lifeCitys;
    }

    public void setLifeCitys(List<City> lifeCitys) {
        this.lifeCitys = lifeCitys;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCitys=" + Arrays.toString(workCitys) +
                ", lifeCitys=" + lifeCitys +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println("用户 Bean [" + beanName + "] 初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("用户Bean [" + beanName + "] 销毁中...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
