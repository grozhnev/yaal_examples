package transaction.declarative.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TransactionalConfig.class)
public class TransactionalTest {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PlatformTransactionManager txManager;

    @Test
    public void queryForObject() {
        TransactionDefinition definition = new DefaultTransactionAttribute();
        TransactionStatus txStatus = txManager.getTransaction(definition);
        assertEquals("Mary", template.queryForObject("SELECT title FROM names WHERE id=2", String.class));
        txManager.commit(txStatus);
    }
}