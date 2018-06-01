package priv.cxs.drools;

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
}
