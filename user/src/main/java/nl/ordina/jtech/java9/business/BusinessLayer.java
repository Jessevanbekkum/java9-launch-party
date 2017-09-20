package nl.ordina.jtech.java9.business;

import nl.ordina.jtech.java9.service.collections.SuperCollectionService;
import nl.ordina.jtech.java9.service.collections.impl.SuperCollectionServiceArrayListImpl;
import nl.ordina.jtech.java9.user.PlainUser;
import nl.ordina.jtech.java9.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BusinessLayer {
    private final static Logger LOG = LoggerFactory.getLogger(BusinessLayer.class);

    private SuperCollectionService service = new SuperCollectionServiceArrayListImpl();

    private SuperCollectionService internalService;

    public static void main(String[] args) {
        LOG.info("internalServe result: " + BusinessLayer.internalServe(new PlainUser()));
    }

    public BusinessLayer() {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        internalService = applicationContext.getBean("internal", SuperCollectionService.class);
        //- will not compile - package 'opens' in module-info enables just runtime Reflection, not compilation
        //internalService = new SuperCollectionServiceArraysAsListInternal();
    }

    private static String serve(User user, SuperCollectionService service) {
        return user.getName() + ", " + service.serve();
    }

    public static String normalServe(User user) {
        return serve(user, new BusinessLayer().service);
    }

    public static String internalServe(User user) {
        return serve(user, new BusinessLayer().internalService);
    }
}
