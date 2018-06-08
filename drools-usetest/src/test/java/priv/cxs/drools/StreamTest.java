package priv.cxs.drools;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.time.SessionClock;
import priv.cxs.drools.usetest.drls.stream.Degree;
import priv.cxs.drools.usetest.drls.stream.DegreeThreshold;
import priv.cxs.drools.usetest.drls.stream.Fire;
import priv.cxs.drools.usetest.drls.stream.SprinklerActivated;

import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/7 下午3:29
 **/
public class StreamTest {

    @Test
    public void testWaitWithRealTime() throws InterruptedException {
        KieSession session = SessionUtil.getStreamSession();

        session.insert(new Fire());
        Thread.sleep(4000);
        session.insert(new SprinklerActivated());
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("stream :"));
    }

    @Test
    public void testWaitWithPseudoTime() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();
        KieBaseConfiguration baseConfiguration = kieServices.newKieBaseConfiguration();
        baseConfiguration.setOption(EventProcessingOption.STREAM);
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSessionConfiguration sessionConfiguration = kieServices.newKieSessionConfiguration();
        sessionConfiguration.setOption(ClockTypeOption.get("pseudo"));
        KieSession session = kieContainer.newKieBase("StreamBase", baseConfiguration).newKieSession(sessionConfiguration, null);

        session.insert(new Fire());
        SessionPseudoClock sessionClock = session.getSessionClock();
        sessionClock.advanceTime(4000, TimeUnit.SECONDS);
        session.insert(new SprinklerActivated());
        session.fireUntilHalt(new RuleNameStartsWithAgendaFilter("stream :"));
    }

    @Test
    public void testTimeWindowDoWork() {
        KieSession session = SessionUtil.getPseudoSession();
        SessionPseudoClock clock = session.getSessionClock();

        session.insert(new DegreeThreshold(50));
        session.insert(new Degree(10));
        session.insert(new Degree(20));
        session.insert(new Degree(30));
        clock.advanceTime(5, TimeUnit.SECONDS);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("stream : accumulate degree"));
    }

    @Test
    public void testTimeWindowNotWork() {
        KieSession session = SessionUtil.getPseudoSession();
        SessionPseudoClock clock = session.getSessionClock();

        session.insert(new DegreeThreshold(50));
        session.insert(new Degree(10));
        session.insert(new Degree(20));
        clock.advanceTime(20, TimeUnit.SECONDS);
        session.insert(new Degree(30));

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("stream : accumulate degree"));
    }

    @Test
    public void testLengthWindow() {
        KieSession session = SessionUtil.getPseudoSession();

        session.insert(new DegreeThreshold(50));
        session.insert(new Degree(10));
        session.insert(new Degree(20));
        session.insert(new Degree(30));

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("stream : accumulate degree by length"));
    }
}
