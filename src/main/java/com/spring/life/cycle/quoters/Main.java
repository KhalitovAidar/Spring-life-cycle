package com.spring.life.cycle.quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// Создание подобных тестов в классе Main некорректно и я делаю это намеренно
// тк проект демонстративный
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    }
}
