package ru.yandex.kingartaved.converter.impl;

import ru.yandex.kingartaved.converter.IPolandNotationConverter;
import ru.yandex.kingartaved.preparator.IPreparator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static ru.yandex.kingartaved.utils.Fields.priorities;
import static ru.yandex.kingartaved.utils.Utils.isNumber;

public class ReversePolandNotationConverter implements IPolandNotationConverter { //Reverse Polish notation
    private IPreparator iPreparator;

    public ReversePolandNotationConverter(IPreparator iPreparator) {
        this.iPreparator = iPreparator;
    }

    @Override
    public List<String> resultArrayAfterConversation() {
        return convertToPostfix();
    }


    /**
     * Метод для перевода пользовательского выражения в обратную польскую нотацию.
     */
    private List<String> convertToPostfix() {

        //IPreparator unaryMinusPreparator = new UnaryMinusPreparator();
        List<String> validExpressionAfterTransformation = iPreparator.resultArrayAfterTransformation();


        Deque<String> operators = new ArrayDeque<>(); //стек операторов
        List<String> resultPostfixArray = new ArrayList<>(); //коллекция вывода

        for (String item : validExpressionAfterTransformation) {
            //Если элемент массива число, то в выводной список:
            if (isNumber(item)) {
                resultPostfixArray.add(item);
            }
            //Если элемент массива открывающая скобка "(":
            else if (item.equals("(")) {
                //добавляем элемент в стек операторов:
                operators.push(item);
            } else if (item.equals(")")) {//Если элемент массива закр скобка ")", то:
                //todo Условие ниже (первый if) скорее всего тоже можно убрать.
                if (!operators.isEmpty()) {//если стек операторов не пуст, то:
                    if (!operators.peek().equals("(")) {//пока не встретим на вершине стека откр скобку "(":
                        //добавлялем в вывод список элементы из стека операторов (опер-ры удаляем):
                        resultPostfixArray.add(operators.poll());
                    }
                    //иначе удаляем откр скобку (последний эл-нт): до этого было operators.remove("(");
                    operators.remove("("); //.poll();
                }
            }
            /**
             * Блок else когда входит оператор:
             */
            else {
                int incomingPriority = priorities.get(item);

                //Если Стек операторов пуст или верхний элемент Стека == "(":
                if (operators.isEmpty() || operators.peek().equals("(")) {
                    operators.push(item);

                    //Если входящий priority > приоритета последнего элемента Стэка:
                } else if (incomingPriority > priorities.get(operators.peek())) {
                    operators.push(item);
                }

                //Условие "Если входящий priority <= приоритета верхнего элемента Стэка"
                else if (incomingPriority <= priorities.get(operators.peek())) {

                    //  Ошибка была в том, что в цикле while вместо && ставил ||, и peek выдавал ноль.
                    while (!operators.isEmpty() && !operators.peek().equals("(") && incomingPriority <= priorities.get(operators.peek())) {
                        resultPostfixArray.add(operators.poll());
                    }
                    operators.push(item);
                }
            }
        }
        while (!operators.isEmpty()) {
            resultPostfixArray.add(operators.poll());
        }
        return resultPostfixArray;
    }


}
