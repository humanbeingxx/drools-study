package priv.cxs.drools.usetest.official;

import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.SessionUtil;
import priv.cxs.drools.usetest.official.politician.Politician;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/5 下午5:51
 **/
public class PoliticianTest {

    @Test
    public void test() {
        Politician blair = new Politician("blair", true);
        Politician bush = new Politician("bush", true);
        Politician chirac = new Politician("chirac", true);
        Politician schroder = new Politician("schroder", true);

        KieSession session = SessionUtil.getStatefulSession();

        session.insert( blair );
        session.insert( bush );
        session.insert( chirac );
        session.insert( schroder );
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("PoliticianRule"));
    }
}
