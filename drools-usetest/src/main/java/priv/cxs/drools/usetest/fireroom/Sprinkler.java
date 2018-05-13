package priv.cxs.drools.usetest.fireroom;

import lombok.Builder;
import lombok.Data;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
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
