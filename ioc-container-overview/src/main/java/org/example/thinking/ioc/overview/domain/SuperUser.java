package org.example.thinking.ioc.overview.domain;

import org.example.thinking.ioc.overview.annotation.Super;

/**
 * @Author：zzh
 * @Description: 超级用户
 * @Date: 2020/12/31 9:24 下午
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
