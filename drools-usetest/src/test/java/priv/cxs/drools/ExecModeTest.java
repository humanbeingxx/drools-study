package priv.cxs.drools;

import lombok.Data;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import priv.cxs.drools.usetest.drls.ageinfer.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/22 下午5:56
 **/
public class ExecModeTest {

    @Test
    public void testActiveMode() throws InterruptedException {
        KieSession session = SessionUtil.getStatefulSession();

        new Thread(() -> session.fireUntilHalt(new RuleNameMatchesAgendaFilter("Test Mode.*"))).start();

        Person person = Person.builder().age(10).build();
        session.insert(person);
        Thread.sleep(100);

        session.submit(kieSession -> {
            FactHandle handle = session.getFactHandle(person);
            person.setAge(20);
            kieSession.update(handle, person);
            kieSession.insert(Person.builder().age(30).build());
        });

        Thread.sleep(100);

        session.halt();
    }

    @Test
    public void testPropagationModeBefore() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert(1);
        session.insert("1");

        session.fireAllRules();
    }

    @Test
    public void testPropagationModeAfter() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert("1");
        session.insert(1);

        session.fireAllRules();
    }
}
