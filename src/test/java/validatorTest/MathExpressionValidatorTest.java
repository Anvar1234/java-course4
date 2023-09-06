package validatorTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import static org.junit.jupiter.api.Assertions.*;

public class MathExpressionValidatorTest {

    @Test
    public void isBracketsOrderCorrectTest(){
        MathExpressionValidator validator = new MathExpressionValidator(" []() [()] ()[] ([])");
        assertTrue(validator.isBracketsOrderCorrectForTest());
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest(){
        MathExpressionValidator validator = new MathExpressionValidator("([ [(]) ])");
        assertFalse(validator.isBracketsOrderCorrectForTest());
    }


    @Test
    public void isValidTokensTest(){
        MathExpressionValidator validator = new MathExpressionValidator(" +-/*()[].,0123456789^");//пробел зачищается изначально
        assertTrue(validator.isValidTokensForTest());
    }
    @Test
    public void isValidTokensNegativeTest(){
        MathExpressionValidator validator = new MathExpressionValidator(" %");
        assertFalse(validator.isValidTokensForTest());
    }


    @Test
    public void isNotEmptyTest() {
        MathExpressionValidator mathExpressionValidator = new MathExpressionValidator("1+2");
        assertTrue(mathExpressionValidator.isNotEmptyForTest());
    }
    @Test
    public void isNotEmptyNegativeTest() {
        MathExpressionValidator mathExpressionValidator = new MathExpressionValidator("");
        assertFalse(mathExpressionValidator.isNotEmptyForTest());
    }



}
