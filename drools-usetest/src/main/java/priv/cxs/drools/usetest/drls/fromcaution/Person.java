package priv.cxs.drools.usetest.drls.fromcaution;

import lombok.Data;

import java.util.List;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/1 下午9:28
 **/
@Data
public class Person {

    private String name;

    private String gender;

    private Address address;

    private String prefix;

    private String suffix;

    private List<String> titles;
}
