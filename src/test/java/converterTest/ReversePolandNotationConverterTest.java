package converterTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.IPolandNotationConverter;
import ru.yandex.kingartaved.converter.impl.ReversePolandNotationConverter;
import ru.yandex.kingartaved.preparator.IPreparator;
import ru.yandex.kingartaved.preparator.impl.UnaryMinusPreparator;
import ru.yandex.kingartaved.validator.IValidator;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReversePolandNotationConverterTest {

    @Test
    public void convertToPostfixTest1(){
        IValidator iValidator = new MathExpressionValidator("(-1+5/2+3^)");
        IPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        //косяк в конвертации при добавлении в выражение ниже знака ^, выходит лишняя скобка,
        // во второй версии описал проблему.
        List<String> actualList = reversePolandNotationConverter.resultArrayAfterConversation();
        List<String> expectedList =new ArrayList<>(Arrays.asList("0","1","-","5","2","/","+","3","^","+"));
        assertEquals(expectedList, actualList);
    }
    @Test
    public void convertToPostfixTest2(){
        IValidator iValidator = new MathExpressionValidator("1-(1+2)-3+4-5*7");
        IPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        List<String> actualList = reversePolandNotationConverter.resultArrayAfterConversation();
        List<String> expectedList =new ArrayList<>(Arrays.asList("1", "1", "2", "+", "-", "3", "-", "4", "+", "5", "7", "*", "-"));
        assertEquals(expectedList, actualList);
    }


    @Test
    public void convertToPostfixNegativeTest1() {
        IValidator iValidator = new MathExpressionValidator("(-1+5/2+3^)");
        IPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        //косяк в конвертации при добавлении в выражение ниже знака ^, выходит лишняя скобка,
        // во второй версии описал проблему.
        List<String> actualList = reversePolandNotationConverter.resultArrayAfterConversation();
        List<String> expectedList = new ArrayList<>(Arrays.asList("0", "1", "-", "5", "2", "/", "+", "3", "^", "("));
        assertNotEquals(expectedList, actualList);
    }
    @Test
    public void convertToPostfixNegativeTest2() {
        IValidator iValidator = new MathExpressionValidator("1-(1+2)-3+4-5*7");
        IPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        //косяк в конвертации при добавлении в выражение ниже знака ^, выходит лишняя скобка,
        // во второй версии описал проблему.
        List<String> actualList = reversePolandNotationConverter.resultArrayAfterConversation();
        List<String> expectedList =new ArrayList<>(Arrays.asList("1", "1", "2", "+", "-", "3", "-", "4", "+", "5", "7", "*", "("));
        assertNotEquals(expectedList, actualList);
    }


}
