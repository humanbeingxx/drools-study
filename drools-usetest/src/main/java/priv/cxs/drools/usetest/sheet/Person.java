package priv.cxs.drools.usetest.sheet;

import lombok.*;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/15 下午10:52
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int age;

    private String gender;

    private String color;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
