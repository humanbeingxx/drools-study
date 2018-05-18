package priv.cxs.drools.usetest.drls.cashflow;

import lombok.Builder;
import lombok.Data;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 下午3:45
 **/
@Data
@Builder
public class Account {

    private long accountNo;

    private double balance;
}
