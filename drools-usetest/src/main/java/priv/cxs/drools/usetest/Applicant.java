package priv.cxs.drools.usetest;

import lombok.Builder;
import lombok.Data;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午1:11
 **/
@Data
@Builder
public class Applicant {

    private String name;

    private int age;

    private boolean valid;
}