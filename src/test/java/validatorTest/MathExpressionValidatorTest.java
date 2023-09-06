package validatorTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.IExpressionPreparator;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;
import ru.yandex.kingartaved.validator.IValidator;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import static org.junit.jupiter.api.Assertions.*;

public class MathExpressionValidatorTest {

    @Test
    public void isBracketsOrderCorrectTest(){
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator(" []() [()] ()[] ([])");
        MathExpressionValidator validator = new MathExpressionValidator(iExpressionPreparator);
        assertTrue(validator.isBracketsOrderCorrectForTest());
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest(){
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("([ [(]) ])");
        MathExpressionValidator validator = new MathExpressionValidator(iExpressionPreparator);
        assertFalse(validator.isBracketsOrderCorrectForTest());
    }


    @Test
    public void isValidTokensTest(){
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator(" +-/*()[].,0123456789^");
        MathExpressionValidator validator = new MathExpressionValidator(iExpressionPreparator);
        assertTrue(validator.isValidTokensForTest());
    }
    @Test
    public void isValidTokensNegativeTest(){
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator(" %");
        MathExpressionValidator validator = new MathExpressionValidator(iExpressionPreparator);
        assertFalse(validator.isValidTokensForTest());
    }


    @Test
    public void isNotEmptyTest() {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("1+2");
        MathExpressionValidator mathExpressionValidator = new MathExpressionValidator(iExpressionPreparator);
        assertTrue(mathExpressionValidator.isNotEmptyForTest());
    }
    @Test
    public void isNotEmptyNegativeTest() {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("");
        MathExpressionValidator mathExpressionValidator = new MathExpressionValidator(iExpressionPreparator);
        assertFalse(mathExpressionValidator.isNotEmptyForTest());
    }



}
