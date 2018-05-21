package priv.cxs.drools;

import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.*;
import priv.cxs.drools.usetest.drls.ageinfer.Person;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/5/21 下午9:28
 **/
public class QueryTest {

    @Test
    public void testQuery() {
        KieSession session = SessionUtil.getStatefulSession();
        session.insert(Person.builder().age(10).build());
        session.insert(Person.builder().age(11).build());
        session.insert(Person.builder().age(12).build());
        session.insert(Person.builder().age(13).build());

        QueryResults queryResults = session.getQueryResults("query-age");

//        session.fireAllRules();

        for (QueryResultsRow queryResult : queryResults) {
            System.out.println(queryResult.get("$person"));
        }
    }

    @Test
    public void testLiveQuery() {
        KieSession session = SessionUtil.getStatefulSession();

        LiveQuery liveQuery = session.openLiveQuery("query-age", new Object[]{}, new ViewChangedEventListener() {
            @Override
            public void rowInserted(Row row) {
                System.out.println("insert " + row.get("$person"));
            }

            @Override
            public void rowDeleted(Row row) {
                System.out.println("delete " + row.get("$person"));

            }

            @Override
            public void rowUpdated(Row row) {
                System.out.println("update " + row.get("$person"));
            }
        });

        session.insert(Person.builder().age(10).build());
        session.insert(Person.builder().age(11).build());
        session.insert(Person.builder().age(12).build());
        session.insert(Person.builder().age(13).build());

        session.fireAllRules();

        liveQuery.close();
    }
}
