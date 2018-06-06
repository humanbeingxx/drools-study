package priv.cxs.drools.usetest.official;

import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.SessionUtil;
import priv.cxs.drools.usetest.DroolsUtils;
import priv.cxs.drools.usetest.official.pricing.Driver;
import priv.cxs.drools.usetest.official.pricing.LocationRiskLevel;
import priv.cxs.drools.usetest.official.pricing.Policy;
import priv.cxs.drools.usetest.official.pricing.PolicyApplyType;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/5 下午3:19
 **/
public class PricingTest {

    @Test
    public void test() {
        Driver driver = new Driver();
        driver.setAge(23);
        driver.setPrior(1);
        driver.setRiskLevel(LocationRiskLevel.LOW.code());

        Policy policy = new Policy();
        policy.setApplyType(PolicyApplyType.COMPREHENSIVE.getText());

        KieSession session = SessionUtil.getSheetSession();

        session.insert(driver);
        session.insert(policy);

        session.fireAllRules(new RuleNameStartsWithAgendaFilter("PricingRule"));

        Assert.assertEquals(450, policy.getPrice());
    }
    @Test
    public void testParse() {
        System.out.println(DroolsUtils.translateTable("/dtable/pricing_rule.xlsx"));
    }
}
