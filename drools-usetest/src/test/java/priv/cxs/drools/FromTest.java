package priv.cxs.drools;

import com.google.common.collect.Lists;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.drls.fromcaution.Address;
import priv.cxs.drools.usetest.drls.fromcaution.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/1 下午10:09
 **/
public class FromTest {

    @Test
    public void testFrom() {
        KieSession session = SessionUtil.getStatefulSession();

        Person person = new Person();
        Address address = new Address();
        address.setStreet("HD");
        person.setAddress(address);

        session.insert(person);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("caution_from"));
    }

    @Test
    public void testFromSelf() {
        KieSession session = SessionUtil.getStatefulSession();

        Person person = new Person();
        person.setTitles(Lists.newArrayList("Sir", "gentle", "hero"));

        session.insert(person);

        Person person1 = new Person();
        System.out.println("person1" + person1);
        Person person2 = new Person();
        System.out.println("person2" + person2);
        session.insert(person1);
        session.insert(person2);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("caution_from_self"));
    }

    @Test
    public void testCollect() {
        KieSession session = SessionUtil.getStatefulSession();

        Person person1 = new Person();
        person1.setGender("m");
        person1.setTitles(Lists.newArrayList("Sir", "gentle", "hero"));

        Person person2 = new Person();
        person2.setGender("m");
        person2.setTitles(Lists.newArrayList("Sir", "rough", "hero"));

        Person person3 = new Person();
        person3.setGender("f");
        person3.setTitles(Lists.newArrayList("Princess", "elegant"));

        session.insert(person1);
        session.insert(person2);
        session.insert(person3);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("collect_person_gender"));
    }

    @Test
    public void testAccumulate() {
        KieSession session = SessionUtil.getStatefulSession();

        Person person = new Person();
        person.setGender("m");
        person.setTitles(Lists.newArrayList("Sir", "gentle", "hero"));

        session.insert(person);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("collect_accumulate"));
    }
}
