package nl.ordina.jtech.java9.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

public class NumberDoublerLoggingConsumer implements Subscriber<Integer> {
    private final static Logger LOG = LoggerFactory.getLogger(NumberDoublerLoggingConsumer.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        LOG.info("onNext: {}", item * 2);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOG.error("onError", throwable);
    }

    @Override
    public void onComplete() {
        LOG.info("Done!");
    }
}
