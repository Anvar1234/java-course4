package ru.yandex.kingartaved;

public class ExampleTDD {

    public int calculate(int x, int y, char symbol){
        if(symbol == '+') return x+y;
        else if(symbol == '-') return x-y;
        else return x*y;
    }
}
