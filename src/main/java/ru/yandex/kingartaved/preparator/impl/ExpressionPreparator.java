package ru.yandex.kingartaved.preparator.impl;

import ru.yandex.kingartaved.preparator.IExpressionPreparator;

public class ExpressionPreparator implements IExpressionPreparator {

    private final String preparedExpression;

    public ExpressionPreparator(String inputExpression) {
        this.preparedExpression = inputExpression.replaceAll(" ", "").trim();
    }

    @Override
    public String getPreparedExpression() {
        return preparedExpression;
    }
}
