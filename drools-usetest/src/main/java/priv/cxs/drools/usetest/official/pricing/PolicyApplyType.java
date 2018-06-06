package priv.cxs.drools.usetest.official.pricing;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/5 下午3:18
 **/
public enum PolicyApplyType {
    COMPREHENSIVE("comprehensive"),
    FIRE_THEFT("fire_theft"),
    THIRD_PARTY("third_party");

    String text;


    PolicyApplyType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
