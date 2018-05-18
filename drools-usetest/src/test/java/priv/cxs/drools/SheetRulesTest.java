package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.sheet.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/15 下午10:55
 **/
public class SheetRulesTest {

    @Test
    public void test() {
        Person person = new Person();
        person.setAge(17);
        person.setGender("male");

        KieSession session = SessionUtil.getSheetSession();


        session.insert(person);
        session.fireAllRules();

        session.dispose();
        Assert.assertEquals("red", person.getColor());
    }
}
