package nl.ordina.jtech.java9.business;

import nl.ordina.jtech.java9.service.collections.SuperCollectionService;
import nl.ordina.jtech.java9.service.collections.impl.SuperCollectionServiceArrayListImpl;
import nl.ordina.jtech.java9.user.BaseUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BusinessLayer {
    private SuperCollectionService service = new SuperCollectionServiceArrayListImpl();

    private SuperCollectionService internalService;

    public BusinessLayer() {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        internalService = applicationContext.getBean("internal", SuperCollectionService.class);
        //- will not compile - package 'opens' in module-info enables just runtime Reflection, not compilation
        //internalService = new SuperCollectionServiceArraysAsListInternal();
    }

    private static String serve(BaseUser user, SuperCollectionService service) {
        return user.getName() + ", " + service.serve();
    }

    public static String normalServe(BaseUser user) {
        return serve(user, new BusinessLayer().service);
    }

    public static String internalServe(BaseUser user) {
        return serve(user, new BusinessLayer().internalService);
    }
}
