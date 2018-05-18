package priv.cxs.drools.usetest;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/18 下午3:00
 **/
public class DroolsUtils {

    public static String translateTable(String path) {
        SpreadsheetCompiler spreadsheetCompiler = new SpreadsheetCompiler();

        return spreadsheetCompiler.compile(path, InputType.XLS);
    }
}
