package priv.cxs.drools;

import org.junit.Test;
import priv.cxs.drools.usetest.DroolsUtils;

import static org.junit.Assert.*;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/18 下午3:02
 **/
public class DroolsUtilsTest {
    @Test
    public void testTranslateTable() throws Exception {
        System.out.println(DroolsUtils.translateTable("/rules/sheet_drools.xlsx"));
    }

}