package de.fi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        context.registerShutdownHook();

        Demo d = context.getBean(Demo.class);
        System.out.println(d);


    }
}
