package priv.cxs.drools;

import org.junit.Test;
import org.kie.api.runtime.KieSession;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/25 下午9:45
 **/
public class EmptyTest {

    @Test
    public void fireEmpty() {
        KieSession session = SessionUtil.getStatefulSession();

        session.fireAllRules();
    }
}
