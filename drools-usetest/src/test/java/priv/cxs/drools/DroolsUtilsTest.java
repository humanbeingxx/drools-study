package priv.cxs.drools;

import org.junit.Test;
import priv.cxs.drools.usetest.DroolsUtils;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/18 下午3:02
 **/
public class DroolsUtilsTest {
    @Test
    public void testTranslateTable() throws Exception {
        System.out.println(DroolsUtils.translateTable("/dtable/sheet_drools.xlsx"));
    }

}