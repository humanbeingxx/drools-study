package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.DroolsUtils;
import priv.cxs.drools.usetest.sheet.ComplicatedUse;
import priv.cxs.drools.usetest.sheet.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/15 下午10:55
 **/
public class SheetRulesTest {

    /**
     * 按照以前的说法，不是应该会迭代执行规则吗？为什么停在了18，上面的test。
     * 如果是写drls文件，则可以。
     */
    @Test
    public void test() {
        Person person = new Person();
        person.setAge(17);
        person.setGender("male");

        KieSession session = SessionUtil.getSheetSession();

        session.insert(person);
        session.fireAllRules();


        /*
          我错了。drls里面 == 和 : 的含义不一样。
          == 是判断作用，当左右相等时才成立
          : 是赋值作用，没有判断功能
          这个case中，最后一个rule哪怕是写个Person(age:2321312)，也能命中。
         */

        /*
          经过一番修正，现在可以迭代执行了，主要变化时在action里面使用了modify。
          这样规则引擎可以识别到fact的变化。
         */

        Assert.assertEquals(99, person.getAge());


    }

    @Test
    public void testComplicate() {
        ComplicatedUse complicatedUse = new ComplicatedUse();

        complicatedUse.setKey1("1");
        complicatedUse.setKey2("x");

        KieSession session = SessionUtil.getStatefulSession();

        session.insert(complicatedUse);

        session.fireAllRules();

        System.out.println(complicatedUse);
    }

    @Test
    public void testSeeComplicateFile() {
        System.out.println(DroolsUtils.translateTable("/rules/complicated_rule.xlsx"));
    }
}
