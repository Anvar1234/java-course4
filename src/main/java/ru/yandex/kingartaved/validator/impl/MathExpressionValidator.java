package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.preparator.IExpressionPreparator;
import ru.yandex.kingartaved.validator.IValidator;

import java.util.Deque;
import java.util.LinkedList;

import static ru.yandex.kingartaved.utils.Fields.*;

public class MathExpressionValidator implements IValidator {


    private IExpressionPreparator iExpressionPreparator;

    public MathExpressionValidator(IExpressionPreparator iExpressionPreparator) {
        this.iExpressionPreparator = iExpressionPreparator;
    }


    /**
     * The public result method for checking the validity of an expression.
     */
    @Override
    public boolean isExpressionValid() throws RuntimeException {
        if (isBracketsOrderCorrect()) {
            return true;
        } else throw new RuntimeException("Parentheses are incorrect!");
    }


    /**
     * Method for checking the nesting of parentheses in a custom expression.
     */
    private boolean isBracketsOrderCorrect() throws RuntimeException {
        if (isValidTokens()) {
            Deque<Character> stack = new LinkedList<>();
            for (char c : iExpressionPreparator.getPreparedExpression().toCharArray()) {
                //если мапа содержит значение "с" (откр скобка), то пушим ее в стек.
                if (brackets.containsValue(c)) {
                    stack.push(c);
                    //иначе если перед нами закрыв скобка (ключ "с"), то:
                } else if (brackets.containsKey(c)) {
                    //если стек пустой или последнее значение стека != значению по ключу (откр скобка),
                    // что означает что каждой закрыв скобке должна соответствовать (быть в стеке) откр скобка:
                    if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty(); //или tru?
        } else throw new RuntimeException("Invalid characters used!");
    }

    //Method for test
    public boolean isBracketsOrderCorrectForTest(){
        return isBracketsOrderCorrect();
    }


    /**
     * Method for checking if a custom expression contains only valid tokens.
     */
    private boolean isValidTokens() throws RuntimeException {
        if (isNotEmpty()) {
            for (String item : iExpressionPreparator.getPreparedExpression().split("")) {
                if (!tokens.contains(item)) return false;
            }
            return true;
        } else throw new RuntimeException("The expression is empty!");
    }

    //Method for test
    public boolean isValidTokensForTest() {
        return isValidTokens();
    }


    /**
     * Method for checking an expression for emptiness.
     */
    private boolean isNotEmpty() {
        return !iExpressionPreparator.getPreparedExpression().isEmpty();
    }

    //Method for test
    public boolean isNotEmptyForTest() {
        return isNotEmpty();
    }
}
