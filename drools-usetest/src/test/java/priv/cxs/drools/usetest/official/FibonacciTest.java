package priv.cxs.drools.usetest.official;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.SessionUtil;
import priv.cxs.drools.usetest.official.fibo.Fibonacci;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/4 下午5:25
 **/
public class FibonacciTest {

    @Test
    public void test() {
        KieSession session = SessionUtil.getStatefulSession();

        Fibonacci first = new Fibonacci(5);
        first.setValue(1);
        Fibonacci second = new Fibonacci(4);
        second.setValue(1);
        Fibonacci result = new Fibonacci(-99);
        result.setValue(-1);

        session.insert(first);
        session.insert(second);
        session.insert(result);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("my_fibonacci"));

        Assert.assertEquals(-99, result.getSequence());
        Assert.assertEquals(8, result.getValue());
    }

    @Test
    public void testManual() {
        Fibonacci result = calculate(4);
        System.out.println(result);
    }

    private Fibonacci calculate(int sequence) {
        Fibonacci first = new Fibonacci(sequence);
        first.setValue(1);
        Fibonacci second = new Fibonacci(sequence - 1);
        second.setValue(1);

        return calculateRec(first, second);
    }

    private Fibonacci calculateRec(Fibonacci first, Fibonacci second) {
        if (second.getSequence() < 1) {
            return second;
        }
        Fibonacci next = new Fibonacci(second.getSequence() - 1);
        next.setValue(first.getValue() + second.getValue());
        next = calculateRec(second, next);
        return next;
    }
}
