package priv.cxs.drools.usetest.cashflow;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 下午3:45
 **/
@Data
@Builder
public class AccountPeriod {

    private Date startDate;

    private Date endDate;
}
