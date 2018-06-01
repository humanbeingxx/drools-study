package priv.cxs.drools;

import com.google.common.collect.Lists;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.drls.Applicant;
import priv.cxs.drools.usetest.drls.Applicant2;
import priv.cxs.drools.usetest.drls.manyrules.*;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/25 下午9:52
 **/
public class ManyRuleTest {

    @Test
    public void testExistTrue() {
        KieSession session = SessionUtil.getStatefulSession();

        Policy policy = new Policy();
        Driver driver = new Driver();
        driver.setAge(20);
        session.insert(policy);
        session.insert(driver);

        session.fireAllRules(new RuleNameEqualsAgendaFilter("Many Rule 1"));
        Assert.assertTrue(policy.isApproved());
    }

    @Test
    public void testExistFalseWithAge() {
        KieSession session = SessionUtil.getStatefulSession();

        Policy policy = new Policy();
        Driver driver = new Driver();
        driver.setAge(10);
        session.insert(policy);
        session.insert(driver);

        session.fireAllRules(new RuleNameEqualsAgendaFilter("Many Rule 1"));
        Assert.assertFalse(policy.isApproved());
    }

    @Test
    public void testExistFalseWithRejection() {
        KieSession session = SessionUtil.getStatefulSession();

        Policy policy = new Policy();
        Driver driver = new Driver();
        driver.setAge(20);
        Rejection rejection = new Rejection();
        session.insert(policy);
        session.insert(driver);
        session.insert(rejection);

        session.fireAllRules(new RuleNameEqualsAgendaFilter("Many Rule 1"));
        Assert.assertFalse(policy.isApproved());
    }

    @Test
    public void testLoop() {
        KieSession session = SessionUtil.getStatefulSession();

        session.insert(new Policy());

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("Many Rule 2"));
    }

    @Test
    public void testEval() {
        SessionUtil.getStatefulSession().fireAllRules(new RuleNameEqualsAgendaFilter("eval(true)"));
    }

    @Test
    public void testValueBind() {
        Applicant applicant = Applicant.builder().age(16).build();

        KieSession session = SessionUtil.getStatefulSession();
        session.insert(applicant);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("value_bind"));
    }

    @Test
    public void testUnification() {
        Applicant applicant = Applicant.builder().age(16).build();
        Applicant2 applicant2 = Applicant2.builder().age(8).build();


        KieSession session = SessionUtil.getStatefulSession();
        session.insert(applicant);
        session.insert(applicant2);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("unification"));
    }

    @Test
    public void testOrFire() {
        Applicant applicant = Applicant.builder().age(16).build();


        KieSession session = SessionUtil.getStatefulSession();
        session.insert(applicant);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("or_fire"));
    }

    @Test
    public void testExistFire() {
        Applicant applicant1 = Applicant.builder().age(16).build();
        Applicant applicant2 = Applicant.builder().age(16).build();
        Applicant applicant3 = Applicant.builder().age(16).build();


        KieSession session = SessionUtil.getStatefulSession();
        session.insert(applicant1);
        session.insert(applicant2);
        session.insert(applicant3);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("exist_fire"));
    }

    @Test
    public void testForAllFire() {
        Applicant applicant1 = Applicant.builder().age(16).build();
        Applicant applicant2 = Applicant.builder().age(16).build();
        Applicant applicant3 = Applicant.builder().age(16).build();


        KieSession session1 = SessionUtil.getStatefulSession();
        session1.insert(applicant1);
        session1.insert(applicant2);
        session1.insert(applicant3);

        session1.fireAllRules(new RuleNameStartsWithAgendaFilter("forall_fire"));

        Applicant applicant4 = Applicant.builder().age(16).build();
        Applicant applicant5 = Applicant.builder().age(17).build();
        Applicant applicant6 = Applicant.builder().age(18).build();


        KieSession session2 = SessionUtil.getStatefulSession();
        session2.insert(applicant4);
        session2.insert(applicant5);
        session2.insert(applicant6);
        session2.fireAllRules(new RuleNameStartsWithAgendaFilter("forall_fire"));
    }

    @Test
    public void testFrom001() {
        KieSession session = SessionUtil.getStatefulSession();

        Airplane airplane1 = Airplane.withPilot("747", "cxs001", Lists.newArrayList());
        Pilot pilot = new Pilot();
        pilot.setName("cxs");
        session.insert(airplane1);
        session.insert(pilot);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("From Rule 001"));

        Airplane airplane2 = Airplane.withPilot("747", "cxs", Lists.newArrayList());
        session.insert(airplane2);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("From Rule 001"));
    }

    @Test
    public void testFrom002() {
        KieSession session = SessionUtil.getStatefulSession();

        Airplane airplane1 = Airplane.withPilot("747", "cxs", Lists.newArrayList("cxs001", "cxs002"));
        session.insert(airplane1);
        session.fireAllRules(new RuleNameEqualsAgendaFilter("From Rule 002"));
    }
}
