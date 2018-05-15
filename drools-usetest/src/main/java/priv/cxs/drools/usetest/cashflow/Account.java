package priv.cxs.drools.usetest.cashflow;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
