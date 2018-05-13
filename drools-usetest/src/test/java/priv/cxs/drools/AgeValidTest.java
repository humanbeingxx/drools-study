package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import priv.cxs.drools.usetest.Applicant;
import priv.cxs.drools.usetest.Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午1:17
 **/
public class AgeValidTest {

    private StatelessKieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newStatelessKieSession("KSession1_2");
    }

    @Test
    public void test() {
        StatelessKieSession session = getSession();

        Applicant applicant = Applicant.builder().name("cxs").age(23).build();
        Assert.assertFalse(applicant.isValid());

        session.execute(applicant);

        Assert.assertTrue(applicant.isValid());
    }

    @Test
    public void testMulti() throws ParseException {
        StatelessKieSession session = getSession();

        Applicant applicant = Applicant.builder().name("cxs").age(23).build();
        Assert.assertFalse(applicant.isValid());

        Date applyDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-01");
        Application application = Application.builder().dateApplied(applyDate).build();
        Assert.assertFalse(application.isValid());

        session.execute(Arrays.asList(applicant, application));

        Assert.assertTrue(applicant.isValid());
        Assert.assertTrue(application.isValid());

    }
}
