package github.therealbuggy.configurator.translator;

import com.udojava.evalex.Expression;
import github.therealbuggy.configurator.IConfigurator;


/**
 * Created by jonathan on 02/01/16.
 */
public abstract class ExpressionTranslator<T> extends VariableTranslator<T> {

    ExpressionTranslator() {
        super();
    }

    ExpressionTranslator(IConfigurator configurator) {
        super(configurator);
    }

    Expression getTranslatedExpression(String expressionString){

        UniversalTranslator translator = new UniversalTranslator(getConfigurator());

        String resultExpression = translator.translate(expressionString);

        Expression expression = new Expression(resultExpression);

        return expression;
    }
}
