package priv.cxs.drools;

import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.drls.ageinfer.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/21 下午10:29
 **/
public class AgendaGroupTest {

    @Test
    public void testMultiMatch() {
        KieSession session = SessionUtil.getStatefulSession();

        Person person = Person.builder().age(10).build();
        session.insert(person);

        session.fireAllRules();
    }

    @Test
    public void testAgendaGroup() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert(Person.builder().age(20).build());
        session.fireAllRules();

        session.getAgenda().getAgendaGroup("first").setFocus();
        session.fireAllRules();

        session.getAgenda().getAgendaGroup("second").setFocus();
        session.fireAllRules();
    }

    @Test
    public void testAgendaGroupOrder() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert(Person.builder().age(20).build());
        session.getAgenda().getAgendaGroup("first").setFocus();
        session.getAgenda().getAgendaGroup("second").setFocus();

        session.fireAllRules();
    }
}
