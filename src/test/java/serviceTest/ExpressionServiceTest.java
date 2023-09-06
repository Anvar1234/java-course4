package serviceTest;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.IPolandNotationConverter;
import ru.yandex.kingartaved.converter.impl.ReversePolandNotationConverter;
import ru.yandex.kingartaved.preparator.IExpressionPreparator;
import ru.yandex.kingartaved.preparator.IUnaryMinusPreparator;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;
import ru.yandex.kingartaved.preparator.impl.UnaryMinusPreparator;
import ru.yandex.kingartaved.service.IExpressionService;
import ru.yandex.kingartaved.service.impl.ExpressionService;
import ru.yandex.kingartaved.validator.IValidator;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionServiceTest {
    @Test
    public void sizeEqualityTest() {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator(" (-1+5/2+3^)");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator, iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(10.5));
        assertEquals(actualDeque.size(), expectedDeque.size());
    }

    @Test
    public void sizeEqualityNegativeTest() {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("(-1+5/2+3^)");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator, iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(10.5, 2.0));
        assertNotEquals(actualDeque.size(), expectedDeque.size());
    }


    @Test
    public void calculatePostfixNotationTest1() throws RuntimeException {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("(-1+5/2+3^)");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator,iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(10.5));
        assertEquals(expectedDeque.peek(), actualDeque.peek());
    }

    @Test
    public void calculatePostfixNotationTest2() throws RuntimeException {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("1-(1+2)-3+4-5*7");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator,iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(-36.0));
        assertEquals(expectedDeque.peek(), actualDeque.peek());
    }


    @Test
    public void calculatePostfixNotationNegativeTest1() throws RuntimeException {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("(1+5/2+3^)");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator,iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(10.5)); //12.5
        assertNotEquals(expectedDeque.peek(), actualDeque.peek());
    }

    @Test
    public void calculatePostfixNotationNegativeTest2() throws RuntimeException {
        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator("-1-(1+2)-3+4-5*7");
        IValidator iValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(iValidator,iExpressionPreparator);
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> actualDeque = expressionService.resultDequeAfterCalculation();
        Deque<Double> expectedDeque = new ArrayDeque<>(List.of(-36.0)); //-38.0
        assertNotEquals(expectedDeque.peek(), actualDeque.peek());
    }


}
