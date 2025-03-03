package de.fi.hallo.unterordner;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")

@RequiredArgsConstructor
public class Demo {

    //@Qualifier("upper")
    private final Translator translator;

    @Value("${Demo.message}")
    private final String  message ;



    /*public Demo() {
        System.out.println("Ctor Demo");
    }*/

    @PostConstruct
    public void foo() {
        System.out.println(translator.translate(message));
    }

   @PreDestroy
    public void bar() {
        System.out.println("Und tschuess");
    }
}
