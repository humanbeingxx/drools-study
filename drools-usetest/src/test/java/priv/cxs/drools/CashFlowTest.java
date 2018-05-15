package priv.cxs.drools;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import priv.cxs.drools.usetest.cashflow.Account;
import priv.cxs.drools.usetest.cashflow.AccountPeriod;
import priv.cxs.drools.usetest.cashflow.CashFlow;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static priv.cxs.drools.usetest.cashflow.CashFlow.CREDIT;
import static priv.cxs.drools.usetest.cashflow.CashFlow.DEBIT;

/**

 *
 * @author xiaoshuang.cui
 * @date 2018/5/14 下午10:14
 **/
public class CashFlowTest {

    @Test
    public void test() throws ParseException {
        KieSession session = SessionUtil.getStatefulSession();

        // 改变顺序
        session.getAgenda().getAgendaGroup("debit").setFocus();
        session.getAgenda().getAgendaGroup("credit").setFocus();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Account account = Account.builder().balance(1000.0).accountNo(123456L).build();

        AccountPeriod accountPeriod = AccountPeriod.builder()
                .startDate(format.parse("2017-09-01"))
                .endDate(format.parse("2018-12-01"))
                .build();

        CashFlow cashFlow1 = CashFlow.builder()
                .accountNo(123456L)
                .type(CREDIT)
                .date(format.parse("2018-01-01"))
                .amount(50.0)
                .build();

        CashFlow cashFlow2 = CashFlow.builder()
                .accountNo(123456L)
                .type(DEBIT)
                .date(format.parse("2018-04-01"))
                .amount(30.0)
                .build();

        CashFlow cashFlow3 = CashFlow.builder()
                .accountNo(123456L)
                .type(CREDIT)
                .date(format.parse("2019-01-01"))
                .amount(15.0)
                .build();

        session.insert(account);
        session.insert(accountPeriod);
        session.insert(cashFlow1);
        session.insert(cashFlow2);
        session.insert(cashFlow3);

        session.fireAllRules();

        Assert.assertEquals(1020.0, account.getBalance(), 0.1);

        // 执行完需要重新setFocus，否则不会再执行
        session.getAgenda().getAgendaGroup("credit").setFocus();

        // another period
        AccountPeriod accountPeriod2 = AccountPeriod.builder()
                .startDate(format.parse("2010-01-01"))
                .endDate(format.parse("2010-12-01"))
                .build();

        CashFlow cashFlow4 = CashFlow.builder()
                .accountNo(123456L)
                .type(CREDIT)
                .date(format.parse("2010-01-02"))
                .amount(1.0)
                .build();

        session.insert(accountPeriod2);
        session.insert(cashFlow4);

        session.fireAllRules();
        Assert.assertEquals(1021.0, account.getBalance(), 0.1);
    }
}
