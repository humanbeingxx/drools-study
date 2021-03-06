package priv.cxs.drools.usetest.drls;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午1:38
 **/
@Data
@Builder
public class Application {

    private Date dateApplied;

    private boolean valid;
}
