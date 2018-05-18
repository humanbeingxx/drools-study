package priv.cxs.drools.usetest.drls.fireroom;

import lombok.Builder;
import lombok.Data;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午1:53
 **/
@Data
@Builder
public class Sprinkler {

    private Room room;

    private boolean on;
}
