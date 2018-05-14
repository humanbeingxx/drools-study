package priv.cxs.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午11:31
 **/
public class SessionUtil {

    static StatelessKieSession getStatelessSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newStatelessKieSession("KSession1_2");
    }

    static KieSession getStatefulSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        return kieContainer.newKieSession("KSession1_1");
    }
}
