package priv.cxs.drools.usetest.cashflow;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 下午3:44
 **/
@Data
@Builder
public class CashFlow {

    public static final int CREDIT = 1;

    public static final int DEBIT = 2;

    private Date date;

    private double amount;

    private int type;

    private long accountNo;
}
