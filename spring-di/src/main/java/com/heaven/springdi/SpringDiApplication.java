package com.heaven.springdi;

import com.heaven.springdi.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiApplication {

    public static void main(String[] args) {

        ApplicationContext ctx =  SpringApplication.run(SpringDiApplication.class, args);
        MyController controller = ctx.getBean(MyController.class);
        System.out.println("In main method");
        System.out.println(controller.sayHello());
    }

}
