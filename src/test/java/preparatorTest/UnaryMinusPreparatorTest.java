package preparatorTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.IPreparator;
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
        IValidator iValidator1 = new MathExpressionValidator("(-1+5)");
        IValidator iValidator2 = new MathExpressionValidator("(-(-1)+5)");
        IPreparator unaryMinusPreparator1 = new UnaryMinusPreparator(iValidator1);
        IPreparator unaryMinusPreparator2 = new UnaryMinusPreparator(iValidator2);
        List<String> actualList1 = unaryMinusPreparator1.resultArrayAfterTransformation();
        List<String> actualList2 = unaryMinusPreparator2.resultArrayAfterTransformation();
        ArrayList<String> expectedList1 =new ArrayList<>(Arrays.asList("(","0","-","1","+","5",")"));
        ArrayList<String> expectedList2 =new ArrayList<>(Arrays.asList("(","0", "-","(","0","-","1",")","+","5",")"));
        assertEquals(expectedList1, actualList1);
        assertEquals(expectedList2, actualList2);
    }
    @Test
    public void unaryMinusChangerNegativeTest(){
        IValidator iValidator = new MathExpressionValidator("(-1+2)");
        IPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator);
        List<String> actualList = unaryMinusPreparator.resultArrayAfterTransformation();
        ArrayList<String> expectedList =new ArrayList<>(Arrays.asList("(","-","1","+","2",")"));
        assertNotEquals(expectedList, actualList);
    }


}
