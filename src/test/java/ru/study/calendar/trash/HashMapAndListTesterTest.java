package ru.study.calendar.trash;


import org.junit.jupiter.api.Test;

class HashMapAndListTesterTest { ;
    @Test
    public void TestMap() {
        Integer counter = 10000;
        Integer range = 10000;
        Integer count = 1000000;
        HashMapAndListTester test = new HashMapAndListTester(counter, range);
        test.testMap(count);
    }
    @Test
    public void TestList() {
        Integer counter = 10000;
        Integer range = 10000;
        Integer count = 1000000;
        HashMapAndListTester test = new HashMapAndListTester(counter, range);
        test.testList(count);
    }
}