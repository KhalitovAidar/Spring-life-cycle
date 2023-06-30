package com.spring.life.cycle.quoters;

import com.spring.life.cycle.quoters.annotations.InjectRandomInt;
import com.spring.life.cycle.quoters.annotations.PostProxy;
import com.spring.life.cycle.quoters.annotations.Profiling;
import jakarta.annotation.PostConstruct;

//Для обработки аннотации нужен свой компонент Spring(BeanPostProcessor)
@Profiling
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min=2, max=7)
    private int repeat;
    private String message;

    public TerminatorQuoter() {
        System.out.println("Фаза конструктора");
    }

    @PostConstruct
    private void init() {
        System.out.println("Фаза PostConstruct(или init метода)");
    }
    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Фаза PostProxy");
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
