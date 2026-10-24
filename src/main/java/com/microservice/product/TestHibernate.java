package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHibernate {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        if (sessionFactory != null) {
            System.out.println("SessionFactory initialisee : " + sessionFactory.getClass().getName());
        }
        System.out.println("Contexte Spring initialise avec succes !");

        context.close();
    }
}
