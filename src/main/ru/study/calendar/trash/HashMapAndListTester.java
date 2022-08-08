package ru.study.calendar.trash;

import java.util.*;

public class HashMapAndListTester {
    private Map<String, Integer> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private Integer size;
    public HashMapAndListTester(Integer count, Integer range) {
        String s;
        size = 0;
        for(Integer iterator = range; iterator< count + range; iterator++) {
            s = iterator.toString();
            map.put(s, size);
            list.add(s);
            size ++;
        }
    }
    public void testList(Integer count) {

        Random random = new Random();
        Integer j;
        String s;
        for(Integer iterator=0; iterator<count; iterator ++){
            j = random.nextInt(size);
            s = list.get(j);
            j = list.indexOf(s);
        }
    }
    public void testMap(Integer count) {

        Random random = new Random();
        Integer j;
        String s;
        for(Integer iterator=0; iterator<count; iterator ++){
            j = random.nextInt(size);
            s = list.get(j);
            j = map.get(s);
            s = list.get(j);
        }
    }
}


