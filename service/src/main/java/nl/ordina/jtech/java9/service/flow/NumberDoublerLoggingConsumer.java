package nl.ordina.jtech.java9.service.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberDoublerLoggingConsumer {//implements Subscriber<Integer> {
    private final static Logger LOG = LoggerFactory.getLogger(NumberDoublerLoggingConsumer.class);

    //private Flow.Subscription subscription;

    //@Override
    public void onSubscribe(/*Flow.Subscription subscription*/) {
        //- enable this - remember subscription & request one item at a time
        //this.subscription = subscription;
        //subscription.request(1);
    }

    //@Override
    public void onNext(Integer item) {
        LOG.info("onNext: {}", item * 2);
        //- enable this - request one item at a time
        //subscription.request(1);
    }

    //@Override
    public void onError(Throwable throwable) {
        LOG.error("onError", throwable);
    }

    //@Override
    public void onComplete() {
        LOG.info("Done!");
    }
}
