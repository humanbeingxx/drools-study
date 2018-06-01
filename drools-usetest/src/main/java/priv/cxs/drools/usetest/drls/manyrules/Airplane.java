package priv.cxs.drools.usetest.drls.manyrules;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/30 下午3:02
 **/
@Data
public class Airplane {

    private String name;

    private Pilot pilot;

    private List<Pilot> vicePilots;

    public static Airplane withPilot(String planeName, String pilotName, List<String> vicePilotNames) {
        Airplane airplane = new Airplane();
        airplane.setName(planeName);
        airplane.setPilot(pilot(pilotName));

        List<Pilot> vices = Lists.newArrayList();
        for (String vicePilotName : vicePilotNames) {
            vices.add(pilot(vicePilotName));
        }
        airplane.setVicePilots(vices);

        return airplane;
    }

    private static Pilot pilot(String name) {
        Pilot pilot = new Pilot();
        pilot.setName(name);
        return pilot;
    }
}
