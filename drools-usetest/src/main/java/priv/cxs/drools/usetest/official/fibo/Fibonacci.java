package priv.cxs.drools.usetest.official.fibo;

import lombok.Data;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/4 下午5:25
 **/
public class Fibonacci {

    private int sequence;

    private int value;

    public Fibonacci() {
    }

    public Fibonacci(int sequence) {
        this.sequence = sequence;
        value = -1;
    }

    public Fibonacci(int sequence, int value) {
        this.sequence = sequence;
        this.value = value;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fibonacci fibonacci = (Fibonacci) o;

        return sequence == fibonacci.sequence;
    }

    @Override
    public int hashCode() {
        return sequence;
    }

    @Override
    public String toString() {
        return "Fibonacci{" +
                "sequence=" + sequence +
                ", value=" + value +
                '}';
    }
}
