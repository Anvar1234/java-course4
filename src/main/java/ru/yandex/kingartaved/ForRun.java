package ru.yandex.kingartaved;

import java.util.ArrayList;
import java.util.List;

public class ForRun {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("Строка");
        list.add(10);
        list.add(true);

        for (Object element : list) {
            System.out.println(element);
        }
    }
}
