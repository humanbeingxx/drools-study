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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Politician that = (Politician) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}