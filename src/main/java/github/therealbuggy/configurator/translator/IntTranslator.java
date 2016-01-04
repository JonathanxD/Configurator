package github.therealbuggy.configurator.translator;

import com.udojava.evalex.Expression;
import github.therealbuggy.configurator.IConfigurator;

/**
 * Created by jonathan on 02/01/16.
 */
public class IntTranslator extends ExpressionTranslator<Integer> {

    IntTranslator() {

    }

    public IntTranslator(IConfigurator configurator) {
        super(configurator);
    }

    @Override
    public Integer translate(String expressionString) {

        Expression expression = getTranslatedExpression(expressionString);
        return expression.eval().intValueExact();
    }


}
