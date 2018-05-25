package priv.cxs.drools;

import com.google.common.collect.Maps;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

import java.util.Date;
import java.util.HashMap;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/24 下午3:38
 **/
public class TypeDeclareTest {

    @Test
    public void test() {
        KieSession session = SessionUtil.getStatefulSession();

        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("name", "this is name");
        map.put("age", 12);
        map.put("birth", new Date());
        session.insert(map);

        session.fireAllRules();
    }

    @Test
    public void testKey() {
        KieSession session = SessionUtil.getStatefulSession();
        session.fireAllRules(new RuleNameEqualsAgendaFilter("type_declare_key"));
    }

    @Test
    public void testPosition() throws IllegalAccessException, InstantiationException {
        KieSession session = SessionUtil.getStatefulSession();

        org.kie.api.definition.type.FactType factType = session.getKieBase().getFactType(
                "priv.cxs.drools.usetest.drls.declaration",
                "Something");
        Object sth = factType.newInstance();

        factType.set(sth, "type", "person");
        factType.set(sth, "name", "type_declare_position");
        factType.set(sth, "value", "no position for me");

        session.insert(sth);

        session.fireAllRules(new RuleNameEqualsAgendaFilter("type_declare_position"));
    }
}
