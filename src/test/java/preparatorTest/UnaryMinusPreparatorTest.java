package preparatorTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.IExpressionPreparator;
import ru.yandex.kingartaved.preparator.IUnaryMinusPreparator;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;
import ru.yandex.kingartaved.preparator.impl.UnaryMinusPreparator;
import ru.yandex.kingartaved.validator.IValidator;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnaryMinusPreparatorTest {

    @Test
    public void unaryMinusChangerTest(){
        IExpressionPreparator iExpressionPreparator1 = new ExpressionPreparator("(-1+5)");
        IValidator iValidator1 = new MathExpressionValidator(iExpressionPreparator1);
        IUnaryMinusPreparator unaryMinusPreparator1 = new UnaryMinusPreparator(iValidator1, iExpressionPreparator1);
        List<String> actualList1 = unaryMinusPreparator1.resultArrayAfterTransformation();
        ArrayList<String> expectedList1 =new ArrayList<>(Arrays.asList("(","0","-","1","+","5",")"));
        assertEquals(expectedList1, actualList1);

        IExpressionPreparator iExpressionPreparator2 = new ExpressionPreparator("(-(-1)+5)");
        IValidator iValidator2 = new MathExpressionValidator(iExpressionPreparator2);
        IUnaryMinusPreparator unaryMinusPreparator2 = new UnaryMinusPreparator(iValidator2, iExpressionPreparator2);
        List<String> actualList2 = unaryMinusPreparator2.resultArrayAfterTransformation();
        ArrayList<String> expectedList2 =new ArrayList<>(Arrays.asList("(","0", "-","(","0","-","1",")","+","5",")"));
        assertEquals(expectedList2, actualList2);
    }
    @Test
    public void unaryMinusChangerNegativeTest(){
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("(-1+2)");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator, iExpressionPreparator);
        List<String> actualList = unaryMinusPreparator.resultArrayAfterTransformation();
        ArrayList<String> expectedList =new ArrayList<>(Arrays.asList("(","-","1","+","2",")"));
        assertNotEquals(expectedList, actualList);
    }


}
