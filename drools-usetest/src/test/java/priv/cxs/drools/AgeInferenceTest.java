package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.ageinfer.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/15 下午3:11
 **/
public class AgeInferenceTest {

    @Test
    public void test() {
        KieSession session = SessionUtil.getStatefulSession();

        Person child = new Person();
        child.setAge(13);

        Person adult = new Person();
        adult.setAge(33);

        session.insert(child);
        session.insert(adult);

        session.fireAllRules();

        Assert.assertEquals(1, child.getPassType());
        Assert.assertEquals(2, adult.getPassType());

        child.setAge(23);

        session.insert(child);
        session.fireAllRules();

        Assert.assertEquals(2, child.getPassType());
    }
}
