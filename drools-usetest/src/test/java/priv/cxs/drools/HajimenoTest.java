package priv.cxs.drools;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.conf.ClockTypeOption;

import java.io.File;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/10 下午8:39
 **/
public class HajimenoTest {

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

    @Test
    public void testStepByStep() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("out", "this is out");
        kieSession.insert(Lists.newArrayList(1, 2, 3));
        kieSession.fireAllRules();
        Globals globals = kieSession.getGlobals();
        System.out.println(globals.getGlobalKeys());
    }

    @Test
    public void testMultiSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSession kSession1_1 = kieContainer.newKieSession("KSession1_1");
        StatelessKieSession kSession1_2 = kieContainer.newStatelessKieSession("KSession1_2");

        kSession1_1.setGlobal("out", "out fromcaution kSession1_1");
        Assert.assertEquals("out fromcaution kSession1_1", kSession1_1.getGlobal("out"));

        kSession1_2.setGlobal("out", "out fromcaution kSession1_2");
        Assert.assertEquals("out fromcaution kSession1_2", kSession1_2.getGlobals().get("out"));
        Assert.assertEquals("out fromcaution kSession1_1", kSession1_1.getGlobal("out"));
    }

    @Test
    public void testUseRemote() {
        KieServices kieServices = KieServices.Factory.get();
        //必须引用kie-ci
        KieContainer kieContainer = kieServices.newKieContainer(
                kieServices.newReleaseId("priv.cxs", "drools-repo", "LATEST"));

        Assert.assertNotNull(kieContainer);

    }
}
