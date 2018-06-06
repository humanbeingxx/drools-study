package priv.cxs.drools.usetest.official.politician;

import lombok.Data;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/5 下午5:46
 **/
@Data
public class Politician {
    private String name;
    private boolean honest;

    public Politician(String name, boolean honest) {
        this.name = name;
        this.honest = honest;
    }
}