package priv.cxs.drools.usetest.ageinfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/15 下午3:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public final static int CHILD = 1;
    public final static int ADULT = 2;

    private int age;

    private int passType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsChild {

        private Person person;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsAdult {

        private Person person;

    }

}
