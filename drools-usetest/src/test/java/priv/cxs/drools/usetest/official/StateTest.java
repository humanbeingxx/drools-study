package priv.cxs.drools.usetest.official;

import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.SessionUtil;
import priv.cxs.drools.usetest.official.state.State;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/4 下午4:18
 **/
public class StateTest {

    @Test
    public void test() {
        KieSession session = SessionUtil.getStatefulSession();

        State a = new State("A");
        State b = new State("B");
        State c = new State("C");

        session.insert(a);
        session.insert(b);
        session.insert(c);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("State"));
        session.dispose();
    }
}
