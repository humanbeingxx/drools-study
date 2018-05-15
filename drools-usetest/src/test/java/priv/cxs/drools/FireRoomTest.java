package priv.cxs.drools;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import priv.cxs.drools.usetest.fireroom.Fire;
import priv.cxs.drools.usetest.fireroom.Room;
import priv.cxs.drools.usetest.fireroom.Sprinkler;

import java.util.HashMap;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 上午1:57
 **/
public class FireRoomTest {

    @Test
    public void test() {
        KieSession session = SessionUtil.getStatefulSession();
        String[] names = new String[] {"kitchen", "office", "livingroom"};
        HashMap<String, Room> name2room = Maps.newHashMap();
        for (String name : names) {
            Room room = Room.builder().name(name).build();
            name2room.put(name, room);
            session.insert(room);
            Sprinkler sprinkler = Sprinkler.builder().room(room).build();
            session.insert(sprinkler);
        }

        session.fireAllRules();

        Fire officeFire = Fire.builder().room(name2room.get("office")).build();
        Fire kitchenFire = Fire.builder().room(name2room.get("kitchen")).build();

        session.insert(kitchenFire);
        session.insert(officeFire);

        session.fireAllRules();
    }
}
