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
        Assert.assertEquals(18, person.getAge());
    }

    /**
     * 按照以前的说法，不是应该会迭代执行规则吗？为什么停在了18，上面的test。
     * 如果是写drls文件，则可以。
     */
    @Test
    public void whtNotIter() {
        Person person = new Person();
        person.setAge(17);

        KieSession session = SessionUtil.getStatefulSession();

        session.insert(person);
        int rules = session.fireAllRules();
        System.out.println(rules);

        Assert.assertEquals(99, person.getAge());

        /**
         * 我错了。drls里面 == 和 : 的含义不一样。
         * == 是判断作用，当左右相等时才成立
         * : 是赋值作用，没有判断功能
         * 这个case中，最后一个rule哪怕是写个Person(age:2321312)，也能命中。
         */
        Assert.fail();
    }
}
