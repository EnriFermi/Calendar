package ru.study.calendar.trash;

import java.util.*;

public class HashMapAndListTester {
    private final Map<Integer, Integer> map = new HashMap<>();
    private final List<Integer> list = new ArrayList<>();
    private Integer size;

    public HashMapAndListTester(Integer count, Integer range) {
        String s;
        size = 0;
        for (Integer iterator = range; iterator < count + range; iterator++) {
            map.put(iterator, size);
            list.add(iterator);
            size++;
        }
    }

    public void testList(Integer count) {


        Random random = new Random();
        Integer j;
        Integer s;
        Long result = 0L;
        for (Integer iterator = 0; iterator < count; iterator++) {
            j = random.nextInt(size);
            s = list.get(j);
            j = list.indexOf(s);
            result += s - j.intValue();
        }
        System.out.print(result);
    }

    public void testMap(Integer count) {

        Random random = new Random();
        Integer j;
        Integer s;
        Long result = 0L;
        for (Integer iterator = 0; iterator < count; iterator++) {
            j = random.nextInt(size);
            s = list.get(j);
            j = map.get(s);
            s = list.get(j);
            result += s - j.intValue();
        }
        System.out.print(result);
    }
}


