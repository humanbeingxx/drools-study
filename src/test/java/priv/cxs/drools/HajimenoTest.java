package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.conf.ClockTypeOption;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/10 下午8:39
 **/
public class HajimenoTest {

    @Test
    public void testContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieBase kieBase = kieClasspathContainer.getKieBase("KBase1");
        StatelessKieSession kSession2_1 = kieClasspathContainer.newStatelessKieSession("KSession2_2");
    }

    @Test
    public void testManual() {
        KieServices kieServices = KieServices.Factory.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

        KieBaseModel kBaseModel = kieModuleModel.newKieBaseModel("KBase")
                .setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
                .setEventProcessingMode(EventProcessingOption.STREAM);

        KieSessionModel kieSessionModel = kBaseModel.newKieSessionModel("KSession")
                .setType(KieSessionModel.KieSessionType.STATELESS)
                .setClockType(ClockTypeOption.get("realtime"));

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());

        kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kBase = kieContainer.getKieBase("KBase");
        StatelessKieSession kSession = kieContainer.newStatelessKieSession("KSession");
        Assert.assertTrue(kSession.getKieBase() == kBase);

    }
}
