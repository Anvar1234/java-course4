package ru.yandex.kingartaved;

import ru.yandex.kingartaved.converter.IPolandNotationConverter;
import ru.yandex.kingartaved.converter.impl.ReversePolandNotationConverter;
import ru.yandex.kingartaved.preparator.IExpressionPreparator;
import ru.yandex.kingartaved.preparator.IUnaryMinusPreparator;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;
import ru.yandex.kingartaved.preparator.impl.UnaryMinusPreparator;
import ru.yandex.kingartaved.service.impl.ExpressionService;
import ru.yandex.kingartaved.service.IExpressionService;
import ru.yandex.kingartaved.validator.IValidator;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //примеры выражений и ОПН выражения:
        System.out.println("-3*1*2^+5/2 =     [0, 1, -, 3, *, 1, *, 2, ^, *, 5, 2, /, +]  = -9.5");
        System.out.println("-(-1-(1+2)) =     [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]     = 4.0");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("-3+(1-(1+2)) =    [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) =     [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2^+5 =        [3, 1, 2, ^, *, +, 5, +]");
        System.out.println("( -1+ 5/2+3^) =   [0, 1, -, 5, 2, /, +, 3, ^, +]");
        System.out.println("-1+ 5/2+3^ =      [0, 1, -, 5, 2, /, +, 3, ^, +]");

        //присваиваем пользовательский ввод переменной inputExpression.
        String inputExpression = prompt();

        IExpressionPreparator iExpressionPreparator = new ExpressionPreparator(inputExpression);
        IValidator mathExpressionValidator = new MathExpressionValidator(iExpressionPreparator);
        IUnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(mathExpressionValidator, iExpressionPreparator);
        List<String> tempArray = unaryMinusPreparator.resultArrayAfterTransformation(); // specialSymbolChanger();
        System.out.println("После трансформатора:\n" + tempArray);

        //Загоняем выражение, прошедшее проверку и преобразование в ОПН-конвертер.
        IPolandNotationConverter reversePolandNotationConverter = new ReversePolandNotationConverter(unaryMinusPreparator);
        List<String> resultArrayOfConversation = reversePolandNotationConverter.resultArrayAfterConversation();
        System.out.println("ОПН:\n" + resultArrayOfConversation);

        //Загоняем ОПН-выражение в калькулятор (котор высчитывает ОПН).
        IExpressionService expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> finalResultArray = expressionService.resultDequeAfterCalculation();
        //Выводим результат работы калькулятора.
        System.out.println("Результат:\n" + finalResultArray);


    }


    //    private static void start(){
//        while (true){
//            prompt();
//        }
//    }
    private static String prompt() {
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}

