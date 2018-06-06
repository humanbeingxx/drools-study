package priv.cxs.drools.usetest.official.pricing;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/5 下午2:58
 **/
public enum LocationRiskLevel {
    LOW(0), MED(1), HIGH(2);

    int code;

    LocationRiskLevel(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
