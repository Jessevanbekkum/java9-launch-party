package nl.ordina.jtech.java9.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

public class StringLoggingConsumer implements Flow.Subscriber<String> {
    private final static Logger LOG = LoggerFactory.getLogger(StringLoggingConsumer.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        LOG.info("onNext {}", item);
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
