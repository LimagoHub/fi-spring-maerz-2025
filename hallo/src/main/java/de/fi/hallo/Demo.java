package de.fi.hallo;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@Lazy
public class Demo {

    public Demo() {
        System.out.println("Ctor Demo");
    }
}
