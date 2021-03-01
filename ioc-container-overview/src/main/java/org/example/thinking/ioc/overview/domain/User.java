package org.example.thinking.ioc.overview.domain;

import org.example.thinking.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;


/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/1/6 9:24 上午
 */
public class User {

    private Long id;

    private String name;

    private City city;

    private City[] workCitys;

    private List<City> lifeCitys;

    private Resource configFileLocation;

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
}
