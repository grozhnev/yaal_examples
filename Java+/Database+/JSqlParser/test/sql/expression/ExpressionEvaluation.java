package sql.expression;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.junit.Test;

public class ExpressionEvaluation {
    @Test
    public void test() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("a*(5+mycolumn)");
    }
}
