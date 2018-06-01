package priv.cxs.drools;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import priv.cxs.drools.usetest.drls.timetest.TimeCondition;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/27 下午11:57
 **/
public class TimerTest {

    @Test
    public void testTimer() throws InterruptedException {
        KieSession session = SessionUtil.getStatefulSession();

        TimeCondition condition = new TimeCondition();
        condition.setActive(true);
        condition.setName("timer running");

        FactHandle factHandle = session.insert(condition);

        new Thread(() -> session.fireUntilHalt(new RuleNameEqualsAgendaFilter("timer_run"))).start();

        Thread.sleep(3000);
        condition.setActive(false);
        session.update(factHandle, condition);

        Thread.sleep(3000);

        session.dispose();
    }


}
