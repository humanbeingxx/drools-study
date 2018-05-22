package priv.cxs.drools;

import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.junit.Test;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import priv.cxs.drools.usetest.drls.ageinfer.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/21 下午10:29
 **/
public class GroupAndEventTest {

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

    @Test
    public void testActivationGroupOrder() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert(Person.builder().age(30).build());

        session.fireAllRules();
    }

    @Test
    public void testEvent() {
        KieSession session = SessionUtil.getStatefulSession();

        session.addEventListener(new DebugRuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                System.out.println("---------------------");
                System.out.println(event.getFactHandle().toString());
                System.out.println("---------------------");
            }
        });

        session.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void matchCreated(MatchCreatedEvent event) {
                System.out.println("************************");
                System.out.println(event.getMatch().getRule().getName());
                System.out.println("************************");
            }
        });

        session.insert(Person.builder().age(30).build());

        session.fireAllRules(new RuleNameMatchesAgendaFilter("Test Event.*"));
    }
}
