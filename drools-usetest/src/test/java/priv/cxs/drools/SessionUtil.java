package priv.cxs.drools;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午11:31
 **/
public class SessionUtil {

    public static StatelessKieSession getStatelessSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newStatelessKieSession("KSession1_2");
    }

    public static KieSession getStatefulSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newKieSession("KSession1_1");
    }

    public static KieSession getSheetSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        return kieContainer.newKieSession("sheetSession");
    }

    public static KieSession getStreamSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newKieSession("streamSession");
    }

    public static KieSession getPseudoSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newKieSession("pseudoSession");
    }
}
